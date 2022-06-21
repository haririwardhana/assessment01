package org.d3if0044.assessment1_hitungbahanbakar.ui.listkonsumsi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0044.assessment1_hitungbahanbakar.R
import org.d3if0044.assessment1_hitungbahanbakar.databinding.FragmentKonsumsikendaraanBinding
import org.d3if0044.assessment1_hitungbahanbakar.model.DataKonsumsi
import org.d3if0044.assessment1_hitungbahanbakar.network.dataApi

    class DataKonsumsiAdapter : RecyclerView.Adapter<DataKonsumsiAdapter.ViewHolder>() {

    private val data = mutableListOf<DataKonsumsi>()

    fun updateData(newData: List<DataKonsumsi>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentKonsumsikendaraanBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

        override fun getItemCount(): Int {
            return data.size
        }

    class ViewHolder (
        private val binding: FragmentKonsumsikendaraanBinding
        ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rata: DataKonsumsi) = with(binding){
            namaTextView.text = rata.nama
            hasilTextView.text = rata.hasil
            Glide.with(imageView.context)
                .load(dataApi.getDataUrl(rata.logoId))
                .error(R.drawable.ic_baseline_broken_image_24).into(imageView)
        }

    }
}