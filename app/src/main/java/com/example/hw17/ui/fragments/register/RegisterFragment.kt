package com.example.hw17.ui.fragments.register

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.hw17.helpers.Constants
import com.example.hw17.base.BaseFragment
import com.example.hw17.databinding.FragmentRegisterBinding
import com.example.hw17.helpers.toast
import kotlinx.coroutines.launch


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun listeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                when {
                    isEmptyField() -> {
                        toast("Please fill the fields")
                    }

                    !isValidEmail() -> {
                        toast("Please enter the valid email")
                    }

                    isValidPassword() -> {
                        toast("password must contain at least 8 symbols")
                    }

                    !passwordsMatch() -> {
                        toast("passwords do not match")
                    }

                    else -> {
                        viewModel.register(
                            email = binding.etEmail.text.toString(),
                            password = binding.etPassword.text.toString()
                        )
                    }
                }
            }
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect {
                    it.data?.let {
                        toast("registered successfully")
                        fragmentResultApi()
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                    }
                    if (it.error.isNotEmpty()) {
                        toast(it.error)
                    }
                    binding.loader.isVisible = it.isLoading
                }
            }
        }
    }

    private fun isValidEmail(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()

    private fun isEmptyField(): Boolean = with(binding) {
        return@with binding.etEmail.text.toString().isEmpty() ||
                binding.etPassword.text.toString().isEmpty()
    }

    private fun isValidPassword(): Boolean {
        return binding.etPassword.text.toString().length < 8
    }

    private fun passwordsMatch(): Boolean = with(binding) {
        return@with binding.etPassword.text.toString() == binding.etRepeatPassword.text.toString()
    }

    private fun fragmentResultApi() {
        val emailInput = binding.etEmail.text.toString()
        val passwordInput = binding.etPassword.text.toString()

        setFragmentResult(
            Constants.REQUEST_KEY,
            bundleOf(
                Constants.BUNDLE_KEY to emailInput,
                Constants.BUNDLE_KEY_PAS to passwordInput
            )
        )
    }
}