package gst.trainingcourse.roomexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gst.trainingcourse.roomexample.repository.UserRepository

class Factory(private val userRepository: UserRepository)  {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return UserViewModel(userRepository) as T
//        }
//        throw IllegalArgumentException("Unable to construct viewmodel")
//    }
}