package api

import com.kanghara.riiidproject.data.api.PostApi
import com.kanghara.riiidproject.data.entities.CommentData
import com.kanghara.riiidproject.data.entities.PatchPostData
import com.kanghara.riiidproject.data.entities.PostData
import io.reactivex.observers.TestObserver
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
class PostApiTest {
    private lateinit var api: PostApi
    @BeforeEach
    fun setup() {
        api = ApiClient.retrofit.create(PostApi::class.java)
    }

    @Test
    fun getPosts() {
        val observer = TestObserver<List<PostData>>()
        api.getPosts().subscribe(observer)
        observer.assertValue { it.isNotEmpty() }
    }

    @Test
    fun getPost() {
        val observer = TestObserver<PostData>()
        api.getPost(1).subscribe(observer)
        observer.assertValue {
            it.userId == 1 && it.id == 1 && it.title.isNotEmpty() && it.body.isNotEmpty()
        }
    }

    @Test
    fun getComments() {
        val observer = TestObserver<List<CommentData>>()
        api.getComments(1).subscribe(observer)
        observer.assertValue { it.isNotEmpty() }
    }

    @Test
    fun deletePost() {
        val observer = TestObserver<Void>()
        api.deletePost(1).subscribe(observer)
        observer.assertNoErrors()
    }

    @Test
    fun patchPost() {
        val observer = TestObserver<PatchPostData>()
        api.patchPost(1, PatchPostData(title = "hello")).subscribe(observer)
        observer.assertNoErrors()
    }
}