package com.example.hw17.presentation.fragments.login

import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.hw17.helpers.base.BaseFragment
import com.example.hw17.databinding.FragmentLoginBinding
import com.example.hw17.helpers.Constants
import com.example.hw17.helpers.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()

    //private val viewModel: LoginViewModel by viewModels()


    override fun listeners() {
        setFragmentListener()

        binding.btnLogin.setOnClickListener {
            when {
                isEmptyField() -> {toast("Please fill the fields") }
                !isValidEmail() -> {toast("Please enter the valid email")}

                else -> {
                    viewModel.login(
                        email = binding.etEmail.text.toString(),
                        password = binding.etPassword.text.toString()
                    )
                }
            }
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect {
                    it.data?.let { loginResponseModel ->
                        if (binding.cbRememberMe.isChecked) {
                            viewModel.save(Constants.KEY_TOKEN, loginResponseModel.token.toString())
                            viewModel.save(Constants.KEY_EMAIL, binding.etEmail.text.toString())
                        }
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                                binding.etEmail.text.toString()
                            )
                        )
                    }
                    if (it.error.isNotEmpty()) {
                        toast(it.error)
                    }
                    binding.loader.isVisible = it.isLoading
                }
            }
        }
    }

    private fun setFragmentListener() {
        setFragmentResultListener(Constants.REQUEST_KEY) { _, bundle ->
            val resultEmail = bundle.getString(Constants.BUNDLE_KEY, "Default value")
            val resultPassword = bundle.getString(Constants.BUNDLE_KEY_PAS, "Default value")
            binding.etPassword.setText(resultPassword)
            binding.etEmail.setText(resultEmail)
        }
    }

    private fun isValidEmail(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()

    private fun isEmptyField(): Boolean = with(binding) {
        return@with binding.etEmail.text.toString().isEmpty() ||
                binding.etPassword.text.toString().isEmpty()
    }
}