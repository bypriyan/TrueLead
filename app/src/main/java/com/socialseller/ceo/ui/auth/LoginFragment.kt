package com.socialseller.ceo.ui.auth

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.socialseller.ceo.R
import com.socialseller.ceo.databinding.FragmentLoginBinding
import com.socialseller.clothcrew.utility.KeyboardUtils
import com.truecaller.android.sdk.oAuth.CodeVerifierUtil
import com.truecaller.android.sdk.oAuth.TcOAuthCallback
import com.truecaller.android.sdk.oAuth.TcOAuthData
import com.truecaller.android.sdk.oAuth.TcOAuthError
import com.truecaller.android.sdk.oAuth.TcSdk
import com.truecaller.android.sdk.oAuth.TcSdkOptions
import java.math.BigInteger
import java.security.SecureRandom
import com.truecaller.android.sdk.*

import android.content.Intent
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupPhoneNumberListener()
        binding.apply {
            requestOTPBtn.setOnClickListener { handleOtpRequest() }
        }
    }

    private fun setupPhoneNumberListener() {
        binding.phoneNumberET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val isValid = s?.length == 10
                binding.requestOTPBtn.apply {
                    isEnabled = isValid
                    alpha = if (isValid) 1.0f else 0.5f
                }

                if (isValid) {
                    KeyboardUtils.hideKeyboard(requireContext(), binding.phoneNumberET)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun handleOtpRequest() {
        val phoneNumber = binding.phoneNumberET.text?.toString()?.trim()
        if (phoneNumber.isNullOrEmpty() || phoneNumber.length != 10) {
            Toast.makeText(
                requireContext(),
                "Please enter a valid 10-digit mobile number",
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
                requestOTPBtn.visibility = View.GONE
            }else{
                progressbar.visibility = View.GONE
                requestOTPBtn.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}