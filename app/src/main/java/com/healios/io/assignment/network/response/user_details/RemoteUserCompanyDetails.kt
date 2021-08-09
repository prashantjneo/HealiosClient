package com.healios.io.assignment.network.response.user_details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteUserCompanyDetails(
    @SerializedName("name") @Expose val name: String?,
    @SerializedName("catchPhrase") @Expose val catchPhrase: String?,
    @SerializedName("bs") @Expose val bs: String?, )
