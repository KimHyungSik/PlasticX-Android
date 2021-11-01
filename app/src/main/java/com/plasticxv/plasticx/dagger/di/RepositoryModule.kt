package com.plasticxv.plasticx.dagger.di

import android.content.Context
import com.plasticxv.plasticx.retrofit.repository.RetrofitRepository
import com.plasticxv.plasticx.room.RoomRepository
import com.plasticxv.plasticx.room.notice.NoticeRepository
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