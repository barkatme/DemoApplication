package com.barkatme.demo.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FlowFragmentBinding
import kotlinx.android.synthetic.main.flow_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class FlowFragment : Fragment() {

    companion object {
        val TAG = FlowFragment::class.simpleName
    }

    private val viewModel: FlowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FlowFragmentBinding>(
            layoutInflater,
            R.layout.flow_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setSearchFlow(searchView.getQueryTextChangeStateFlow())
    }

    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
        val query = MutableStateFlow("")
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                query.value = newText
                return true
            }
        })
        return query
    }
}