package com.product.nguyencongduc.bookticketapplication.activities;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Locale;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by nguyencongduc on 3/30/18.
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected abstract int getContentView();

    /**
     * @param savedInstanceState
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //SETTING LOCATE
        Locale locale = Locale.JAPANESE;
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        // PRINT SCREEN NAME
        Log.d("Screen name: ", this.getClass().getSimpleName());
        // BIND VIEW
        setContentView(getContentView());
        ButterKnife.bind(this);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Using for custom font
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
