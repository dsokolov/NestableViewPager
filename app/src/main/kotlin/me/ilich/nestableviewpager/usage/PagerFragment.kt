package me.ilich.nestableviewpager.usage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_pager.*
import me.ilich.nestableviewpager.NestablePagerAdapterHelper
import me.ilich.nestableviewpager.NestablePagerItem

class PagerFragment : Fragment(), NestablePagerItem {

    companion object {
        fun create(): PagerFragment = PagerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager.adapter = Adapter(childFragmentManager, listOf(
                NoMenuFragment.create(),
                MenuInnerFragment.create(),
                NoMenuFragment.create()
        ))
        NestablePagerAdapterHelper.addListenerToPager(pager, activity as AppCompatActivity)
        tabs.setupWithViewPager(pager)
    }

    override fun getNestedViewPager(): ViewPager? = pager

    override fun getOptionsMenuIds(): IntArray = intArrayOf()

}
