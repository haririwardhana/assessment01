package org.d3if0044.assessment1_hitungbahanbakar.ui.listdata

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if0044.assessment1_hitungbahanbakar.R
import org.d3if0044.assessment1_hitungbahanbakar.database.DbDao
import org.d3if0044.assessment1_hitungbahanbakar.database.Enitity
import org.d3if0044.assessment1_hitungbahanbakar.databinding.ItemListdataBinding
import org.d3if0044.assessment1_hitungbahanbakar.model.KategoriBB
import org.d3if0044.assessment1_hitungbahanbakar.model.hitungTotal
import java.text.SimpleDateFormat
import java.util.*

class listAdapter : ListAdapter<Enitity, listAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Enitity>(){
            override fun areItemsTheSame(oldData: Enitity, newData: Enitity): Boolean {
                return oldData.id == newData.id
            }

            override fun areContentsTheSame(oldData: Enitity, newData: Enitity): Boolean {
                return oldData == newData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListdataBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
            
    }


    class ViewHolder(
        private val binding: ItemListdataBinding
    ): RecyclerView.ViewHolder(binding.root){
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        fun bind(item: Enitity) = with(binding){
            val hasilHitung = item.hitungTotal()
            viewKategori.text = hasilHitung.kategori.toString().substring(0, 1)
            val colorRes = when(hasilHitung.kategori){
                KategoriBB.IRIT -> R.color.irit
                KategoriBB.SEDANG -> R.color.sedang
                KategoriBB.BOROS -> R.color.boros
            }
            val circleBg = viewKategori.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))

            viewTanggal.text = dateFormatter.format(Date(item.tanggal))
            viewHasil.text = root.context.getString(R.string.hasil_hitung,hasilHitung.total, hasilHitung.kategori)
            val jenisK = root.context.getString(
                if (item.jenis) R.string.mobil else R.string.motor)
            viewDetail.text = root.context.getString(R.string.detail_hitung, hasilHitung.jarak, item.bensin, jenisK)
        }
    }
}