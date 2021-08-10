package com.healios.io.assignment.network.repositories

import com.healios.io.assignment.database.comment.LocalPostComment
import com.healios.io.assignment.database.comment.LocalPostCommentDao
import com.healios.io.assignment.database.user_details.LocalUserDao
import com.healios.io.assignment.database.user_details.LocalUserDetails
import com.healios.io.assignment.network.APIInterface
import com.healios.io.assignment.network.response.user_details.RemoteUserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
    private val remoteDataSource: APIInterface,
    private  val localUserDao: LocalUserDao,
    private  val localPostCommentDao: LocalPostCommentDao
) {

    suspend fun getRemoteUserDetails() {
        withContext(Dispatchers.IO) {
            try {
                val result = remoteDataSource.getUserDetails().await()
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
                    0, item.id, item.name, item.username, item.email, item.phone, item.website)
                listLocalUser.add(localDetails)
            }
            localUserDao.saveUserDetails(listLocalUser)
        }
    }

    suspend fun getSelectedUser(userId: Int): LocalUserDetails {

        var localUserDetails: LocalUserDetails
        withContext(Dispatchers.IO) {

            localUserDetails = localUserDao.getSelectedUser(userId)
        }
        return localUserDetails

    }



    suspend fun  getSelectedUserComment(postId:Int):List<LocalPostComment>{
        var localPostComment:List<LocalPostComment>

        withContext(Dispatchers.IO){
            localPostComment = localPostCommentDao.getSelectedPostComment(postId)
        }
        return  localPostComment
    }


}