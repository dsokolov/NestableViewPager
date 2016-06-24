package me.ilich.nestableviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

public class NestablePagerAdapterHelper {

    public static void onCreateOptionsMenu(Menu menu, ViewPager viewPager, MenuInflater menuInflater) {
        menu.clear();
        List<Integer> menuItemIds = new ArrayList<>();
        fillIdList(viewPager, menuItemIds);
        for (int menuItemId : menuItemIds) {
            menuInflater.inflate(menuItemId, menu);
        }
    }

    private static void fillIdList(ViewPager viewPager, List<Integer> menuIdList) {
        int currentPageIndex = viewPager.getCurrentItem();
        PagerAdapter adapter = viewPager.getAdapter();
        final Fragment fragment;
        if (adapter instanceof FragmentPagerAdapter) {
            fragment = ((FragmentPagerAdapter) adapter).getItem(currentPageIndex);
        } else if (adapter instanceof FragmentStatePagerAdapter) {
            fragment = ((FragmentStatePagerAdapter) adapter).getItem(currentPageIndex);
        } else {
            fragment = null;
        }
        if (fragment != null) {
            if (fragment instanceof NestablePagerItem) {
                int[] items = ((NestablePagerItem) fragment).getOptionsMenuIds();
                for (int item : items) {
                    menuIdList.add(item);
                }
                ViewPager nestedViewPager = ((NestablePagerItem) fragment).getNestedViewPager();
                if (nestedViewPager != null) {
                    fillIdList(nestedViewPager, menuIdList);
                }
            }
        }
    }

    public static void addListenerToPager(ViewPager pager, final AppCompatActivity activity) {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                activity.supportInvalidateOptionsMenu();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
