package com.socialseller.ceo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.socialseller.ceo.R
import com.socialseller.ceo.adapter.TabPagerAdapter
import com.socialseller.ceo.databinding.FragmentHomeBinding
import com.socialseller.ceo.databinding.FragmentOtpBinding
import com.socialseller.ceo.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val tabTitles = listOf("Followup", "Prospects", "On Hold", "Quotation Shared",
        "Project Confirmed", "Project Completed", "Lost")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup ViewPager2 and TabLayout
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = TabPagerAdapter(this, tabTitles)

        // Connect TabLayout with ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null)
            val tabText = tabView.findViewById<TextView>(R.id.tab_text)
            tabText.text = tabTitles[position]

            if (position == 0) {
                tabText.setBackgroundResource(R.drawable.tab_selected_background)
                tabText.setTextColor(resources.getColor(R.color.appColor, null))
            } else {
                tabText.setBackgroundResource(R.drawable.tab_unselected_background)
                tabText.setTextColor(resources.getColor(R.color.white, null))
            }
            tab.customView = tabView
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.tab_text)?.apply {
                    setBackgroundResource(R.drawable.tab_selected_background)
                    setTextColor(resources.getColor(R.color.appColor, null))
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.tab_text)?.apply {
                    setBackgroundResource(R.drawable.tab_unselected_background)
                    setTextColor(resources.getColor(R.color.white, null))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Optional: Set tab mode to scrollable if many tabs
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
    }

}