package xyz.aungpyaephyo.ma.ac.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import xyz.aungpyaephyo.ma.ac.MAApp;
import xyz.aungpyaephyo.ma.ac.data.db.AppDatabase;
import xyz.aungpyaephyo.ma.ac.data.vo.AttractionVO;
import xyz.aungpyaephyo.ma.ac.events.DataEvent;
import xyz.aungpyaephyo.ma.ac.network.AttractionDataAgent;
import xyz.aungpyaephyo.ma.ac.network.RetrofitDataAgent;

/**
 * Created by aung on 7/28/17.
 */

public class AttractionsModel extends ViewModel {

    private AttractionDataAgent mDataAgent;
    private AppDatabase mAppDatabase;

    private LiveData<Map<String, AttractionVO>> mAttractionMapLiveData;

    public AttractionsModel() {
        mDataAgent = RetrofitDataAgent.getInstance();
        EventBus.getDefault().register(this);

        mDataAgent.loadAttractions();
    }

    public void initDatabase(Context context) {
        mAppDatabase = AppDatabase.getAttractionsDatabase(context);
    }

    public LiveData<List<AttractionVO>> getAttractions() {
        return mAppDatabase.attractionsDao().getAllAttractions();
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
        mAppDatabase.attractionsDao().deleteAll();
        long[] insertedIds = mAppDatabase.attractionsDao().insertAttractions(event.getAttractionList().toArray(new AttractionVO[0]));
        Log.d(MAApp.TAG, "Total inserted count : " + insertedIds.length);
    }
}
