package com.kanghara.riiid

import android.app.Application
import androidx.room.Room
import api.ApiClient
import cache.PostDatabase
import com.kanghara.riiidproject.data.api.PostApi
import com.kanghara.riiidproject.data.repository.PostRepositoryImpl
import com.kanghara.riiidproject.domain.PostRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * @author kevin.kang. Created on 2019-11-29..
 */
class RiiidApplication : Application() {

    val appModule = module {
        single { ApiClient.retrofit.create(PostApi::class.java) }
        single { Room.databaseBuilder(get(), PostDatabase::class.java, "posts").build() }
        single<PostRepository> { PostRepositoryImpl(get(), get()) }
        viewModel { PostsViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RiiidApplication)
            modules(appModule)
        }
    }
}