package com.example.plasticx.room.notice

import android.content.Context
import android.util.Log
import com.example.plasticx.model.NoticeModel
import com.example.plasticx.room.AppDatabase
import io.reactivex.Flowable

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

    fun delete(noticeItem: NoticeModel) = noticeNewsDao.delete(noticeItem)
}