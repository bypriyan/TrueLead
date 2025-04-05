package com.socialseller.ceo.ui.home.fillterList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socialseller.ceo.R
import com.socialseller.ceo.databinding.FragmentFilterListBinding

class FilterListFragment : Fragment(R.layout.fragment_filter_list) {

    private var _binding: FragmentFilterListBinding? = null
    private val binding get() = _binding!!
    private var filterType: String? = null

    companion object {
        private const val ARG_FILTER_TYPE = "filter_type"

        fun newInstance(filterType: String): FilterListFragment {
            return FilterListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FILTER_TYPE, filterType)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilterListBinding.bind(view)

        filterType = arguments?.getString(ARG_FILTER_TYPE)

        // Load data based on filterType
        loadData()
    }

    private fun loadData() {
        when(filterType) {
            "Followup" -> { /* Load followup data */ }
            "Prospects" -> { /* Load prospects data */ }
            // ... other cases
        }
    }
}