package com.example.un

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceDataStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.un.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import android.text.Editable

import android.text.TextWatcher
import android.view.KeyEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var arr:Array<String>

    var gelenIsimDizisi= arrayListOf<String>()
    var gelenUnNoDizisi= arrayListOf<String>()
    var gelenBakanlikKodDizisi= arrayListOf<String>()
    var gelenSiniflandirmaKodu= arrayListOf<String>()
    var gelenPaketlemeKodu= arrayListOf<String>()

    private lateinit var veriList:ArrayList<VeriModel>
    private lateinit var adapter: Adapter


    var arr2= arrayListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var SharedPreferences=this.getSharedPreferences("un_izinleri", MODE_PRIVATE)
        var editor=SharedPreferences.edit()

        var izinDurumu:Boolean=SharedPreferences.getBoolean("veriDurumu",false)


        if (!izinDurumu){
            try {
                // daha önceden veri kaydedilmediyse LoadindScreen'a yönlendiriyor


                editor.putBoolean("veriDurumu",true).apply()



                val intent= Intent(this,LoadingScreen::class.java)
                startActivity(intent)



            }catch (ee : IOException){
                ee.printStackTrace()
                Toast.makeText(applicationContext, "Başaramadık!", Toast.LENGTH_SHORT).show()
            }

        }
        else{
            Toast.makeText(applicationContext, "Hoş geldiniz.", Toast.LENGTH_LONG).show()//Veri Durumu true geldi!

        }


        val context=this
        var db=DataBaseHelper(context)




        veriList= ArrayList<VeriModel>()


        // yedeklerdeki


        //binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= Adapter(veriList)
        binding.recyclerView.adapter=adapter

        sorgulama()
        adapter.notifyDataSetChanged()


        binding.editTextNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) { // edittext boş ise çalıştıracak

                runBlocking { launch {
                    if (binding.editTextNumber.length() == 0) {
                        //Toast.makeText(applicationContext, "deneme", Toast.LENGTH_SHORT).show()
                        veriList.clear()
                        sorgulama()
                        adapter.notifyDataSetChanged() // veriList yenilendiği zaman bu fonksiyonu çağırmalıyız. Adapteri güncelliyor
                    }
                }}


            }
        })



        binding.editTextNumber.setOnClickListener(){
            enterEvent()
        }




    }

    fun enterEvent(){
        binding.editTextNumber.setOnKeyListener(View.OnKeyListener{v,keyCode,event ->
            if(keyCode==KeyEvent.KEYCODE_ENTER && event.action==KeyEvent.ACTION_UP){
                val context=this
                var db=DataBaseHelper(context)
                var uzunluk=binding.editTextNumber.length()

                var str=binding.editTextNumber.text.toString()
                val ch: CharArray = str.toCharArray()
                val str2 = java.lang.String.valueOf(ch)



                if (db.kayitVarYok(str2) != 0) {


                    arama(str2)

                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(applicationContext, "Kayıt bulunamadı", Toast.LENGTH_SHORT)
                        .show()
                }


                return@OnKeyListener true
            }
            false
        })
    }



    fun openSearchScreen(view : View){
        val context = this
        var db = DataBaseHelper(context)



            var uzunluk = binding.editTextNumber.length()

            var str = binding.editTextNumber.text.toString()
            val ch: CharArray = str.toCharArray()
            val str2 = java.lang.String.valueOf(ch)



        runBlocking { launch {
            if (db.kayitVarYok(str2) != 0) {


                arama(str2)

                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Kayıt bulunamadı", Toast.LENGTH_SHORT)
                    .show()
            }


        } }



    }

    fun sorgulama(){ // çalışıyor. değerler dizilere ayrı olarak aktarılıyor.
        veriList.clear()
        val context=this
        var db=DataBaseHelper(context)


        var data=db.firstDatas() // return array

        for(i in 0 until data.size){ // gelen verileri dizilere ayırıyorum.
            gelenIsimDizisi.add(data.get(i).isim)
            gelenUnNoDizisi.add(data.get(i).un_no)
            gelenBakanlikKodDizisi.add(data.get(i).bakanlik_kod)
            gelenSiniflandirmaKodu.add(data.get(i).siniflandirma_kodu)
            gelenPaketlemeKodu.add(data.get(i).paketleme_grubu)

            var veriModel=VeriModel(data.get(i).un_no,data.get(i).isim,data.get(i).bakanlik_kod,data.get(i).siniflandirma_kodu,data.get(i).paketleme_grubu)
            veriList.add(veriModel)

        }


    }

    fun arama(gelenDeger:String){ // çalışıyor. değerler dizilere ayrı olarak aktarılıyor.


        veriList.clear()
        val context=this
        var db=DataBaseHelper(context)


            var data = db.searchDatas(gelenDeger) // return array

            for (i in 0 until data.size) { // gelen verileri dizilere ayırıyorum.
                gelenIsimDizisi.add(data.get(i).isim.toString())
                gelenUnNoDizisi.add(data.get(i).un_no.toString())
                gelenBakanlikKodDizisi.add(data.get(i).bakanlik_kod.toString())

                gelenSiniflandirmaKodu.add(data.get(i).siniflandirma_kodu)
                gelenPaketlemeKodu.add(data.get(i).paketleme_grubu)

                var veriModel = VeriModel(
                    data.get(i).un_no,
                    data.get(i).isim,
                    data.get(i).bakanlik_kod,
                    data.get(i).siniflandirma_kodu,
                    data.get(i).paketleme_grubu
                )
                veriList.add(veriModel)


            }



    }


 fun jsonParse(view:View){
 /*   val context=this
     var db=DataBaseHelper(context)

     try {
         db.jsonApiParse()


         Toast.makeText(applicationContext, "Güncelleme Başarılı.", Toast.LENGTH_SHORT).show()
     }catch (e:Exception){
         e.printStackTrace()
         println("Güncelleme başarısız!")
     }


*/


 }


    /* fun firstData(){
         try {


             val context = this
             val db = DataBaseHelper(context)

             var data=db.firstDatas()
             binding.tvSonuc.setText("")

             for(i in 0 until data.size){
                 binding.tvSonuc.append("İsim: "+data.get(i).un_no+" Yaş: "+data.get(i).isim+"\n")
             }
             Toast.makeText(applicationContext, "çalışıyor", Toast.LENGTH_SHORT).show()

         }catch (e : Exception){
             e.printStackTrace()
             Toast.makeText(applicationContext, "Hatalı3", Toast.LENGTH_SHORT).show()
         }
     }*/

    /* fun readAndSave_json(){ // çalışıyor
         var json:String?=null
         try {
             val  inputStream: InputStream =assets.open("un_kodlari.json")
             json=inputStream.bufferedReader().use{it.readText()}
             var jsonarr= JSONArray(json)

             for (i in 0..jsonarr.length()-1){ // satır satır arıyor
                 var jsonobj=jsonarr.getJSONObject(i)
                 //arr.add(jsonobj.getString("un_no"))
                 arr.add(jsonobj.getString("un_no"))
                 arr2.add(jsonobj.getString("isim"))
             }

             var  adpt= ArrayAdapter(this, R.layout.simple_list_item_1,arr)
             binding.listView.adapter=adpt

             Toast.makeText(applicationContext, "Veriler yüklendi.", Toast.LENGTH_SHORT).show()

         }catch (e: IOException){
             e.printStackTrace()
             Toast.makeText(applicationContext, "Başarısız olduk!", Toast.LENGTH_LONG).show()
         }
     }*/




}