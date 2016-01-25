package com.kienvt.tclgt.common;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by johnvi on 1/26/16.
 */
public class TclgtApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(this);
    }
}
