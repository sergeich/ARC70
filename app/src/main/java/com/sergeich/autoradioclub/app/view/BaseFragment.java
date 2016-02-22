package com.sergeich.autoradioclub.app.view;

import android.support.annotation.LayoutRes;

import io.reist.visum.presenter.VisumPresenter;
import io.reist.visum.view.VisumFragment;

public abstract class BaseFragment<P extends VisumPresenter> extends VisumFragment<P> {

    public BaseFragment(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    protected FragmentController getFragmentController() {
        Object a = getActivity();
        if (a instanceof FragmentController) {
            return (FragmentController) a;
        } else {
            throw new IllegalArgumentException("Can't find " + FragmentController.class.getSimpleName());
        }
    }

    public interface FragmentController {
        void showFragment(VisumFragment fragment, boolean remove);
    }

}
