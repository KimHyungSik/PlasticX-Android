package com.example.plasticx.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.room.Room
import com.example.plasticx.MyApplication
import com.example.plasticx.R
import com.example.plasticx.main.MainActivity
import com.example.plasticx.model.NoticeModel
import com.example.plasticx.room.AppDatabase
import com.example.plasticx.room.RoomRepository
import com.example.plasticx.room.notice.NoticeRepository
import com.example.plasticx.utils.Utility.CHANNEL_ID
import com.example.plasticx.utils.Utility.DATABASE_NAME
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random


class MyFirebaseMessagingService : FirebaseMessagingService(){

    val TAG = "MFMessag - 로그"

    //새 토큰 생성 시 호출
    override fun onNewToken(token: String) {
    }

    fun getToken(callback: (String) -> Unit){
        var token = ""
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            token = task.result.toString()
            callback(token)
        })
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val title = remoteMessage.notification?.title
        val msg = remoteMessage.notification?.body

        Log.d(TAG, "onMessageReceived: ")

        val intent = Intent(this, MainActivity::class.java)
        val notification_ID = Random.nextInt()

        val notificationDatabase = AppDatabase.getInstance(applicationContext)

        //플래그 추가
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME).build()

        //다른 프로세스에서 권한 할당
        val contentIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)

        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_icon_invert)
            .setContentTitle(title)
            .setContentText(msg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setSound(uri)
            .build()

        notificationManager.notify(notification_ID, builder)
        Thread {
            val noticeModel = NoticeModel(null, title!!, msg)
            //notificationDatabase.noticeDao().insertNotice(noticeModel)
            database.noticeDao().insertNotice(noticeModel)
        }.start()
    }
}