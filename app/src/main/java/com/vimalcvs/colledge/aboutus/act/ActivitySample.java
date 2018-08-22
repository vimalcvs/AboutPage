package com.vimalcvs.colledge.aboutus.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vimalcvs.colledge.R;
import com.vimalcvs.colledge.aboutus.helper.SampleHelper;

/**
 * Created by VimalCVS on 22/08/18.
 */
public class ActivitySample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sample_view);

        SampleHelper.with(this).init().loadAbout();

    }
}
