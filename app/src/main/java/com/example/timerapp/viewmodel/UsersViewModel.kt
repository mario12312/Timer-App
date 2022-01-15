package com.example.timerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timerapp.model.User
import com.example.timerapp.network.users.RecyclerData
import com.example.timerapp.repository.users.UsersRetro
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel  @Inject constructor(
    private val usersRetro: UsersRetro
) : ViewModel() {
    var liveDataList: MutableLiveData<List<User>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<User>>{
        return liveDataList
    }

    fun loadListOfUsers(){
        usersRetro.makeApiCall(liveDataList)
    }
}