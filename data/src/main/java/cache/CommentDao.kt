package cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanghara.riiidproject.data.entities.CommentData
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author kevin.kang. Created on 2019-12-03..
 */
@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comment: CommentData): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comments: List<CommentData>): Completable

    @Query("DELETE FROM comments WHERE postId = :postId")
    fun delete(postId: Int): Completable

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getComments(postId: Int): Single<List<CommentData>>
}