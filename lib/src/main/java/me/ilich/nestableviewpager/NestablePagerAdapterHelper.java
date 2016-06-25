package me.ilich.nestableviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class NestablePagerAdapterHelper {

    public static void onCreateOptionsMenu(Menu menu, ViewPager viewPager, MenuInflater menuInflater) {
        menu.clear();
        fillIdList(viewPager, menu, menuInflater);
    }

    private static void fillIdList(ViewPager viewPager, Menu menu, MenuInflater menuInflater) {
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
            fragment.onCreateOptionsMenu(menu, menuInflater);
            if (fragment instanceof NestablePagerItem) {
                ViewPager nestedViewPager = ((NestablePagerItem) fragment).getNestedViewPager();
                if (nestedViewPager != null) {
                    fillIdList(nestedViewPager, menu, menuInflater);
                }
            }
        }
    }

    public static boolean onOptionsItemSelected(MenuItem menuItem, ViewPager viewPager) {
        boolean b = false;
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
            b = fragment.onOptionsItemSelected(menuItem);
            if (!b && fragment instanceof NestablePagerItem) {
                ViewPager nestedViewPager = ((NestablePagerItem) fragment).getNestedViewPager();
                if (nestedViewPager != null) {
                    b = onOptionsItemSelected(menuItem, nestedViewPager);
                }
            }
        }
        return b;
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
