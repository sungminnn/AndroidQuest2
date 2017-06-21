package com.mulcam.c901.ari.androidquest;

import com.tsengvn.typekit.Typekit;

/**
 * Created by student on 2017-06-21.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/BMDOHYEON_ttf.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/BMDOHYEON_ttf.ttf"));
    }


}
