package com.flatstack.singleactivityapp.fragments;

import android.support.v4.app.Fragment;

import com.flatstack.singleactivityapp.utils.Bus;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public class ReplaceFragment {

    private final Fragment f;

    private boolean addToBackStack, clearBackStack;

    private ReplaceFragment(Fragment f){
        this.f = f;
    }

    public static ReplaceFragment with(Fragment f){
        return new ReplaceFragment(f);
    }

    public ReplaceFragment addToBackStack(){
        this.addToBackStack = true;
        return this;
    }

    public ReplaceFragment clearBackStack(){
        this.clearBackStack = true;
        return this;
    }

    public boolean isAddToBackStack(){
        return addToBackStack;
    }

    public boolean isClearBackStack(){
        return clearBackStack;
    }

    public Fragment getFragment(){
        return f;
    }

    public void go(){
        Bus.event(this);
    }

}
