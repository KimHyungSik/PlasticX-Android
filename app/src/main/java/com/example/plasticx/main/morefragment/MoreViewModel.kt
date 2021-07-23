package com.example.plasticx.main.morefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plasticx.model.NoticeModel
import com.example.plasticx.room.RoomRepository
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject

class MoreViewModel @Inject constructor(val roomRepository: RoomRepository) : ViewModel() {

    var _loading: MutableLiveData<Boolean> = MutableLiveData()
    var _noticeList: MutableLiveData<ArrayList<NoticeModel>> = MutableLiveData()


    suspend fun getNoticeList() {
        _noticeList.postValue(
            ArrayList(
                roomRepository
                    .noticeRepository
                    .getAll()
            )
        )
    }

}