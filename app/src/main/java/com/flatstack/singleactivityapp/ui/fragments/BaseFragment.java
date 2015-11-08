package com.flatstack.singleactivityapp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.f2prateek.dart.Dart;
import com.flatstack.singleactivityapp.R;
import com.flatstack.singleactivityapp.ui.activities.MainActivity;
import com.flatstack.singleactivityapp.utils.AndroidUtils;

import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by IlyaEremin on 14/01/15.
 */
public abstract class BaseFragment extends Fragment {

    protected FragmentInfo fragmentInfo;
    private boolean mFirstAttach = true;

    @Override public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        if (fragmentInfo.isNeedArgumentInject()) {
            Dart.inject(this, getArguments());
        }
        if (savedState != null) {
            restoreState(savedState);
        } else {
            firstFragmentLaunch();
        }
    }

    /**
     * called in {@link #onCreate(Bundle)} when savedState is null
     */
    protected void firstFragmentLaunch() {}

    protected void restoreState(Bundle savedState) {}

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentInfo = getFragmentInfo();
        if (mFirstAttach && fragmentInfo.isNeedDagerInject()) {
            inject();
            mFirstAttach = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        if (fragmentInfo.isShowActionBar()) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.root_fragment_layout, container, false);
            inflater.inflate(fragmentInfo.getLayoutId(), rootView);
            v = rootView;
        } else {
            v = inflater.inflate(fragmentInfo.getLayoutId(), container, false);
        }
        ButterKnife.bind(this, v);
        Toolbar toolbar = ButterKnife.findById(v, R.id.toolbar);
        if (toolbar != null) {
            activity().setUpActionBar(toolbar);
        }
        return v;
    }

    @NonNull protected <T> Observable<T> bind(@NonNull Observable<T> source) {
        return source.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    abstract protected FragmentInfo getFragmentInfo();

    @Override public void onResume() {
        super.onResume();
        if (fragmentInfo.getTitleId() != R.string.dont_change) {
            activity().setTitle(fragmentInfo.getTitleId());
        }
        if (fragmentInfo.getHomeBtn() != HomeButtonAction.DONT_CHANGE) {
            activity().setHomeBtn(fragmentInfo.getHomeBtn());
        }
        setHasOptionsMenu(true);
    }

    protected void setTitle(@StringRes int title) {
        activity().setTitle(title);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (fragmentInfo.getHomeBtn() == HomeButtonAction.BACK) {
                AndroidUtils.hideKeyboard(getActivity());
                getActivity().onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public String trim(TextView textView) {
        return textView.getText().toString().trim();
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(fragmentInfo.getActionBarMenuId(), menu);
    }

    public void inject() {
    }

    protected String textOf(TextView tv) {
        return tv.getText().toString();
    }

    protected boolean isEmpty(TextView tv) {
        return TextUtils.isEmpty(textOf(tv));
    }

    protected boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    protected int getColor(@ColorRes int colorRes) {
        return getResources().getColor(colorRes);
    }

    protected String[] getStringArray(@ArrayRes int arrayRes) {
        return getResources().getStringArray(arrayRes);
    }

    protected void closeCurrentFragment() {
        AndroidUtils.hideKeyboard(getActivity());
        getActivity().getFragmentManager().popBackStackImmediate();
    }

    public void finish(){
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    protected MainActivity activity() {
        return (MainActivity) getActivity();
    }

    protected Context context() {
        return getActivity();
    }
    public void startFragmentForResult(Fragment current, Fragment newFragment, int requestCode) {
        newFragment.setTargetFragment(current, requestCode);
        FragmentTransactionEvent.with(newFragment).addToBackStack().emit();
    }

    /**
     * @param data passed in onActivityResult of target fragment
     * @param resultCode
     */
    public void setResult(Intent data, int resultCode) {
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, data);
        finish();
    }

}
