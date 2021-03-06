package com.plasticxv.plasticx.room

import com.plasticxv.plasticx.room.notice.NoticeRepository
import javax.inject.Inject

class RoomRepository @Inject constructor (val noticeRepository: NoticeRepository) {

    companion object{
        private var instance: RoomRepository? = null
        public fun getInstance(noticeRepository: NoticeRepository):RoomRepository{
            return instance ?:synchronized(this) {
                instance ?: RoomRepository(noticeRepository).also {
                    instance = it
                }
            }
        }
    }

}