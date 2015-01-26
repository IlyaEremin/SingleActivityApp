package com.flatstack.singleactivityapp.fragments;

import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;

import com.flatstack.singleactivityapp.R;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* Created by IlyaEremin on 14/01/15.
*/
@Accessors(chain = true)
class FragmentInfo {
    @Getter @Setter private @StringRes int title = R.string.empty;
    @Getter @Setter private @LayoutRes int layoutId;
    @Getter @Setter private @MenuRes   int actionBarMenuId = R.menu.empty;
    @Getter @Setter private HomeButtonAction homeBtn = HomeButtonAction.ICON;

    public FragmentInfo(@LayoutRes int layoutId){
        this.layoutId = layoutId;
    }

}
