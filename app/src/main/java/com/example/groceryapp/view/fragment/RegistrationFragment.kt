package com.example.groceryapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.groceryapp.R
import com.example.groceryapp.databinding.FragmentRegistrationBinding
import com.example.groceryapp.viewmodel.RegistrationResult
import com.example.groceryapp.viewmodel.RegistrationViewModel

class RegistrationFragment : Fragment() {

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            val firstName = binding.edFirstName.text.toString()
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()
            val mobile = binding.edMobile.text.toString()

            viewModel.register(firstName, email, password, mobile)
        }

        viewModel.registrationResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RegistrationResult.Success -> {
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT)
                        .show()
                    // Navigate to login screen
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragment_container, LoginFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                is RegistrationResult.Error -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                is RegistrationResult.Loading -> {
                    // Show loading indicator
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, LoginFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
