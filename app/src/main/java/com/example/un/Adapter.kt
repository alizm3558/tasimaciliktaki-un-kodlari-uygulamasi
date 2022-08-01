package com.example.un

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.un.databinding.ActivityMainBinding
import com.example.un.databinding.RecylerRowBinding

class Adapter(val veriList:ArrayList<VeriModel>):RecyclerView.Adapter<Adapter.VeriHolder>() {
    private val colors:Array<String> = arrayOf("#f8f8ff","#f2f2f2")

    class VeriHolder(val binding: RecylerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VeriHolder {
        val binding=RecylerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VeriHolder(binding)
    }

    @SuppressLint("StringFormatInvalid")
    override fun onBindViewHolder(holder: VeriHolder, position: Int) {
        holder.binding.row.setBackgroundColor(Color.parseColor(colors[position%2]))


    holder.binding.unText.text=holder.itemView.context.getString(R.string.un, veriList.get(position).unKod)

        holder.binding.isimText.text=veriList.get(position).isim

       holder.binding.siniflandirmaText.text="Sınıflandırma Kodu: "+veriList.get(position).siniflandirmaKodu


        if(veriList.get(position).paketlemeKodu!="null"){
            holder.binding.paketlemeText.text="Paketleme Grubu: "+veriList.get(position).paketlemeKodu
        }
        else{
            holder.binding.paketlemeText.text="Paketleme Grubu: -----"
        }


        // listener verdik
        holder.itemView.setOnClickListener{
            val intent=Intent(holder.itemView.context,SearchScreen::class.java)
            intent.putExtra("bakanlik_kodu",veriList.get(position).bakanlikKod)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
       return veriList.count()
    }

}

/*private fun View.setOnClickListener(holder: Adapter.VeriHolder, position: Int) {
    val intent=Intent(holder.itemView.context,SearchScreen::class.java)
    intent.putExtra("Value",veriList.get(position).bakanlikKod)
    holder.itemView.context.startActivity(intent)
}*/
