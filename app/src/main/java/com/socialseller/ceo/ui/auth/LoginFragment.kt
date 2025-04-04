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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.socialseller.ceo.R
import com.socialseller.ceo.databinding.FragmentLoginBinding
import com.socialseller.clothcrew.utility.KeyboardUtils
import androidx.navigation.fragment.findNavController
import com.socialseller.ceo.viewModel.AuthViewModel
import com.socialseller.clothcrew.utility.ResponceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeLoginResponce()
    }

    private fun setupUI() {
        setupPhoneNumberListener()
        binding.apply {
            requestOTPBtn.setOnClickListener { handleOtpRequest() }
        }
    }
    private fun observeLoginResponce() {
        viewLifecycleOwner.lifecycleScope.launch {
            authViewModel.loginUser.collectLatest { response ->
                ResponceHelper.handleApiResponse(
                    response,
                    onSuccess = {
                        toggleLoading(false)
                        val action = LoginFragmentDirections.actionLoginFragmentToOtpFragment(binding.phoneNumberET.text.toString())
                        findNavController().navigate(action)
                    },
                    onError = { errored ->
                        toggleLoading(false)
                    },
                    logTag = "Login"
                )
            }
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
        authViewModel.loginUser(phoneNumber)

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