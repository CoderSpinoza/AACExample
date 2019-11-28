package api

import com.kanghara.riiidproject.data.api.PostApi
import com.kanghara.riiidproject.data.entities.Comment
import com.kanghara.riiidproject.data.entities.PatchPost
import com.kanghara.riiidproject.data.entities.Post
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
        val observer = TestObserver<List<Post>>()
        api.getPosts().subscribe(observer)
        observer.assertValue { it.isNotEmpty() }
    }

    @Test
    fun getPost() {
        val observer = TestObserver<Post>()
        api.getPost(1).subscribe(observer)
        observer.assertValue {
            it.userId == 1 && it.id == 1 && it.title.isNotEmpty() && it.body.isNotEmpty()
        }
    }

    @Test
    fun getComments() {
        val observer = TestObserver<List<Comment>>()
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
        val observer = TestObserver<PatchPost>()
        api.patchPost(1, PatchPost(title = "hello")).subscribe(observer)
        observer.assertNoErrors()
    }
}