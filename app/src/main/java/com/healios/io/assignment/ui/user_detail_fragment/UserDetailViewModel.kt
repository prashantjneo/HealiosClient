package com.healios.io.assignment.ui.user_detail_fragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.healios.io.assignment.app_base_component.BaseViewModel
import com.healios.io.assignment.database.comment.LocalPostComment
import com.healios.io.assignment.database.user_details.LocalUserDetails
import com.healios.io.assignment.network.repositories.UserDetail
import kotlinx.coroutines.launch

class UserDetailViewModel(application: Application) : BaseViewModel(application) {


    private val _getLocalUserDetails = MutableLiveData<LocalUserDetails>()
    val getLocalUserDetails: LiveData<LocalUserDetails>
        get() = _getLocalUserDetails

    private val _getLocalPostComment = MutableLiveData<List<LocalPostComment>>()
    val getLocalPostComment: LiveData<List<LocalPostComment>>
        get() = _getLocalPostComment


    fun callRemoteUserDetails() {
        coroutineScope.launch {
            UserDetail.getRemoteUserDetails()
        }
    }




    fun getSelectedUserFromLocal(userId: Int) {
        coroutineScope.launch {
            _getLocalUserDetails.value = UserDetail.getSelectedUser(userId)
        }
    }

    fun getSelectedUserPostComment(pID: Int) {

        coroutineScope.launch {
            _getLocalPostComment.value= UserDetail.getSelectedUserComment(pID)
        }
    }
}