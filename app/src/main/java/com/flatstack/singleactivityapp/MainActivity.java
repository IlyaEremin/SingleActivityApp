package com.flatstack.singleactivityapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.flatstack.singleactivityapp.fragments.HomeButtonAction;
import com.flatstack.singleactivityapp.fragments.InitialFragment;
import com.flatstack.singleactivityapp.fragments.ReplaceFragment;
import com.flatstack.singleactivityapp.utils.Bus;


public class MainActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bus.subscribe(this);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            ReplaceFragment.with(new InitialFragment()).go();
        }
    }

    private void replaceFragment(ReplaceFragment replaceIFragmentInfo) {
        if (replaceIFragmentInfo.isClearBackStack()){
            clearFragmentBackStack();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (replaceIFragmentInfo.isAddToBackStack()) {
            ft.addToBackStack(null);
        }
        ft.replace(R.id.content_frame, replaceIFragmentInfo.getFragment());
        ft.commit();
    }

    private void clearFragmentBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void onEvent(ReplaceFragment replace){
        replaceFragment(replace);
    }

    @Override protected void onDestroy() {
        Bus.unsubscribe(this);
        super.onDestroy();
    }

    public void onEvent(HomeButtonAction event) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(event == HomeButtonAction.BACK);
    }
}
