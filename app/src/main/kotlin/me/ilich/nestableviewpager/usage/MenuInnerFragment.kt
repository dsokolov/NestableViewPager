package me.ilich.nestableviewpager.usage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.Toast
import me.ilich.nestableviewpager.NestablePagerItem

class MenuInnerFragment : Fragment(), NestablePagerItem {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.inner, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId==R.id.menu_inner_1){
            Toast.makeText(context, "inner 1", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getNestedViewPager(): ViewPager? = null

    companion object {
        fun create(): MenuInnerFragment = MenuInnerFragment()
    }

}
