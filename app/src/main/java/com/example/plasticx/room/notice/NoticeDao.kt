package com.example.plasticx.room.notice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.plasticx.model.NoticeModel
import io.reactivex.Flowable

@Dao
interface NoticeDao {

    @Query("SELECT * FROM NoticeList ORDER BY Id DESC")
    fun getNoticeAll(): List<NoticeModel>

    @Insert
    fun insertNotice(noticeItem : NoticeModel)

    @Delete
    fun delete(noticeItem: NoticeModel)
}