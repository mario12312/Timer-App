package com.example.timerapp.repository.users

import androidx.lifecycle.MutableLiveData
import com.example.timerapp.model.User
import com.example.timerapp.network.users.RecyclerData
import com.example.timerapp.network.users.RetroServieInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UsersRetro @Inject constructor(
    private val retroInstance: RetroServieInstance
) {

    fun makeApiCall(liveData: MutableLiveData<List<User>>){
        val call: Call<List<User>> = retroInstance.getUsers()
        call.enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                liveData.postValue(response.body()!!)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
}