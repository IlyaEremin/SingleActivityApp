package com.flatstack.singleactivityapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public abstract class BaseFragment extends Fragment {

    private static final String FRAGMENT_INFO_KEY = "fragmentInfoKey";

    protected FragmentInfo fragmentInfo;

    protected abstract @NonNull FragmentInfo getFragmentInfo();

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // here we can use some fragment arguments injector, for example fragmentargs
        if (savedInstanceState == null) {
            fragmentInfo = getFragmentInfo();
        } else {
            fragmentInfo = (FragmentInfo) savedInstanceState.getSerializable(FRAGMENT_INFO_KEY);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // here we can call butterknife inject
        return inflater.inflate(fragmentInfo.getLayoutId(), container, false);
    }

    @Override public void onResume() {
        super.onResume();
        getActivity().setTitle(fragmentInfo.getTitle());
        // set action bar home icon and behaviour
        ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(fragmentInfo.getHomeBtn() == HomeButtonAction.BACK);
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(fragmentInfo.getActionBarMenuId(), menu);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(fragmentInfo != null){
            outState.putSerializable(FRAGMENT_INFO_KEY, fragmentInfo);
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (fragmentInfo.getHomeBtn() == HomeButtonAction.BACK) {
                getActivity().onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void startFragmentForResult(Fragment current, Fragment newFragment, int requestCode) {
        newFragment.setTargetFragment(current, requestCode);
        ReplaceFragment.with(newFragment).addToBackStack().go();
    }

    /**
     * @param data passed in onActivityResult of target fragment
     * @param resultCode
     */
    public void setResult(Intent data, int resultCode) {
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, data);
    }

}
