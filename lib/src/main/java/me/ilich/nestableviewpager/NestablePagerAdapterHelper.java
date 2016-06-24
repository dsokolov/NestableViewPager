package me.ilich.nestableviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
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
        FragmentPagerAdapter adapter = (FragmentPagerAdapter) viewPager.getAdapter();
        Fragment f = adapter.getItem(currentPageIndex);
        if (f instanceof NestablePagerItem) {
            int[] items = ((NestablePagerItem) f).getOptionsMenuIds();
            for (int item : items) {
                menuIdList.add(item);
            }
            ViewPager nestedViewPager = ((NestablePagerItem) f).getNestedViewPager();
            if (nestedViewPager != null) {
                fillIdList(nestedViewPager, menuIdList);
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
