package com.example.un

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL



val database_name="UnProjesi"
val table_name="unKodlari"

val col_adr_ozel_hukumler="adr_ozel_hukumler"
val col_adr_tank_kodu="adr_tank_kodu"
val col_ambalaj_talimatlari="ambalaj_talimatlari"
val col_bakanlik_kod="bakanlik_kod"
val col_etiketler="etiketler"
val col_isim="isim"
val col_karisik_ambalaj_hukumleri="karisik_ambalaj_hukumleri"
val col_ozel_ambalaj_hukumleri="ozel_ambalaj_hukumleri"
val col_ozel_hukumler="ozel_hukumler"
val col_paketleme_grubu="paketleme_grubu"
val col_portatif_ozel_hukumler="portatif_ozel_hukumler"
val col_portatif_talimatlar="portatif_talimatlar"
val col_sinif="sinif"
val col_siniflandirma_kodu="siniflandirma_kodu"
val col_sinirli_istisnai_miktarlar_7a="sinirli_istisnai_miktarlar_7a"
val col_sinirli_istisnai_miktarlar_7b="sinirli_istisnai_miktarlar_7b"
val col_tank_tasimasi="tank_tasimasi"
val col_tasima_kategorisi="tasima_kategorisi"
val col_tasima_ozel_ambalajlar="tasima_ozel_ambalajlar"
val col_tasima_ozel_dokme="tasima_ozel_dokme"
val col_tasima_ozel_ellecleme="tasima_ozel_ellecleme"
val col_tasima_ozel_operasyon="tasima_ozel_operasyon"
val col_tehlike_tanimlama_no="tehlike_tanimlama_no"
val col_un_no="un_no"


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


class DataBaseHelper (var context:Context):SQLiteOpenHelper(context, database_name,null,1) {


