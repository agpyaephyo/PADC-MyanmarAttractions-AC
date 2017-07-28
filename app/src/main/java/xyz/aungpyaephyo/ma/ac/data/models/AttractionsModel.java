package xyz.aungpyaephyo.ma.ac.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.aungpyaephyo.ma.ac.events.DataEvent;
import xyz.aungpyaephyo.ma.ac.network.AttractionDataAgent;
import xyz.aungpyaephyo.ma.ac.network.RetrofitDataAgent;

/**
 * Created by aung on 7/28/17.
 */

public class AttractionsModel {

    private static AttractionsModel objInstance;

    private AttractionDataAgent mDataAgent;

    private AttractionsModel() {
        mDataAgent = RetrofitDataAgent.getInstance();
        EventBus.getDefault().register(this);
    }

    public static AttractionsModel getInstance() {
        if (objInstance == null) {
            objInstance = new AttractionsModel();
        }
        return objInstance;
    }

    public void loadAttractions() {
        mDataAgent.loadAttractions();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onAttractionsLoadedEvent(DataEvent.AttractionsLoadedEvent event) {

    }
}
