package gst.trainingcourse.roomexample.repository

import androidx.lifecycle.LiveData
import gst.trainingcourse.roomexample.data.UserDao
import gst.trainingcourse.roomexample.model.User

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllUser()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
    suspend fun update(user: User){
        userDao.update(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}

//
//interface UserRepository {
//    suspend fun addUser(user: User)
//    suspend fun update(user: User)
//    suspend fun deleteUser(user: User)
//    suspend fun deleteAllUsers()
//    suspend fun readAllData()
//}