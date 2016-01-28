package com.flatstack.singleactivityapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.flatstack.singleactivityapp.ui.fragments.HomeButtonAction;
import com.flatstack.singleactivityapp.ui.fragments.InitialFragment;


public class MainActivity extends AppCompatActivity {

    protected HomeButtonAction currentHomeBtnMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Navigator.initialize(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Navigator.showScreen(new InitialFragment());
        }
    }

    public void setHomeBtn(HomeButtonAction event) {
        switch (event) {
            case BACK:
                this.currentHomeBtnMode = event;
                break;
            case NONE:
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
        }
    }

    public void setUpActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
