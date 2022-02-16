package com.example.gfg_challenge.model

import com.google.gson.annotations.SerializedName


data class MainDataModel (

  @SerializedName("status" ) var status : String?          = null,
  @SerializedName("feed"   ) var feed   : Feed?            = Feed(),
  @SerializedName("items"  ) var items  : ArrayList<Items> = arrayListOf()

)