package com.flatstack.singleactivityapp.fragments;

import android.support.v4.app.Fragment;

import com.flatstack.singleactivityapp.utils.Bus;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public class FragmentTransactionEvent {

    private final Fragment f;

    private boolean addToBackStack, clearBackStack;

    private FragmentTransactionEvent(Fragment f){
        this.f = f;
    }

    public static FragmentTransactionEvent with(Fragment f){
        return new FragmentTransactionEvent(f);
    }

    public FragmentTransactionEvent addToBackStack(){
        this.addToBackStack = true;
        return this;
    }

    public FragmentTransactionEvent clearBackStack(){
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

    public void emit(){
        Bus.event(this);
    }

}
