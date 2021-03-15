package gst.trainingcourse.roomexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gst.trainingcourse.roomexample.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                /*Tạo RoomDatabase.Builder cho cơ sở dữ liệu liên tục.
                Sau khi cơ sở dữ liệu được xây dựng,
                bạn nên giữ một tham chiếu đến nó và sử dụng lại nó.*/
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}