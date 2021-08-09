package com.healios.io.assignment.network

import com.healios.io.assignment.network.response.RemotePostComments
import com.healios.io.assignment.network.response.RemotePosts
import com.healios.io.assignment.network.response.user_details.RemoteUserDetails
import com.healios.io.assignment.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface APIInterface {

    @GET(Constants.GET_POSTS)
    fun getAllPosts(
    ): Deferred<ArrayList<RemotePosts>>


    @GET(Constants.GET_USER_DETAILS)
    fun getUserDetails():Deferred<ArrayList<RemoteUserDetails>>


    @GET(Constants.GET_POST_COMMENT)
    fun  getPostComments():Deferred<ArrayList<RemotePostComments>>

}