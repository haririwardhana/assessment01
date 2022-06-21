package org.d3if0044.assessment1_hitungbahanbakar.ui.listdata

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if0044.assessment1_hitungbahanbakar.R
import org.d3if0044.assessment1_hitungbahanbakar.database.DbBahanBakar
import org.d3if0044.assessment1_hitungbahanbakar.database.Enitity
import org.d3if0044.assessment1_hitungbahanbakar.databinding.FragmentListdataBinding
import java.text.FieldPosition

class   listFragment : Fragment() {

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
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.hapus_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus){
            hapusSemua()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun hapusSemua(){
        MaterialAlertDialogBuilder(requireContext()).setMessage(R.string.konfirmasi_hapus)
            .setPositiveButton(getString(R.string.hapus_list)) {_, _ -> viewModel.hapusSemua()
            }.setNegativeButton(getString(R.string.batal)) { dialog, _ -> dialog.cancel()}.show()
    }
}