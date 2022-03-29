package org.d3if0044.assessment1_hitungbahanbakar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if0044.assessment1_hitungbahanbakar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonHitung.setOnClickListener { penghitungan() }
        binding.buttonClear.setOnClickListener {
            binding.hasilKategori.text = ""
            binding.hasilJumlah.text = ""
            binding.inputJarakAwal.text = null
            binding.inputJarakAkhir.text = null
            binding.inputBensin.text = null
        }

    }

    private fun penghitungan(){
        val awal = binding.inputJarakAwal.text.toString()
        if (TextUtils.isEmpty(awal)){
            Toast.makeText(this,"Lengkapi data.",Toast.LENGTH_LONG).show()
            return
        }
        val akhir = binding.inputJarakAkhir.text.toString()
        if (TextUtils.isEmpty(akhir)){
            Toast.makeText(this,"Lengkapi data.",Toast.LENGTH_LONG).show()
            return
        }
        val jumlah = binding.inputBensin.text.toString()
        if (TextUtils.isEmpty(jumlah)){
            Toast.makeText(this,"Lengkapi data.",Toast.LENGTH_LONG).show()
            return
        }
        if (awal.toFloat() > akhir.toFloat()) {
            Toast.makeText(this, "Jarak tidak valid", Toast.LENGTH_LONG).show()
            return
        }
        val total = (akhir.toFloat() - awal.toFloat()) / jumlah.toFloat()

        binding.hasilJumlah.text = getString(R.string.hasil_jumlah, total)
    }
}