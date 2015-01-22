package com.flatstack.singleactivityapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.flatstack.singleactivityapp.R;
import com.flatstack.singleactivityapp.utils.Constant;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public class InitialFragment extends BaseFragment {

    public static final int TYPE_REQUEST_CODE = 1111;

    String someText = "initial fragment aha";

    @NonNull @Override protected FragmentInfo getFragmentInfo() {
        return new FragmentInfo(R.layout.initial_fragment)
            .setTitle(R.string.hello_world)
            .setActionBarMenuId(R.menu.menu_main);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((TextView) view.findViewById(R.id.text)).setText(someText);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_type:
                startFragmentForResult(this, new TypeAndReturnFragment(), TYPE_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == TYPE_REQUEST_CODE){
            someText = data.getStringExtra(Constant.DATA_STRING);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
