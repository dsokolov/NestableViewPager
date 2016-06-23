package me.ilich.nestableviewpager.usage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.ilich.nestableviewpager.NestablePagerItem

class MenuInnerFragment : Fragment(), NestablePagerItem {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun getNestedViewPager(): ViewPager? = null

    override fun getOptionsMenuIds(): IntArray = intArrayOf(R.menu.inner)

    companion object {
        fun create(): MenuInnerFragment = MenuInnerFragment()
    }

}
