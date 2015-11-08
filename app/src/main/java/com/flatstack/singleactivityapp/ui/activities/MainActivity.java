package com.flatstack.singleactivityapp.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.flatstack.singleactivityapp.R;
import com.flatstack.singleactivityapp.ui.fragments.FragmentTransactionEvent;
import com.flatstack.singleactivityapp.ui.fragments.HomeButtonAction;
import com.flatstack.singleactivityapp.ui.fragments.InitialFragment;
import com.flatstack.singleactivityapp.utils.AndroidUtils;
import com.flatstack.singleactivityapp.utils.Bus;


public class MainActivity extends AppCompatActivity {

    protected HomeButtonAction currentHomeBtnMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bus.subscribe(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransactionEvent.with(new InitialFragment()).emit();
        }
    }

    public void replaceFragment(FragmentTransactionEvent transactionInfo) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (transactionInfo.isClearBackStack()) {
            clearFragmentBackStack();
        }
        if (transactionInfo.isNeedAnimation()) {
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        }
        if (transactionInfo.isAddToBackStack()) {
            ft.addToBackStack(null);
        }
        ft.replace(R.id.content_frame, transactionInfo.getFragment());
        ft.commitAllowingStateLoss();
        AndroidUtils.hideKeyboard(this);
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
