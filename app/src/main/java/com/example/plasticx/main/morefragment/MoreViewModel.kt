package com.example.plasticx.main.morefragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plasticx.model.NoticeModel
import com.example.plasticx.room.RoomRepository
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MoreViewModel @Inject constructor(private val roomRepository: RoomRepository) : ViewModel() {

    var _loading: MutableLiveData<Boolean> = MutableLiveData()
    var _noticeList: MutableLiveData<ArrayList<NoticeModel>> = MutableLiveData()

    val TAG = "MoreViewModel - 로그"
    suspend fun getNoticeList() {
        roomRepository
            .noticeRepository
            .getAll()
            .let {
                Log.d(TAG, "getNoticeList: $it")
                _noticeList.postValue(
                    ArrayList(
                        it
                    )
                )
                for (noticeItem in it) {
                    noticeItem.checked = true
                    roomRepository.noticeRepository.update(noticeItem)
                }
            }
    }

    suspend fun deleteNoticeItem(noticeModel: NoticeModel) {
        _loading.postValue(true)
        roomRepository.noticeRepository.delete(noticeModel)
        getNoticeList()
    }

}