    override fun onCreate(db: SQLiteDatabase?) {
        // veritabanı oluştuğunda bir kez çalışmaktadır
        var createTable = "Create table " + table_name + "(" +
                col_bakanlik_kod + " TEXT PRIMARY KEY," +
                col_adr_ozel_hukumler + " TEXT," +
                col_adr_tank_kodu + " TEXT," +
                col_ambalaj_talimatlari + " TEXT," +
                col_etiketler + " TEXT," +
                col_isim + " TEXT," +
                col_karisik_ambalaj_hukumleri + " TEXT," +
                col_ozel_ambalaj_hukumleri + " TEXT," +
                col_ozel_hukumler + " TEXT," +
                col_paketleme_grubu + " TEXT," +
                col_portatif_ozel_hukumler + " TEXT," +
                col_portatif_talimatlar + " TEXT," +
                col_sinif + " TEXT," +
                col_siniflandirma_kodu + " TEXT," +
                col_sinirli_istisnai_miktarlar_7a + " TEXT," +
                col_sinirli_istisnai_miktarlar_7b + " TEXT," +
                col_tank_tasimasi + " TEXT," +
                col_tasima_kategorisi + " TEXT," +
                col_tasima_ozel_ambalajlar + " TEXT," +
                col_tasima_ozel_dokme + " TEXT," +
                col_tasima_ozel_ellecleme + " TEXT," +
                col_tasima_ozel_operasyon + " TEXT," +
                col_tehlike_tanimlama_no + " TEXT," +
                col_un_no + " TEXT)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    //veri kaydetme
    fun insertData(veriler: Veriler) { // satır satır eklemeyi loadingScreen yapıyor
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(col_bakanlik_kod, veriler.bakanlik_kod)
        cv.put(col_adr_ozel_hukumler, veriler.adr_ozel_hukumler)
        cv.put(col_adr_tank_kodu, veriler.adr_tank_kodu)
        cv.put(col_ambalaj_talimatlari, veriler.ambalaj_talimatlari)
        cv.put(col_etiketler, veriler.etiketler)
        cv.put(col_isim, veriler.isim)
        cv.put(col_karisik_ambalaj_hukumleri, veriler.karisik_ambalaj_hukumleri)
        cv.put(col_ozel_ambalaj_hukumleri, veriler.ozel_ambalaj_hukumleri)
        cv.put(col_ozel_hukumler, veriler.ozel_hukumler)
        cv.put(col_paketleme_grubu, veriler.paketleme_grubu)
        cv.put(col_portatif_ozel_hukumler, veriler.portatif_ozel_hukumler)
        cv.put(col_portatif_talimatlar, veriler.portatif_talimatlar)
        cv.put(col_sinif, veriler.sinif)
        cv.put(col_siniflandirma_kodu, veriler.siniflandirma_kodu)
        cv.put(col_sinirli_istisnai_miktarlar_7a, veriler.sinirli_istisnai_miktarlar_7a)
        cv.put(col_sinirli_istisnai_miktarlar_7b, veriler.sinirli_istisnai_miktarlar_7b)
        cv.put(col_tank_tasimasi, veriler.tank_tasimasi)
        cv.put(col_tasima_kategorisi, veriler.tasima_kategorisi)
        cv.put(col_tasima_ozel_ambalajlar, veriler.tasima_ozel_ambalajlar)
        cv.put(col_tasima_ozel_dokme, veriler.tasima_ozel_dokme)
        cv.put(col_tasima_ozel_ellecleme, veriler.tasima_ozel_ellecleme)
        cv.put(col_tasima_ozel_operasyon, veriler.tasima_ozel_operasyon)
        cv.put(col_tehlike_tanimlama_no, veriler.tehlike_tanimlama_no)
        cv.put(col_un_no, veriler.un_no)



        db.insert(table_name, null, cv) // tamamlanmadı


    }

    fun updateData(veriler: Veriler) { // çalışabilir


        try {
            insertData(veriler)


        } catch (e: Exception) {
            e.printStackTrace()
            println("Verileri güncelleyemedik")
        }

    }


    @SuppressLint("Range")
    fun firstDatas(): MutableList<Veriler> { // çalışıyor


        var liste: MutableList<Veriler> = ArrayList()
        val db = this.readableDatabase
        var sorgu = "select * from " + table_name + " LIMIT 10;"

        var sonuc = db.rawQuery(sorgu, null)
        if (sonuc.moveToFirst()) {
            do {
                var veri = Veriler()

                veri.isim = sonuc.getString(sonuc.getColumnIndex(col_isim))
                veri.un_no = sonuc.getString(sonuc.getColumnIndex(col_un_no))
                veri.bakanlik_kod = sonuc.getString(sonuc.getColumnIndex(col_bakanlik_kod))
                veri.siniflandirma_kodu =
                    sonuc.getString(sonuc.getColumnIndex(col_siniflandirma_kodu))
                veri.paketleme_grubu = sonuc.getString(sonuc.getColumnIndex(col_paketleme_grubu))

                liste.add(veri)

            } while (sonuc.moveToNext())
        }
        sonuc.close()
        db.close()
        return liste

    }

    @SuppressLint("Range")
    fun searchDatas(gelenUnkodu: String): MutableList<Veriler> { // çalışıyor


        var liste: MutableList<Veriler> = ArrayList()


        val db = this.readableDatabase
        var sorgu =
            "select * from " + table_name + " where un_no='" + gelenUnkodu + "' or isim LIKE '" + gelenUnkodu + "%' or isim LIKE '%" + gelenUnkodu + "%' or isim LIKE '%" + gelenUnkodu + "'"  // '%or%' '%or%'
// where un_no="amonyum" or isim LIKE 'amonyum%';
        var sonuc = db.rawQuery(sorgu, null)


        if (sonuc.moveToFirst()) {
            do {
                var veri = Veriler()

                veri.isim = sonuc.getString(sonuc.getColumnIndex(col_isim))
                veri.un_no = sonuc.getString(sonuc.getColumnIndex(col_un_no))
                veri.bakanlik_kod = sonuc.getString(sonuc.getColumnIndex(col_bakanlik_kod))
                veri.siniflandirma_kodu =
                    sonuc.getString(sonuc.getColumnIndex(col_siniflandirma_kodu))
                veri.paketleme_grubu = sonuc.getString(sonuc.getColumnIndex(col_paketleme_grubu))

                liste.add(veri)

            } while (sonuc.moveToNext())
        }
        sonuc.close()
        db.close()
        return liste


    }


    @SuppressLint("Range")
    fun detailSearch(gelenUnkodu: String): MutableList<Veriler> {


        var liste: MutableList<Veriler> = ArrayList()


        val db = this.readableDatabase
        var sorgu = "select * from " + table_name + " where bakanlik_kod=" + gelenUnkodu

        var sonuc = db.rawQuery(sorgu, null)
        var veri = Veriler()

        if (sonuc.moveToFirst()) {
            do {


                veri.isim = sonuc.getString(sonuc.getColumnIndex(col_isim))
                veri.un_no = sonuc.getString(sonuc.getColumnIndex(col_un_no))
                veri.bakanlik_kod = sonuc.getString(sonuc.getColumnIndex(col_bakanlik_kod))
                liste.add(veri)

            } while (sonuc.moveToNext())
        }
        sonuc.close()
        db.close()
        return liste


    }


    fun kayitVarYok(gelenUnKodu: String): Int { // çalışıyor. Kayıt sayısını getiriyor

        val db = this.readableDatabase
        var sorgu =
            "select * from " + table_name + " where un_no='" + gelenUnKodu + "' or isim LIKE '" + gelenUnKodu + "%' or isim LIKE '%" + gelenUnKodu + "%' or isim LIKE '%" + gelenUnKodu + "'"
        var sonuc = db!!.rawQuery(sorgu, null)
        sonuc.moveToNext()

        return sonuc.count

    }


    // json parse
    private val BASE_URL="http://10.0.2.2:8080/" //  10.0.2.2 çalışan IP
    private val dataApi:ArrayList<DataApi>?=null
    private var dataModels:ArrayList<DataModel>?=null


    fun jsonApiParse() {// çalışıyor
        val db = this.writableDatabase


        try {
           db.execSQL("delete from "+ table_name) // bütün verleri siliyor

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(DataApi::class.java)
            val call = service.getData()

            call.enqueue(object : Callback<List<DataModel>> {
                override fun onResponse(
                    call: Call<List<DataModel>>,
                    response: retrofit2.Response<List<DataModel>>
                ) {

                    if (response.isSuccessful) {


                        response.body()?.let {
                            // null değilse

                            dataModels = ArrayList(it)
                            for (dataModel: DataModel in dataModels!!) {

                                try{
                                if(dataModel.adr_ozel_hukumler!=null)
                                    adr_ozel_hukumler=dataModel.adr_ozel_hukumler
                                else
                                    adr_ozel_hukumler=""

                                if (dataModel.adr_tank_kodu!=null)
                                    adr_tank_kodu=dataModel.adr_tank_kodu
                                else
                                    adr_tank_kodu=""
                                if (dataModel.ambalaj_talimatlari!=null)
                                    ambalaj_talimatlari=dataModel.ambalaj_talimatlari
                                else
                                    ambalaj_talimatlari=""
                                if(dataModel.bakanlik_kod!=null)
                                    bakanlik_kod=dataModel.bakanlik_kod
                                else
                                    bakanlik_kod=""
                                if (dataModel.etiketler!=null)
                                    etiketler=dataModel.etiketler
                                else
                                    etiketler=""
                                if (dataModel.isim!=null)
                                    isim=dataModel.isim
                                else
                                    isim=""
                                if (dataModel.karisik_ambalaj_hukumleri!=null)
                                    karisik_ambalaj_hukumleri=dataModel.karisik_ambalaj_hukumleri
                                else
                                    karisik_ambalaj_hukumleri=""
                                if (dataModel.ozel_ambalaj_hukumleri!=null)
                                    ozel_ambalaj_hukumleri=dataModel.ozel_ambalaj_hukumleri
                                else
                                    ozel_ambalaj_hukumleri=""
                                if (dataModel.ozel_hukumler!=null)
                                    ozel_hukumler=dataModel.ozel_hukumler
                                else
                                    ozel_hukumler=""
                                if (dataModel.paketleme_grubu!=null)
                                    paketleme_grubu=dataModel.paketleme_grubu
                                else
                                    paketleme_grubu=""
                                if (dataModel.portatif_ozel_hukumler!=null)
                                    portatif_ozel_hukumler=dataModel.portatif_ozel_hukumler
                                else
                                    portatif_ozel_hukumler=""
                                if (dataModel.portatif_talimatlar!=null)
                                    portatif_talimatlar=dataModel.portatif_talimatlar
                                else
                                    portatif_talimatlar=""
                                if (dataModel.sinif!=null)
                                    sinif=dataModel.sinif
                                else
                                    sinif=""
                                if (dataModel.siniflandirma_grubu!=null)
                                    siniflandirma_kodu=dataModel.siniflandirma_grubu
                                else
                                    siniflandirma_kodu=""

                                if (dataModel.sinirli_istisnai_miktarlar_7a!=null)
                                    sinirli_istisnai_miktarlar_7a=dataModel.sinirli_istisnai_miktarlar_7a
                                else
                                    sinirli_istisnai_miktarlar_7a=""
                                if (dataModel.sinirli_istisnai_miktarlar_7b!=null)
                                    sinirli_istisnai_miktarlar_7b=dataModel.sinirli_istisnai_miktarlar_7b
                                else
                                    sinirli_istisnai_miktarlar_7b=""

                               if (dataModel.tank_tasimasi!=null)
                                   tank_tasimasi=dataModel.tank_tasimasi
                                else
                                    tank_tasimasi=""
                                if (dataModel.tasima_kategorisi!=null)
                                    tasima_kategorisi=dataModel.tasima_kategorisi
                                else
                                    tasima_kategorisi=""
                                if (dataModel.tasima_ozel_ambalajlar!=null)
                                    tasima_ozel_ambalajlar=dataModel.tasima_ozel_ambalajlar
                                else
                                    tasima_ozel_ambalajlar=""

                                if (dataModel.tasima_ozel_dokme!=null)
                                    tasima_ozel_dokme=dataModel.tasima_ozel_dokme
                                else
                                    tasima_ozel_dokme=""

                                if (dataModel.tasima_ozel_ellecleme!=null)
                                    tasima_ozel_ellecleme=dataModel.tasima_ozel_ellecleme
                                else
                                    tasima_ozel_ellecleme=""
                                if (dataModel.tasima_ozel_operasyon!=null)
                                    tasima_ozel_operasyon=dataModel.tasima_ozel_operasyon
                                else
                                    tasima_ozel_operasyon=""
                                if (dataModel.tehlike_tanimlama_no!=null)
                                    tehlike_tanimlama_no=dataModel.tehlike_tanimlama_no
                                else
                                    tehlike_tanimlama_no=""
                                if (dataModel.un_no!=null)
                                    un_no=dataModel.un_no
                                else
                                    un_no=""





  // atama yapmıyor!


                                    var veriler=Veriler(adr_ozel_hukumler,adr_tank_kodu,ambalaj_talimatlari,bakanlik_kod,etiketler,
                                    isim,karisik_ambalaj_hukumleri,ozel_ambalaj_hukumleri,ozel_hukumler,paketleme_grubu,
                                    portatif_ozel_hukumler,portatif_talimatlar,sinif,siniflandirma_kodu,sinirli_istisnai_miktarlar_7a,sinirli_istisnai_miktarlar_7a,
                                    tank_tasimasi,tasima_kategorisi,tasima_ozel_ambalajlar,tasima_ozel_dokme,tasima_ozel_ellecleme,tasima_ozel_operasyon,tehlike_tanimlama_no,un_no)



                                  updateData(veriler)

                              //  println("isimler : " + veriler.isim) // veriler geliyor
                                    println("Güncelleme yapıldı.")
                                 }catch (e:Exception){
                                     e.printStackTrace()
                                    println("Güncelleme yapılamadı!")
                                 }

                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                    t.printStackTrace()
                    println("olmadı")
                }

            })
        }catch (e:Exception){
            e.printStackTrace()
            println("Verileri çekemedik")
        }



    }
}