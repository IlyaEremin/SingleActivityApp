package com.flatstack.singleactivityapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.flatstack.singleactivityapp.R;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public class InitialFragment extends BaseFragment {

    @NonNull @Override protected FragmentInfo getFragmentInfo() {
        return new FragmentInfo(R.layout.initial_fragment)
            .setTitle(R.string.hello_world)
            .setActionBarMenuId(R.menu.menu_main);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("initial fragment aha");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                ReplaceFragment.with(new DetailsFragment()).addToBackStack().go();
            }
        });
    }
}
