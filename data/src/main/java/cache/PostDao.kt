package cache

import androidx.room.*
import com.kanghara.riiidproject.data.entities.PostData
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author kevin.kang. Created on 2019-12-03..
 */
@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostData): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: List<PostData>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(post: PostData): Completable

    @Query("DELETE FROM posts WHERE id = :postId")
    fun delete(postId: Int): Completable

    @Query("SELECT * FROM posts LIMIT :limit OFFSET :offset")
    fun getAll(offset: Int, limit: Int): Single<List<PostData>>

    @Query("SELECT * FROM posts WHERE id = :postId")
    fun get(postId: Int): Single<PostData>
}