package com.flatstack.singleactivityapp.fragments;

import android.os.Parcel;
import android.os.Parcelable;
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
class FragmentInfo implements Parcelable {
    @Getter @Setter private @StringRes int title = R.string.empty;
    @Getter @Setter private @LayoutRes int layoutId;
    @Getter @Setter private @MenuRes   int actionBarMenuId = R.menu.empty;
    @Getter @Setter private HomeButtonAction homeBtn = HomeButtonAction.ICON;

    public FragmentInfo(@LayoutRes int layoutId){
        this.layoutId = layoutId;
    }


    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.title);
        dest.writeInt(this.layoutId);
        dest.writeInt(this.actionBarMenuId);
        dest.writeInt(this.homeBtn == null ? -1 : this.homeBtn.ordinal());
    }

    private FragmentInfo(Parcel in) {
        this.title = in.readInt();
        this.layoutId = in.readInt();
        this.actionBarMenuId = in.readInt();
        int tmpHomeBtn = in.readInt();
        this.homeBtn = tmpHomeBtn == -1 ? null : HomeButtonAction.values()[tmpHomeBtn];
    }

    public static final Creator<FragmentInfo> CREATOR = new Creator<FragmentInfo>() {
        public FragmentInfo createFromParcel(Parcel source) {
            return new FragmentInfo(source);
        }

        public FragmentInfo[] newArray(int size) {
            return new FragmentInfo[size];
        }
    };
}
