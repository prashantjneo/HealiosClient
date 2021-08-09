package com.healios.io.assignment.network.repositories

import androidx.lifecycle.LiveData
import com.healios.io.assignment.app_base_component.HealiosApp
import com.healios.io.assignment.database.AppDatabase
import com.healios.io.assignment.database.comment.LocalPostComment
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.database.user_details.LocalUserDetails
import com.healios.io.assignment.network.APIInterface
import com.healios.io.assignment.network.APINetwork
import com.healios.io.assignment.network.response.RemotePostComments
import com.healios.io.assignment.network.response.user_details.RemoteUserDetails
import com.healios.io.assignment.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object UserDetail {
    private val mServices: APIInterface by lazy {
        APINetwork.getClient(Constants.BASE_URL).create(APIInterface::class.java)
    }

    val localUserDetailDatabase = AppDatabase.getInstance(HealiosApp.instance).localUserDetails
    val localUserPostCommentDatabase = AppDatabase.getInstance(HealiosApp.instance).localPostComment
    val localUserPostDatabase = AppDatabase.getInstance(HealiosApp.instance).localPostDao

    suspend fun getRemoteUserDetails() {
        withContext(Dispatchers.Main) {
            try {
                val result = mServices.getUserDetails().await()
                saveUserDetailsIntoDatabase(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }





    suspend fun saveUserDetailsIntoDatabase(result: ArrayList<RemoteUserDetails>) {
        var listLocalUser: ArrayList<LocalUserDetails> = ArrayList()
        listLocalUser.clear()

        withContext(Dispatchers.IO) {
            for (item in result) {
                val localDetails = LocalUserDetails(
                    0, item.id, item.name, item.username, item.email,
                    item.phone, item.website
                )
                listLocalUser.add(localDetails)
            }
            localUserDetailDatabase.saveUserDetails(listLocalUser)
        }
    }

    suspend fun getSelectedUser(userId: Int): LocalUserDetails {

        var localUserDetails: LocalUserDetails

        withContext(Dispatchers.IO) {

            localUserDetails = localUserDetailDatabase.getSelectedUser(userId)
        }
        return localUserDetails

    }



    suspend fun  getSelectedUserComment(postId:Int):List<LocalPostComment>{
        var localPostComment:List<LocalPostComment>

        withContext(Dispatchers.IO){
            localPostComment = localUserPostCommentDatabase.getSelectedPostComment(postId)
        }

        return  localPostComment
    }
}