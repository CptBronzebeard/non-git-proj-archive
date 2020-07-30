package com.ilin.simplecurrencyconverter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.lang.ref.WeakReference

class ViewPagerAdapter internal constructor(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val instantiatedFragments: SparseArray<WeakReference<Fragment>> = SparseArray()
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()
    override fun getCount(): Int = mFragmentList.size
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    internal fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        instantiatedFragments.put(position, WeakReference(fragment))
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        instantiatedFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }
}