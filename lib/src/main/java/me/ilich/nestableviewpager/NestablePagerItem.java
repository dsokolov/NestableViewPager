package me.ilich.nestableviewpager;

import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import org.jetbrains.annotations.NotNull;

public interface NestablePagerItem {

    @NotNull
    int[] getOptionsMenuIds();

    @Nullable
    ViewPager getNestedViewPager();

}
