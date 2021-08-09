package com.healios.io.assignment.ui.homefragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.healios.io.assignment.app_base_component.BaseViewModel
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.network.repositories.Home
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {


    private val _getLocalPostResponse = MutableLiveData<List<LocalPost>>()
    val getLocalPostResponse: LiveData<List<LocalPost>>
        get() = _getLocalPostResponse

    fun callRemotePosts() {
        coroutineScope.launch {
            Home.getRemotePost()
        }
    }

    fun getLocalPosts(): LiveData<List<LocalPost>> {
        _getLocalPostResponse.value = Home.getPostsFromDatabase().value
        return Home.getPostsFromDatabase()
    }

    fun callRemotePostComment() {
        coroutineScope.launch {

            Home.getRemotePostComments(success = {
                if (it.size != 0) {
                    Log.d("HOMEVIEWMODEL", it.get(0).body!!)
                }
            }, error = {
                it.printStackTrace()
            })
        }
    }
}