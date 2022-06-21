package org.d3if0044.assessment1_hitungbahanbakar.ui.listkonsumsi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0044.assessment1_hitungbahanbakar.R
import org.d3if0044.assessment1_hitungbahanbakar.databinding.FragmentKonsumsikendaraanBinding
import org.d3if0044.assessment1_hitungbahanbakar.databinding.FragmentListkonsumsiBinding
import org.d3if0044.assessment1_hitungbahanbakar.network.ApiStatus

class DataKonsumsiFragment : Fragment() {

    private val viewModel: DataKonsumsiViewModel by lazy {
        ViewModelProvider(this).get(DataKonsumsiViewModel::class.java)
    }

    private lateinit var binding: FragmentListkonsumsiBinding
    private lateinit var myAdapter: DataKonsumsiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListkonsumsiBinding.inflate(layoutInflater, container,false)
        myAdapter = DataKonsumsiAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })
    }

    private fun updateProgress(status: ApiStatus) {
        when (status){
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.internetErorr.visibility = View.VISIBLE
            }
        }
    }

}