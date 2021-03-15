package gst.trainingcourse.roomexample.data

import androidx.lifecycle.LiveData
import androidx.room.*
import gst.trainingcourse.roomexample.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // nếu thêm 1 user mới giống với 1 user có rồi thì bỏ qua
    suspend fun addUser(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table ")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllUser(): LiveData<List<User>>
}