package com.healios.io.assignment.network.repositories

import androidx.lifecycle.LiveData
import com.healios.io.assignment.app_base_component.HealiosApp
import com.healios.io.assignment.database.AppDatabase
import com.healios.io.assignment.database.comment.LocalPostComment
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.network.APIInterface
import com.healios.io.assignment.network.APINetwork
import com.healios.io.assignment.network.response.RemotePostComments
import com.healios.io.assignment.network.response.RemotePosts
import com.healios.io.assignment.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Home {
    private val mServices: APIInterface by lazy {
        APINetwork.getClient(Constants.BASE_URL).create(APIInterface::class.java)
    }

    val database = AppDatabase.getInstance(HealiosApp.instance).localPostDao

    suspend fun getRemotePost() {
        withContext(Dispatchers.Main) {
            try {
                val result = mServices.getAllPosts().await()
                savePostsIntoLocalDatabase(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun savePostsIntoLocalDatabase(result: ArrayList<RemotePosts>) {

        var listLocalPost: ArrayList<LocalPost> = ArrayList()
        listLocalPost.clear()
        withContext(Dispatchers.IO) {
            for (item in result) {
                val localPost = LocalPost(
                    0, item.userId, item.id, item.title, item.body
                )
                listLocalPost.add(localPost)
            }
            database.saveAllPosts(listLocalPost)
        }
    }


    suspend fun getRemotePostComments(
        success: (ArrayList<RemotePostComments>) -> Unit,
        error: (Exception) -> Unit
    ) {

        withContext(Dispatchers.IO) {
            try {
                val result = mServices.getPostComments().await()

                success(result)
                savePostCommentIntoDatabase(result)
            } catch (e: Exception) {

                error(e)
                e.printStackTrace()
            }
        }
    }

    suspend fun savePostCommentIntoDatabase(result: ArrayList<RemotePostComments>) {

        var listLocalPostComments: ArrayList<LocalPostComment> = ArrayList()
        listLocalPostComments.clear()

        withContext(Dispatchers.IO) {
            for (item in result) {
                val localPostComment = LocalPostComment(
                    0, item.postId, item.id, item.name, item.email, item.body
                )
                listLocalPostComments.add(localPostComment)
            }
            UserDetail.localUserPostCommentDatabase.saveAllPostsComment(listLocalPostComments)

        }

    }

    fun getPostsFromDatabase(): LiveData<List<LocalPost>> {
        return database.getAllLocalPost()
    }

}