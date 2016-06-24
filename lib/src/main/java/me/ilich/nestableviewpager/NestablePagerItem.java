package me.ilich.nestableviewpager;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

public interface NestablePagerItem {

    int[] getOptionsMenuIds();

    @Nullable
    ViewPager getNestedViewPager();

}
