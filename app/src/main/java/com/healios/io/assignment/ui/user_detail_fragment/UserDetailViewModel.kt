package com.healios.io.assignment.ui.user_detail_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healios.io.assignment.database.comment.LocalPostComment
import com.healios.io.assignment.database.user_details.LocalUserDetails
import com.healios.io.assignment.network.repositories.UserDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private  val repository: UserDetailRepository) :ViewModel(){


    private val _getLocalUserDetails = MutableLiveData<LocalUserDetails>()
    val getLocalUserDetails: LiveData<LocalUserDetails>
        get() = _getLocalUserDetails

    private val _getLocalPostComment = MutableLiveData<List<LocalPostComment>>()
    val getLocalPostComment: LiveData<List<LocalPostComment>>
        get() = _getLocalPostComment


    fun callRemoteUserDetails() {
        viewModelScope.launch {
            repository.getRemoteUserDetails()
        }
    }

    fun getSelectedUserFromLocal(userId: Int) {
        viewModelScope.launch {
            _getLocalUserDetails.value = repository.getSelectedUser(userId)
        }
    }

    fun getSelectedUserPostComment(pID: Int) {
        viewModelScope.launch {
            _getLocalPostComment.value = repository.getSelectedUserComment(pID)
        }
    }
}