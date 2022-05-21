package org.d3if0044.assessment1_hitungbahanbakar.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0044.assessment1_hitungbahanbakar.R
import org.d3if0044.assessment1_hitungbahanbakar.database.DbBahanBakar
import org.d3if0044.assessment1_hitungbahanbakar.databinding.FragmentHitungBinding
import org.d3if0044.assessment1_hitungbahanbakar.model.HasilHitung
import org.d3if0044.assessment1_hitungbahanbakar.model.KategoriBB

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding


    private val viewModel: MainViewModel by lazy {
        val db = DbBahanBakar.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonHitung.setOnClickListener { penghitungan() }
        binding.buttonClear.setOnClickListener {
            binding.hasilJarak.text = ""
            binding.hasilJumlah.text = ""
            binding.kategoriView.text = ""
            binding.inputJarakAwal.text = null
            binding.inputJarakAkhir.text = null
            binding.inputBensin.text = null
            binding.buttonGroup.visibility = View.GONE
        }
        binding.buttonBagi.setOnClickListener { bagiData() }
        viewModel.getHasilHitung().observe(requireActivity(), {tampilTotal(it) })
        viewModel.data.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Log.d("HitungFragment", "Data tersimpan. ID = ${it.id}")
        })
    }

    private fun bagiData(){
        val message = getString(R.string.bagikan_template,
            binding.hasilJumlah.text,
            binding.hasilJarak.text,
            binding.kategoriView.text)
        val bagiIntent = Intent(Intent.ACTION_SEND)
        bagiIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT,message)
        if (bagiIntent.resolveActivity(requireActivity().packageManager)!= null){
            startActivity(bagiIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.desc_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_desc){
            findNavController().navigate(R.id.action_hitungFragment_to_penjelasanFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun penghitungan(){
        val awal = binding.inputJarakAwal.text.toString()
        if (TextUtils.isEmpty(awal)){
            Toast.makeText(context,"Lengkapi data.",Toast.LENGTH_LONG).show()
            return
        }
        val akhir = binding.inputJarakAkhir.text.toString()
        if (TextUtils.isEmpty(akhir)){
            Toast.makeText(context,"Lengkapi data.",Toast.LENGTH_LONG).show()
            return
        }
        val jumlah = binding.inputBensin.text.toString()
        if (TextUtils.isEmpty(jumlah)){
            Toast.makeText(context,"Lengkapi data.",Toast.LENGTH_LONG).show()
            return
        }
        if (awal.toFloat() > akhir.toFloat()) {
            Toast.makeText(context, "Jarak tidak valid", Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungTotal(awal.toFloat(),akhir.toFloat(),jumlah.toFloat())
    }
    private fun tampilTotal(result: HasilHitung?){
        if (result == null) return
        binding.hasilJumlah.text = getString(R.string.hasil_jumlah, result.total)
        binding.kategoriView.text = getString(R.string.kategori, kategoriLabel(result.kategori))
        binding.hasilJarak.text = getString(R.string.hasil_jarak, result.jarak)
        binding.buttonGroup.visibility = View.VISIBLE
    }


    private fun kategoriLabel(kategori: KategoriBB): String{
        val stringRes = when (kategori) {
            KategoriBB.SEDANG -> R.string.sedang
            KategoriBB.IRIT -> R.string.irit
            KategoriBB.BOROS -> R.string.boros
        }
        return getString(stringRes)
    }
}