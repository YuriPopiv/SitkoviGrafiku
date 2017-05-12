package com.example.yura9.sitkovigrafiku;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WorkListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_activity);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.workContainer);
        if (f == null) {
            f = new WorkListFragment();
            fm.beginTransaction().add(R.id.workContainer, f).commit();
        }
    }
}
