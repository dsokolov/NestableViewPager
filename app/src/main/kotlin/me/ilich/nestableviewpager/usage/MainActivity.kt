package me.ilich.nestableviewpager.usage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import me.ilich.nestableviewpager.NestablePagerAdapterHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        pager.adapter = Adapter(supportFragmentManager, listOf(
                NoMenuFragment.create(),
                NoMenuFragment.create(),
                MenuOutterFragment.create(),
                PagerFragment.create(),
                NoMenuFragment.create()
        ))
        NestablePagerAdapterHelper.addListenerToPager(pager, this)
        tabs.setupWithViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        NestablePagerAdapterHelper.onCreateOptionsMenu(menu, pager, menuInflater)
        return super.onCreateOptionsMenu(menu)
    }

}
