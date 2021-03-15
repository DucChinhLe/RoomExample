package gst.trainingcourse.roomexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import gst.trainingcourse.roomexample.data.UserDatabase
import gst.trainingcourse.roomexample.repository.UserRepository
import gst.trainingcourse.roomexample.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        //viewModelScope sẽ tự động cancel tất cả các coroutine được start bởi ViewModel,
        // để tránh việc khi ko cần dùng đến nữa mà coroutine vẫn chạy có thể làm tốn tài nguyên bộ nhớ của người dùng
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun update(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }
}