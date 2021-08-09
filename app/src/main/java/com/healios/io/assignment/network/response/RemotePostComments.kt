package com.healios.io.assignment.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemotePostComments(
    @SerializedName("postId") @Expose val postId: Int?,
    @SerializedName("id") @Expose val id: Int?,
    @SerializedName("name") @Expose val name: String?,
    @SerializedName("email") @Expose val email: String?,
    @SerializedName("body") @Expose val body: String?,
)
