package com.socialseller.ceo.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.socialseller.ceo.R
import com.socialseller.ceo.databinding.FragmentLoginBinding
import com.socialseller.clothcrew.utility.KeyboardUtils
import com.truecaller.android.sdk.*
import com.truecaller.android.sdk.oAuth.TcOAuthCallback
import com.truecaller.android.sdk.oAuth.TcOAuthData
import com.truecaller.android.sdk.oAuth.TcOAuthError
import com.truecaller.android.sdk.oAuth.TcSdk

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    //axvvvcvsy9yq9o0u9a89xryygdsehq6slpetfi2n8l8

    private val tcOAuthCallback: TcOAuthCallback = object : TcOAuthCallback {
        override fun onSuccess(tcOAuthData: TcOAuthData) {
            // Fetch the authorization code
            val authCode = tcOAuthData.authorizationCode
            val scopesGranted = tcOAuthData.scopesGranted
            val state = tcOAuthData.state
            Toast.makeText(requireContext(), "Truecaller Verified! Code: $authCode", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationRequired(tcOAuthError: TcOAuthError?) {
            TODO("Not yet implemented")
        }

        override fun onFailure(tcOAuthError: TcOAuthError) {
            Toast.makeText(requireContext(), "Truecaller Error: ${tcOAuthError.errorMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupPhoneNumberListener()
        initializeTruecallerOAuth()
        binding.requestOTPBtn.setOnClickListener { handleOtpRequest() }
        KeyboardUtils.showKeyboard(requireContext(), binding.phoneNumberET)
    }

    private fun initializeTruecallerOAuth() {
        // Check if Truecaller OAuth flow is usable
        if (TcSdk.getInstance().isOAuthFlowUsable) {
            binding.truecallerLoginBtn.visibility = View.VISIBLE
        } else {
            binding.truecallerLoginBtn.visibility = View.GONE
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
                if (isValid) KeyboardUtils.hideKeyboard(requireContext(), binding.phoneNumberET)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun handleOtpRequest() {
        val phoneNumber = binding.phoneNumberET.text?.toString()?.trim()
        if (phoneNumber.isNullOrEmpty() || phoneNumber.length != 10) {
            Toast.makeText(requireContext(), "Please enter a valid mobile number", Toast.LENGTH_SHORT).show()
            return
        }

        KeyboardUtils.hideKeyboard(requireContext(), binding.phoneNumberET)
        toggleLoading(true)
    }

    private fun toggleLoading(isLoading: Boolean) {
        binding.apply {
            progressbar.isVisible = isLoading
            requestOTPBtn.isVisible = !isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}
