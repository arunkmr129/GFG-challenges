package com.example.gfg_challenge.model

import com.google.gson.annotations.SerializedName


data class Enclosure (

  @SerializedName("link"      ) var link      : String? = null,
  @SerializedName("type"      ) var type      : String? = null,
  @SerializedName("thumbnail" ) var thumbnail : String? = null

)