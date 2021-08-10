package com.healios.io.assignment.ui.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.network.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(private  val repository: HomeRepository) : ViewModel() {

    private val _getLocalPostResponse = MutableLiveData<List<LocalPost>>()
    val getLocalPostResponse: LiveData<List<LocalPost>>
        get() = _getLocalPostResponse

    fun callRemotePosts() {
        viewModelScope.launch {
            repository.getRemotePost()
        }
    }

    fun getLocalPosts(): LiveData<List<LocalPost>> {
        _getLocalPostResponse.value = repository.getPostsFromDatabase().value
        return repository.getPostsFromDatabase()
    }

    fun callRemotePostComment() {
        viewModelScope.launch {
            repository.getRemotePostComments(success = {}, error = {
                it.printStackTrace()
            })
        }
    }
}