package com.plasticxv.plasticx.room.notice

import android.content.Context
import android.util.Log
import com.plasticxv.plasticx.model.NoticeModel
import com.plasticxv.plasticx.room.AppDatabase

class NoticeRepository(context: Context) {
    val TAG = "NoticeRepository - 로그"
    private val noticeNewsDao: NoticeDao by lazy{
        val db = AppDatabase.getInstance(context)
        db.noticeDao()
    }

    fun getAll(): List<NoticeModel> = noticeNewsDao.getNoticeAll()


    fun insert(noticeItem: NoticeModel){
        Log.d(TAG, "insert: $noticeItem")
        noticeNewsDao.insertNotice(noticeItem)
    }

    fun update(noticeItem: NoticeModel){
        noticeNewsDao.updateNotice(noticeItem)
    }

    fun delete(noticeItem: NoticeModel) = noticeNewsDao.delete(noticeItem)
}