package com.example.un

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class LoadingScreen : AppCompatActivity() {

    private var adr_ozel_hukumler:String=""
    private var adr_tank_kodu:String=""
    private var ambalaj_talimatlari:String=""
    private var bakanlik_kod:String=""
    private var etiketler:String=""
    private var isim:String=""
    private var karisik_ambalaj_hukumleri:String=""
    private var ozel_ambalaj_hukumleri:String=""
    private var ozel_hukumler:String=""
    private var paketleme_grubu:String=""
    private var portatif_ozel_hukumler:String=""
    private var portatif_talimatlar:String=""
    private var sinif:String=""
    private var siniflandirma_kodu:String=""
    private var sinirli_istisnai_miktarlar_7a:String=""
    private var sinirli_istisnai_miktarlar_7b:String=""
    private var tank_tasimasi:String=""
    private var tasima_kategorisi:String=""
    private var tasima_ozel_ambalajlar:String=""
    private var tasima_ozel_dokme:String=""
    private var tasima_ozel_ellecleme:String=""
    private var tasima_ozel_operasyon:String=""
    private var tehlike_tanimlama_no:String=""
    private var un_no:String=""




    var arr= arrayListOf<String>()
    var arr2= arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        val intent= Intent(this,MainActivity::class.java)

        GlobalScope.launch {
         readAndSave_json()

                startActivity(intent)

                finish()



        }

     /*   runBlocking { launch {


        }, 2000)*/







    }


    fun readAndSave_json(){ // çalışıyor
        var json:String?=null
        try {
            val  inputStream: InputStream =assets.open("un_kodlari.json")
            json=inputStream.bufferedReader().use{it.readText()}
            var jsonarr= JSONArray(json)

            for (i in 0..jsonarr.length()-1){ // satır satır arıyor
                var jsonobj=jsonarr.getJSONObject(i)
                //arr.add(jsonobj.getString("un_no"))
                adr_ozel_hukumler=jsonobj.getString("adr_ozel_hukumler") // veriler geliyor
                adr_tank_kodu=jsonobj.getString("adr_tank_kodu")
                ambalaj_talimatlari=jsonobj.getString("ambalaj_talimatlari")
                bakanlik_kod=jsonobj.getString("bakanlik_kod")
                etiketler=jsonobj.getString("etiketler")
                isim=jsonobj.getString("isim")
                karisik_ambalaj_hukumleri=jsonobj.getString("karisik_ambalaj_hukumleri")
                ozel_ambalaj_hukumleri=jsonobj.getString("ozel_ambalaj_hukumleri")
                ozel_hukumler=jsonobj.getString("ozel_hukumler")
                paketleme_grubu=jsonobj.getString("paketleme_grubu")
                portatif_ozel_hukumler=jsonobj.getString("portatif_ozel_hukumler")
                portatif_talimatlar=jsonobj.getString("portatif_talimatlar")
                sinif=jsonobj.getString("sinif")
                siniflandirma_kodu=jsonobj.getString("siniflandirma_kodu")
                sinirli_istisnai_miktarlar_7a=jsonobj.getString("sinirli_istisnai_miktarlar_7a")
                sinirli_istisnai_miktarlar_7b=jsonobj.getString("sinirli_istisnai_miktarlar_7b")
                tank_tasimasi=jsonobj.getString("tank_tasimasi")
                tasima_kategorisi=jsonobj.getString("tasima_kategorisi")
                tasima_ozel_ambalajlar=jsonobj.getString("tasima_ozel_ambalajlar")
                tasima_ozel_dokme=jsonobj.getString("tasima_ozel_dokme")
                tasima_ozel_ellecleme=jsonobj.getString("tasima_ozel_ellecleme")
                tasima_ozel_operasyon=jsonobj.getString("tasima_ozel_operasyon")
                tehlike_tanimlama_no=jsonobj.getString("tehlike_tanimlama_no")
                un_no=jsonobj.getString("un_no")

                var veriler=Veriler(adr_ozel_hukumler,adr_tank_kodu,ambalaj_talimatlari,bakanlik_kod,etiketler,
                isim,karisik_ambalaj_hukumleri,ozel_ambalaj_hukumleri,ozel_hukumler,paketleme_grubu,
                portatif_ozel_hukumler,portatif_talimatlar,sinif,siniflandirma_kodu,sinirli_istisnai_miktarlar_7a,sinirli_istisnai_miktarlar_7b,
                tank_tasimasi,tasima_kategorisi,tasima_ozel_ambalajlar,tasima_ozel_dokme,tasima_ozel_ellecleme,tasima_ozel_operasyon,tehlike_tanimlama_no,un_no)


                val context=this
                var db=DataBaseHelper(context)

                db.insertData(veriler)






            }



            Toast.makeText(applicationContext, "Veriler yüklendi.", Toast.LENGTH_SHORT).show()


        }catch (e: IOException){
            e.printStackTrace()
            Toast.makeText(applicationContext, "Başarısız olduk!", Toast.LENGTH_LONG).show()
        }
    }
}