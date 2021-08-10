package com.healios.io.assignment.network.repositories

import androidx.lifecycle.LiveData
import com.healios.io.assignment.database.comment.LocalPostComment
import com.healios.io.assignment.database.comment.LocalPostCommentDao
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.database.posts.LocalPostDao
import com.healios.io.assignment.network.APIInterface
import com.healios.io.assignment.network.response.RemotePostComments
import com.healios.io.assignment.network.response.RemotePosts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteDataSource: APIInterface,
    private  val localPostDao:LocalPostDao,
    private  val localPostCommentDao: LocalPostCommentDao
) {

    suspend fun getRemotePost() {
        withContext(Dispatchers.IO) {
            try {
                val result = remoteDataSource.getAllPosts().await()
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
                val localPost = LocalPost(0, item.userId, item.id, item.title, item.body)
                listLocalPost.add(localPost)
            }
            localPostDao.saveAllPosts(listLocalPost)
        }
    }


    suspend fun getRemotePostComments(
        success: (ArrayList<RemotePostComments>) -> Unit,
        error: (Exception) -> Unit) {

        withContext(Dispatchers.IO) {
            try {
                val result = remoteDataSource.getPostComments().await()
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
                val localPostComment = LocalPostComment(0, item.postId, item.id, item.name, item.email, item.body)
                listLocalPostComments.add(localPostComment)
            }
            localPostCommentDao.saveAllPostsComment(listLocalPostComments)

        }

    }

    fun getPostsFromDatabase(): LiveData<List<LocalPost>> {
        return localPostDao.getAllLocalPost()
    }

}