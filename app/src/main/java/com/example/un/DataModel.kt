package com.example.un

import com.google.gson.annotations.SerializedName

data class  DataModel (

  @SerializedName("adr_ozel_hukumler")
  val adr_ozel_hukumler: String,
  @SerializedName("adr_tank_kodu")
   val  adr_tank_kodu: String,
   @SerializedName("ambalaj_talimatlari")
   val ambalaj_talimatlari: String,
    @SerializedName("bakanlik_kod")
   val bakanlik_kod: String,
    @SerializedName("etiketler")
   val  etiketler: String,
    @SerializedName("isim")
   val isim: String,
  @SerializedName("karisik_ambalaj_hukumleri")
   val karisik_ambalaj_hukumleri:String,
    @SerializedName("ozel_ambalaj_hukumleri")
   val ozel_ambalaj_hukumleri: String,
   @SerializedName("ozel_hukumler")
   val ozel_hukumler: String,
    @SerializedName("paketleme_grubu")
   val paketleme_grubu: String,
    @SerializedName("portatif_ozel_hukumler")
   val portatif_ozel_hukumler: String,
    @SerializedName("portatif_talimatlar")
   val portatif_talimatlar: String,
    @SerializedName("sinif")
   val sinif: String,
    @SerializedName("siniflandirma_grubu")
   val siniflandirma_grubu: String,
    @SerializedName("sinirli_istisnai_miktarlar_7a")
   val sinirli_istisnai_miktarlar_7a: String,
    @SerializedName("sinirli_istisnai_miktarlar_7b")
   val sinirli_istisnai_miktarlar_7b: String,
  @SerializedName("tank_tasimasi")
   val tank_tasimasi: String,
 @SerializedName("tasima_kategorisi")
   val tasima_kategorisi: String,
@SerializedName("tasima_ozel_ambalajlar")
   val tasima_ozel_ambalajlar: String,
    @SerializedName("tasima_ozel_dokme")
   val tasima_ozel_dokme: String,
   @SerializedName("tasima_ozel_ellecleme")
   val tasima_ozel_ellecleme: String,
   @SerializedName("tasima_ozel_operasyon")
   val tasima_ozel_operasyon: String,
    @SerializedName("tehlike_tanimlama")
   val tehlike_tanimlama_no: String,
    @SerializedName("un_no")
   val un_no: String

        )