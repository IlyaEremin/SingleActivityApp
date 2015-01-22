package com.flatstack.singleactivityapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flatstack.singleactivityapp.R;
import com.flatstack.singleactivityapp.utils.Constant;

/**
 * Created by IlyaEremin on 16/01/15.
 */
public class TypeAndReturnFragment extends BaseFragment {

    EditText editText;
    TextView okBtn;

    @NonNull @Override protected FragmentInfo getFragmentInfo() {
        return new FragmentInfo(R.layout.type_and_return_screen).setHomeBtn(HomeButtonAction.BACK).setTitle(R.string.type_me);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        editText = (EditText) view.findViewById(R.id.edit_text);
        okBtn = (TextView) view.findViewById(R.id.ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Constant.DATA_STRING, editText.getText().toString());
                setResult(intent, Activity.RESULT_OK);
                getActivity().onBackPressed();
            }
        });
    }
}
