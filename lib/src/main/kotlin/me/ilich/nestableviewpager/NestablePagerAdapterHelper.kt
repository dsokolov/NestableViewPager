package me.ilich.nestableviewpager

import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import me.ilich.nestableviewpager.NestablePagerItem
import java.util.*

object NestablePagerAdapterHelper {

    fun onCreateOptionsMenu(menu: Menu, viewPager: ViewPager, menuInflater: MenuInflater) {
        menu.clear()
        val menuItemIds = ArrayList<Int>()
        fillIdList(viewPager, menuItemIds)
        for (menuItemId in menuItemIds) {
            menuInflater.inflate(menuItemId, menu)
        }
    }

    private fun fillIdList(viewPager: ViewPager, menuIdList: MutableList<Int>) {
        val currentPageIndex = viewPager.currentItem
        val adapter = viewPager.adapter as FragmentPagerAdapter
        val f = adapter.getItem(currentPageIndex)
        if (f is NestablePagerItem) {
            val items = f.getOptionsMenuIds()
            for (item in items) {
                menuIdList.add(item)
            }
            val nestedViewPager = f.getNestedViewPager()
            if (nestedViewPager != null) {
                fillIdList(nestedViewPager, menuIdList)
            }
        }
    }

    fun addListenerToPager(pager: ViewPager, activity: AppCompatActivity) {
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                activity.supportInvalidateOptionsMenu()
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

}
