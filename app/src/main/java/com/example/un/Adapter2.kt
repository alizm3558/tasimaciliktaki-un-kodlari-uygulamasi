package com.example.un

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.un.databinding.RecylerRowBinding
import com.example.un.databinding.RowdetailsBinding

class Adapter2(val kodListesi:ArrayList<String>,val isimListsi:ArrayList<String>): RecyclerView.Adapter<Adapter2.VeriHolder>() {
    private val colors:Array<String> = arrayOf("#f8f8ff","#f2f2f2")
    class VeriHolder(val binding: RowdetailsBinding): RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter2.VeriHolder {

              val binding=RowdetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VeriHolder(binding)
    }

    override fun onBindViewHolder(holder: Adapter2.VeriHolder, position: Int) {
        holder.binding.detailsRow.setBackgroundColor(Color.parseColor(colors[position%2]))

        holder.binding.unText.text=kodListesi.get(position)
        if(isimListsi.get(position).toString()!="null"  ){
            holder.binding.isimText.text=isimListsi.get(position)
        }
        else{
            holder.binding.isimText.text="-----"
        }






    }

    override fun getItemCount(): Int {
        return kodListesi.count()
    }


}