package com.flatstack.singleactivityapp.ui.fragments;

import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;

import com.flatstack.singleactivityapp.R;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* Created by IlyaEremin on 14/01/15.
*/
@Data
@Accessors(chain = true)
class FragmentInfo {
    private @StringRes int title = R.string.dont_change;
    @Getter private final @LayoutRes int layoutId;
    private @MenuRes   int actionBarMenuId = R.menu.empty;

    public FragmentInfo(@LayoutRes int layoutId){
        this.layoutId = layoutId;
    }

    private @StringRes int titleId = R.string.dont_change;
    @Setter @Getter private          HomeButtonAction homeBtn         = HomeButtonAction.DONT_CHANGE;
    private boolean isNeedLeftMenu;
    private boolean isNeedDagerInject;
    private boolean showActionBar      = true;

    public FragmentInfo makeDependencyInject() {
        isNeedDagerInject = true;
        return this;
    }

    public FragmentInfo leftMenuAvailable() {
        isNeedLeftMenu = true;
        return this;
    }

    public FragmentInfo doNotAddActionBar() {
        showActionBar = false;
        return this;
    }

}
