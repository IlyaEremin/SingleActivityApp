package com.flatstack.singleactivityapp.fragments;

import android.support.annotation.NonNull;

import com.flatstack.singleactivityapp.R;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public class DetailsFragment extends BaseFragment {

    @NonNull @Override protected FragmentInfo getFragmentInfo() {
        return new FragmentInfo(R.layout.details_fragment).setTitle(R.string.details_fragment).setHomeBtn(HomeButtonAction.BACK);
    }
}
