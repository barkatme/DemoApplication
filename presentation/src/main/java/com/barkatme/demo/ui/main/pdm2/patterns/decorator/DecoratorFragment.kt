package com.barkatme.demo.ui.main.pdm2.patterns.decorator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentDecoratorBinding
import com.barkatme.demo.ui.base.BaseFragment

class DecoratorFragment(private val layout: Int = R.layout.fragment_decorator) :
    BaseFragment(layout) {

    private val androidNotificationOnly by lazy {
        AndroidNotifier(
            EmptyNotifier(),
            requireContext()
        )
    }
    private val toastNotifier by lazy { ToastNotifier(EmptyNotifier(), requireContext()) }
    private val bothNotifiers by lazy { AndroidNotifier(toastNotifier, requireContext()) }

    private var _binding: FragmentDecoratorBinding? = null
    val binding: FragmentDecoratorBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.tvAndroidNotification.setOnClickListener {
            androidNotificationOnly.notify(binding.etNotification.text.toString())
        }
        binding.tvAndroidNotificationAndToast.setOnClickListener {
            bothNotifiers.notify(binding.etNotification.text.toString())
        }
        binding.tvToastOnly.setOnClickListener {
            toastNotifier.notify(binding.etNotification.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}