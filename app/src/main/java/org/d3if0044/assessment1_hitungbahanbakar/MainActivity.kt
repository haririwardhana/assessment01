package org.d3if0044.assessment1_hitungbahanbakar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if0044.assessment1_hitungbahanbakar.databinding.ActivityMainBinding
import org.d3if0044.assessment1_hitungbahanbakar.model.HasilHitung
import org.d3if0044.assessment1_hitungbahanbakar.model.KategoriBB
import org.d3if0044.assessment1_hitungbahanbakar.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonHitung.setOnClickListener { penghitungan() }
        binding.buttonClear.setOnClickListener {
            binding.hasilJarak.text = ""
            binding.hasilJumlah.text = ""
            binding.inputJarakAwal.text = null
            binding.inputJarakAkhir.text = null
            binding.inputBensin.text = null
        }
        viewModel.getHasilHitung().observe(this,{ tampilTotal(it)
        })
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
        viewModel.hitungTotal(awal.toFloat(),akhir.toFloat(),jumlah.toFloat())
    }
    private fun tampilTotal(result: HasilHitung?){
        if (result == null) return
        binding.hasilJumlah.text = getString(R.string.hasil_jumlah, result.total)
        binding.kategoriView.text = getString(R.string.kategori, kategoriLabel(result.kategori))
        binding.hasilJarak.text = getString(R.string.hasil_jarak, result.jarak)
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