package xyz.aungpyaephyo.ma.ac.data.models;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import xyz.aungpyaephyo.ma.ac.MAApp;
import xyz.aungpyaephyo.ma.ac.data.db.AppDatabase;
import xyz.aungpyaephyo.ma.ac.data.vo.AttractionVO;
import xyz.aungpyaephyo.ma.ac.data.vo.ImageInAttractionVO;
import xyz.aungpyaephyo.ma.ac.events.DataEvent;
import xyz.aungpyaephyo.ma.ac.network.AttractionDataAgent;
import xyz.aungpyaephyo.ma.ac.network.RetrofitDataAgent;

/**
 * Created by aung on 7/28/17.
 */

public class AttractionsModel extends ViewModel {

    private AttractionDataAgent mDataAgent;
    private AppDatabase mAppDatabase;

    public AttractionsModel() {
        mDataAgent = RetrofitDataAgent.getInstance();
        EventBus.getDefault().register(this);

        mDataAgent.loadAttractions();
    }

    public void initDatabase(Context context) {
        mAppDatabase = AppDatabase.getInMemoryDatabase(context);
    }

    public LiveData<List<AttractionVO>> getAttractions(LifecycleOwner lifecycleOwner) {
        LiveData<List<AttractionVO>> attractionList = mAppDatabase.attractionsDao().getAllAttractions();
        attractionList.observe(lifecycleOwner, new Observer<List<AttractionVO>>() {
            @Override
            public void onChanged(@Nullable List<AttractionVO> attractionVOs) {
                for (AttractionVO attraction : attractionVOs) {
                    List<ImageInAttractionVO> imageInAttractionVOs = mAppDatabase.imagesInAttractionsDao().getImagesByAttraction(attraction.getId());
                    String[] attractionImages = new String[imageInAttractionVOs.size()];
                    int index = 0;
                    for (ImageInAttractionVO imageInAttraction : imageInAttractionVOs) {
                        attractionImages[index++] = imageInAttraction.getImagePath();
                    }
                    attraction.setImages(attractionImages);
                }
            }
        });
        return attractionList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        AppDatabase.destroyInstance();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onAttractionsLoadedEvent(DataEvent.AttractionsLoadedEvent event) {
        //mAttractionList.setValue(event.getAttractionList());
        mAppDatabase.attractionsDao().deleteAll();
        int index = 0;
        for (AttractionVO attraction : event.getAttractionList()) {
            attraction.setId(mAppDatabase.attractionsDao().insertAttraction(attraction));
            for (String image : attraction.getImages()) {
                ImageInAttractionVO imageInAttraction = new ImageInAttractionVO();
                imageInAttraction.setImagePath(image);
                imageInAttraction.setAttractionId(attraction.getId());
                mAppDatabase.imagesInAttractionsDao().insertImageInAttraction(imageInAttraction);
                Log.d(MAApp.TAG, "Inserted Image for " + attraction.getTitle() + " - " + image);
            }

            index++;
        }

        Log.d(MAApp.TAG, "Total inserted count : " + index);
    }
}
