package com.example.un

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.un.databinding.ActivityMainBinding
import com.example.un.databinding.ActivitySearchScreenBinding

class SearchScreen : AppCompatActivity() {
    private lateinit var binding:ActivitySearchScreenBinding
    private lateinit var adapter: Adapter2
  private  var kodListesi:ArrayList<String> = ArrayList()
    private var isimListesi:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_screen)


        binding = ActivitySearchScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getData()

        //screenRecycler
        binding.screenRecycler.layoutManager=LinearLayoutManager(this)
        adapter= Adapter2(kodListesi,isimListesi)
        binding.screenRecycler.adapter=adapter

        adapter.notifyDataSetChanged()




    }

    override fun onBackPressed() { finish() }
    fun getData(){
        isimListesi.clear()
        kodListesi.clear()
        var gelenKod=intent.getStringExtra("bakanlik_kodu")
        // Toast.makeText(applicationContext, gelenKod.toString()+" geldi", Toast.LENGTH_SHORT).show()



        var database=this.openOrCreateDatabase("UnProjesi", MODE_PRIVATE,null)
        val cursor=database.rawQuery( "select * from unKodlari where bakanlik_kod=?",
            arrayOf(gelenKod.toString())
        )
        val isim=cursor.getColumnIndex("isim")
        val unNo=cursor.getColumnIndex("un_no")
        val adrOzelHukumler=cursor.getColumnIndex("adr_ozel_hukumler")
        val adrTankKodu=cursor.getColumnIndex("adr_tank_kodu")
        val ambalajTalimatlari=cursor.getColumnIndex("ambalaj_talimatlari")
        val etiketler=cursor.getColumnIndex("etiketler")
        val karisikAmbalajHukumleri=cursor.getColumnIndex("karisik_ambalaj_hukumleri")
        val ozelAmbalajHukumleri=cursor.getColumnIndex("ozel_ambalaj_hukumleri")
        val ozelHukumler=cursor.getColumnIndex("ozel_hukumler")
        val paketlemeGrubu=cursor.getColumnIndex("paketleme_grubu")
        val portatifOzelHukumler=cursor.getColumnIndex("portatif_ozel_hukumler")
        val portatifTalimatlar=cursor.getColumnIndex("portatif_talimatlar")
        val sinif=cursor.getColumnIndex("sinif")
        val siniflandirmaKodu=cursor.getColumnIndex("siniflandirma_kodu")
        val sinirliIstisnaiMiktarlar7a=cursor.getColumnIndex("sinirli_istisnai_miktarlar_7a")
        val sinirliIstisnaiMiktarlar7b=cursor.getColumnIndex("sinirli_istisnai_miktarlar_7b")
        val tankTasimasi=cursor.getColumnIndex("tank_tasimasi")
        val tasimaKategorisi=cursor.getColumnIndex("tasima_kategorisi")
        val tasimaOZelAmbalajlar=cursor.getColumnIndex("tasima_ozel_ambalajlar")
        val tasimaOzelDokme=cursor.getColumnIndex("tasima_ozel_dokme")
        val tasimaOZelEllecleme=cursor.getColumnIndex("tasima_ozel_ellecleme")
        val tasimaOzelOperasyon=cursor.getColumnIndex("tasima_ozel_operasyon")
        val tehlikeTanimlamaNo=cursor.getColumnIndex("tehlike_tanimlama_no")

        while (cursor.moveToNext()){
           // binding.isim.setText("??sim: "+cursor.getString(isim))
            //binding.unKod.setText("UN no: "+cursor.getString(unNo))

            kodListesi.add("UN no: ")
            isimListesi.add(cursor.getString(unNo))

            kodListesi.add("??sim: ")
            isimListesi.add(cursor.getString(isim))

            kodListesi.add("S??n??f: ")
            isimListesi.add(cursor.getString(sinif))

            kodListesi.add("S??n??fland??rma Kodu: ")
            isimListesi.add(cursor.getString(siniflandirmaKodu))

            kodListesi.add("Paketleme Grubu: ")
            isimListesi.add(cursor.getString(paketlemeGrubu))

            kodListesi.add("Etiketler: ")
            isimListesi.add(cursor.getString(etiketler))

            kodListesi.add("??zel H??k??mler: ")
            isimListesi.add(cursor.getString(ozelHukumler))

            kodListesi.add("S??n??rli ??stisnai Miktarlar 7a: ")
            isimListesi.add(cursor.getString(sinirliIstisnaiMiktarlar7a))

            kodListesi.add("S??n??rli ??stisnai Miktarlar 7b: ")
            isimListesi.add(cursor.getString(sinirliIstisnaiMiktarlar7b))

            kodListesi.add("Ambalaj talimatlar??: ")
            isimListesi.add(cursor.getString(ambalajTalimatlari))

            kodListesi.add("??zel Ambalaj H??k??mleri:: ")
            isimListesi.add(cursor.getString(ozelAmbalajHukumleri))

            kodListesi.add("Kar??????k Ambalaj H??k??mleri:: ")
            isimListesi.add(cursor.getString(karisikAmbalajHukumleri))

            kodListesi.add("Portatif Talimatlar: ")
            isimListesi.add(cursor.getString(portatifTalimatlar))

            kodListesi.add("Portatif ??Zel H??k??mler: ")
            isimListesi.add(cursor.getString(portatifOzelHukumler))


            kodListesi.add("ADR Tank Kodu: ")
            isimListesi.add(cursor.getString(adrTankKodu))

            kodListesi.add("ADR ??zel H??k??mler: ")
            isimListesi.add(cursor.getString(adrOzelHukumler))

            kodListesi.add("Tank Ta????mas??: ")
            isimListesi.add(cursor.getString(tankTasimasi))

            kodListesi.add("Ta????ma Kategorisi: ")
            isimListesi.add(cursor.getString(tasimaKategorisi))

            kodListesi.add("Ta????ma ??zel Ambalajlar: ")
            isimListesi.add(cursor.getString(tasimaOZelAmbalajlar))

            kodListesi.add("Ta????ma ??zel Dokme: ")
            isimListesi.add(cursor.getString(tasimaOzelDokme))

            kodListesi.add("Ta????ma ??zel Elle??leme: ")
            isimListesi.add(cursor.getString(tasimaOZelEllecleme))

            kodListesi.add("Ta????ma ??zel Operasyon: ")
            isimListesi.add(cursor.getString(tasimaOzelOperasyon))

            kodListesi.add("Tehlike Tan??mlama No: ")
            isimListesi.add(cursor.getString(tehlikeTanimlamaNo))

            /*












         */




        }
        cursor.close()

        //Toast.makeText(applicationContext, gelenKod.toString(), Toast.LENGTH_SHORT).show()
    }


}