package com.healios.io.assignment.network.response.user_details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteUserGeoCordinate(
    @SerializedName("lat") @Expose val lat: String?,
    @SerializedName("lng") @Expose val lng: String?, )
