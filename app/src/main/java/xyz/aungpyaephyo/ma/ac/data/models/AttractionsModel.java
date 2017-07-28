package xyz.aungpyaephyo.ma.ac.data.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import xyz.aungpyaephyo.ma.ac.data.vo.AttractionVO;
import xyz.aungpyaephyo.ma.ac.events.DataEvent;
import xyz.aungpyaephyo.ma.ac.network.AttractionDataAgent;
import xyz.aungpyaephyo.ma.ac.network.RetrofitDataAgent;

/**
 * Created by aung on 7/28/17.
 */

public class AttractionsModel extends ViewModel {

    private AttractionDataAgent mDataAgent;

    private MutableLiveData<List<AttractionVO>> mAttractionList;

    public AttractionsModel() {
        mDataAgent = RetrofitDataAgent.getInstance();
        EventBus.getDefault().register(this);
        mAttractionList = new MutableLiveData<>();
        mDataAgent.loadAttractions();
    }

    public LiveData<List<AttractionVO>> getAttractions() {
        return mAttractionList;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAttractionsLoadedEvent(DataEvent.AttractionsLoadedEvent event) {
        mAttractionList.setValue(event.getAttractionList());
    }
}
