package com.example.plasticx.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.plasticx.model.NoticeModel
import com.example.plasticx.room.notice.NoticeDao
import com.example.plasticx.utils.Utility.DATABASE_NAME

@Database(version = 1, entities = [NoticeModel::class], exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    public abstract fun noticeDao(): NoticeDao

    companion object{
        var instance: AppDatabase? = null

        public fun getInstance(context: Context): AppDatabase{
            if(instance == null){
               instance = buildDatabase(context)
            }
            return instance!!
        }

        private fun buildDatabase(context: Context): AppDatabase{

            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback(){
                    // 데이터 베이스가 처음 생성되었을 때
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}