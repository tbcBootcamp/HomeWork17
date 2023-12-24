package com.example.hw17.presentation.fragments.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hw17.helpers.base.BaseFragment
import com.example.hw17.databinding.FragmentHomeBinding
import com.example.hw17.helpers.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    private val args: HomeFragmentArgs by navArgs()

    override fun listeners() {
        binding.btnLogOut.setOnClickListener {
            logout()
        }
    }

    override fun observers() {
        if (args.email != null) {
            binding.tvEmail.text = args.email
        } else {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.readEmail().collect {
                        it?.let {
                            binding.tvEmail.text = it
                        }
                    }
                }
            }
        }
    }

    private fun logout() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.remove(Constants.KEY_TOKEN) }.join()
                launch { viewModel.remove(Constants.KEY_EMAIL) }.join()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWelcomeFragment())
            }
        }
    }
}