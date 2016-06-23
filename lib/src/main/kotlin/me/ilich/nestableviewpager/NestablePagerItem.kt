package me.ilich.nestableviewpager

import android.support.v4.view.ViewPager

interface NestablePagerItem {

    fun getOptionsMenuIds(): IntArray

    fun getNestedViewPager(): ViewPager?

}
