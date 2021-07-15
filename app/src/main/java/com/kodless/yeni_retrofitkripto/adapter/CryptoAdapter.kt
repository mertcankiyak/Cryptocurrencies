package com.kodless.yeni_retrofitkripto.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kodless.yeni_retrofitkripto.databinding.RowLayoutBinding
import com.kodless.yeni_retrofitkripto.model.CryptoModel

class CryptoAdapter(var cryptoListesi: ArrayList<CryptoModel>) : RecyclerView.Adapter<CryptoAdapter.PostHolder>() {


    class PostHolder(val binding : RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  PostHolder(binding)
    }
    var renkListem : ArrayList<String> = arrayListOf("#FDFAF6","#E4EFE7")
    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.textView.text = cryptoListesi.get(position).currency
        holder.binding.textView2.text = cryptoListesi.get(position).price
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.binding.root.context, cryptoListesi.get(position).currency+" Tıklandı",Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setBackgroundColor(Color.parseColor(renkListem.get(position %2)))

    }

    override fun getItemCount(): Int {
      return  cryptoListesi.size
    }
}