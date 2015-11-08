package com.flatstack.singleactivityapp.ui.fragments;

import android.support.v4.app.Fragment;

import com.flatstack.singleactivityapp.utils.Bus;

import lombok.Getter;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public class FragmentTransactionEvent {

    public static FragmentTransactionEvent with(Fragment fragment) {
        return new FragmentTransactionEvent(fragment);
    }

    public static FragmentTransactionEvent withAnimAndBackstack(Fragment fragment) {
        return with(fragment).addToBackStack().withAnimation();
    }

    public FragmentTransactionEvent withAnimation() {
        this.needAnimation = true;
        return this;
    }

    public FragmentTransactionEvent addToBackStack() {
        this.addToBackStack = true;
        return this;
    }

    public FragmentTransactionEvent clearBackStack() {
        this.clearBackStack = true;
        return this;
    }

    public FragmentTransactionEvent closeDrawer() {
        this.closeNavigationDrawer = true;
        return this;
    }

    private FragmentTransactionEvent(Fragment fragment) {
        this.fragment = fragment;
    }

    @Getter private final Fragment fragment;
    @Getter
    private               boolean  needAnimation, addToBackStack, clearBackStack, closeNavigationDrawer;

    public void emit(){
        Bus.event(this);
    }

}
