package me.ilich.nestableviewpager.usage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class Adapter(fm: FragmentManager, val pages: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = pages[position]

    override fun getPageTitle(position: Int): CharSequence? = position.toString()

    override fun getCount(): Int = pages.size

}