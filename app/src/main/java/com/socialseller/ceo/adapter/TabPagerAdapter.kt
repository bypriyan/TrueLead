package com.socialseller.ceo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.socialseller.ceo.ui.home.fillterList.FilterListFragment

class TabPagerAdapter(
    fragment: Fragment,
    private val tabTitles: List<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = tabTitles.size

    override fun createFragment(position: Int): Fragment {
        return FilterListFragment.newInstance(tabTitles[position])
    }
}