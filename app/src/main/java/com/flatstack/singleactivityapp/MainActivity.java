package com.flatstack.singleactivityapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.flatstack.singleactivityapp.fragments.FragmentTransactionEvent;
import com.flatstack.singleactivityapp.fragments.InitialFragment;
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
            FragmentTransactionEvent.with(new InitialFragment()).emit();
        }
    }

    private void replaceFragment(FragmentTransactionEvent transactionInfo) {
        if (transactionInfo.isClearBackStack()){
            clearFragmentBackStack();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (transactionInfo.isAddToBackStack()) {
            ft.addToBackStack(null);
        }
        ft.replace(R.id.content_frame, transactionInfo.getFragment());
        ft.commit();
    }

    private void clearFragmentBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void onEvent(FragmentTransactionEvent replace){
        replaceFragment(replace);
    }

    @Override protected void onDestroy() {
        Bus.unsubscribe(this);
        super.onDestroy();
    }
}
