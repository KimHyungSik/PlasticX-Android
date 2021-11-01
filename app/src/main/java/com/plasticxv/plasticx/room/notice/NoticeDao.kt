package com.plasticxv.plasticx.room.notice

import androidx.room.*
import com.plasticxv.plasticx.model.NoticeModel

@Dao
interface NoticeDao {

    @Query("SELECT * FROM NoticeList ORDER BY Id DESC")
    fun getNoticeAll(): List<NoticeModel>

    @Insert
    fun insertNotice(noticeItem : NoticeModel)

    @Update
    fun updateNotice(noticeItem: NoticeModel)

    @Delete
    fun delete(noticeItem: NoticeModel)
}