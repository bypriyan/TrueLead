package com.socialseller.ceo.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.socialseller.ceo.R
import com.socialseller.ceo.databinding.FragmentOtpBinding
import com.socialseller.clothcrew.utility.KeyboardUtils

class OtpFragment : Fragment(R.layout.fragment_otp) {

    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val phoneNumber = arguments?.let { OtpFragmentArgs.fromBundle(it).phoneNumber }
        binding.desTv.setText("Enter OTP sent as SMS to +91 $phoneNumber")
        setupPhoneNumberListener()
        binding.apply {
            verifyOTPBtn.setOnClickListener { handleOtpRequest() }
        }
    }

    private fun setupPhoneNumberListener() {
        binding.otpET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val isValid = s?.length == 6
                binding.verifyOTPBtn.apply {
                    isEnabled = isValid
                    alpha = if (isValid) 1.0f else 0.5f
                }

                if (isValid) {
                    KeyboardUtils.hideKeyboard(requireContext(), binding.otpET)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun handleOtpRequest() {
        val phoneNumber = binding.otpET.text?.toString()?.trim()
        if (phoneNumber.isNullOrEmpty() || phoneNumber.length != 6) {
            Toast.makeText(
                requireContext(),
                "Please enter a valid OTP",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        toggleLoading(true)
        // Navigate to OtpFragment
        val action = LoginFragmentDirections.actionLoginFragmentToOtpFragment(phoneNumber)
        findNavController().navigate(action)
    }

    private fun toggleLoading(isLoading: Boolean) {
        binding.apply {
            if(isLoading){
                progressbar.visibility = View.VISIBLE
                verifyOTPBtn.visibility = View.GONE
            }else{
                progressbar.visibility = View.GONE
                verifyOTPBtn.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}