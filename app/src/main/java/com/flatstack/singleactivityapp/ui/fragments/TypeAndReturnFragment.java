package com.flatstack.singleactivityapp.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.flatstack.singleactivityapp.R;
import com.flatstack.singleactivityapp.utils.Constants;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by IlyaEremin on 16/01/15.
 */
public class TypeAndReturnFragment extends BaseFragment {

    @Bind(R.id.edit_text) EditText editText;

    @NonNull @Override protected FragmentInfo getFragmentInfo() {
        return new FragmentInfo(R.layout.type_and_return_screen)
            .setTitle(R.string.type_me)
            .setHomeBtn(HomeButtonAction.BACK)
            ;
    }

    @OnClick(R.id.ok) void onOkClick(){
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA_STRING, textOf(editText));
        setResult(intent, Activity.RESULT_OK);
    }
}
