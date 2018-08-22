package com.vimalcvs.colledge.aboutus.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vimalcvs.colledge.R;
import com.vimalcvs.colledge.aboutus.helper.SampleHelper;

/**
 * Created by VimalCVS on 22/08/18.
 */
public class FragmentActivitySample extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new LinearLayout(this));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new FragmentSample())
                .commit();
    }

    public static class FragmentSample extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.sample_view, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setRetainInstance(true);

            SampleHelper.with(getActivity()).init().loadAbout();
        }
    }

}
