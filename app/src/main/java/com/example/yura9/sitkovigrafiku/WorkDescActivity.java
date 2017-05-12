package com.example.yura9.sitkovigrafiku;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by yura9 on 4/9/2017.
 */

public class WorkDescActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.workContainer);
        if (f == null) {
            f = new WorkDescFragment();
            fm.beginTransaction().add(R.id.workContainer, f).commit();
        }
    }
}
