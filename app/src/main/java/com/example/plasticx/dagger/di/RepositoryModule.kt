package com.example.plasticx.dagger.di

import android.content.Context
import com.example.plasticx.retrofit.repository.RetrofitRepository
import com.example.plasticx.room.RoomRepository
import com.example.plasticx.room.notice.NoticeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
 class RepositoryModule {
    @Provides fun bindRetrofitRepository()
        = RetrofitRepository()


    @Provides fun bindRoomRepository(noticeRepository: NoticeRepository)
        = RoomRepository(noticeRepository)


    @Provides fun bindNoticeRepository(context: Context)
        = NoticeRepository(context)
}