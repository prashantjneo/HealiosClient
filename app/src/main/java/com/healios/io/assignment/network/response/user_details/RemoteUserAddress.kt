package com.healios.io.assignment.network.response.user_details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteUserAddress(
    @SerializedName("street") @Expose val street: String?,
    @SerializedName("suite") @Expose val suite: String?,
    @SerializedName("city") @Expose val city: String?,
    @SerializedName("zipcode") @Expose val zipcode: String?,
    @SerializedName("geo") @Expose val geo: RemoteUserGeoCordinate?, )
