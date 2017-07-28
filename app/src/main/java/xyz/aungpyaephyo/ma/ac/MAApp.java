package xyz.aungpyaephyo.ma.ac;

import android.app.Application;

import xyz.aungpyaephyo.ma.ac.data.models.AttractionsModel;

/**
 * Created by aung on 7/28/17.
 */

public class MAApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AttractionsModel.getInstance().loadAttractions();
    }
}
