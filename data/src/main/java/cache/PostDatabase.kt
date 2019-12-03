package cache

import androidx.room.*
import com.kanghara.riiidproject.data.entities.CommentData
import com.kanghara.riiidproject.data.entities.PostData

/**
 * @author kevin.kang. Created on 2019-12-03..
 */
@Database(
    entities = [PostData::class, CommentData::class],
    exportSchema = false,
    version = 1
)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
}