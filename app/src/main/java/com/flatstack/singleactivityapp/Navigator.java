package com.flatstack.singleactivityapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.flatstack.singleactivityapp.utils.AndroidUtils;

import java.lang.ref.WeakReference;

/**
 * Created by Ilya Eremin on 29.01.2016.
 */
public class Navigator {

    public static int ADD_TO_BACKSTACK = 1;
    public static int WITH_ANIMATION   = 2;
    public static int CLEAR_BACK_STACK = 4;

    public static void openAppPageOnGooglePlay(Context context) {
        String appPackage = context.getPackageName();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackage));
        context.startActivity(intent);
    }

    private static void sendMail(Context context, String email, String subject, String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        try {
            context.startActivity(Intent.createChooser(emailIntent, "Email label"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "An error occured", Toast.LENGTH_LONG).show();
        }
    }

    public static void openInBrowser(Context context, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    public static void showScreen(Fragment fragment) {
        showScreen(fragment, 0, null);
    }

    public static void showScreen(Fragment fragment, int flags) {
        showScreen(fragment, flags, null);
    }

    public static void showScreen(Fragment fragment, int flags, @Nullable String tag) {
        MainActivity activity = getActivity();
        if (activity == null) return;
        if (needClearBackstack(flags)) {
            clearBackStack(activity);
        }
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        if (needAnimation(flags)) {
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        }
        if (addToBackStack(flags)) {
            ft.addToBackStack(null);
        }
        if (tag == null) {
            ft.replace(R.id.content_frame, fragment);
        } else {
            ft.replace(R.id.content_frame, fragment, tag);
        }
        ft.commitAllowingStateLoss();
        AndroidUtils.hideKeyboard(activity);
    }

    private static boolean addToBackStack(int flags) {
        return (flags & ADD_TO_BACKSTACK) > 0;
    }

    private static boolean needAnimation(int flags) {
        return (flags & WITH_ANIMATION) > 0;
    }

    private static boolean needClearBackstack(int flags) {
        return (flags & CLEAR_BACK_STACK) > 0;
    }

    private static void clearBackStack(AppCompatActivity activity) {
        activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private static WeakReference<MainActivity> activityRef;

    public static void initialize(MainActivity activity) {
        activityRef = new WeakReference<>(activity);
    }

    @Nullable private static MainActivity getActivity() {
        return activityRef == null ? null : activityRef.get();
    }

}
