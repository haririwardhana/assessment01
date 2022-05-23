package org.d3if0044.assessment1_hitungbahanbakar.ui.listdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0044.assessment1_hitungbahanbakar.database.DbBahanBakar
import org.d3if0044.assessment1_hitungbahanbakar.databinding.FragmentListdataBinding

class listFragment : Fragment() {

    private val viewModel: listViewModel by lazy {
        val db = DbBahanBakar.getInstance(requireContext())
        val factory = listViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[listViewModel::class.java]
    }

    private lateinit var binding: FragmentListdataBinding
    private lateinit var myAdapter: listAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListdataBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = listAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        viewModel.data.observe(viewLifecycleOwner, {
            binding.ViewKosong.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }
}