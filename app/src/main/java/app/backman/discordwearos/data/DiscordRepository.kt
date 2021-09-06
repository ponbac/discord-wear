package app.backman.discordwearos.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.backman.discordwearos.data.entity.Member
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscordRepository {
    fun fetchAllMembers(): LiveData<List<Member>> {
        val data = MutableLiveData<List<Member>>()

        discordService?.getMembers()?.enqueue(object : Callback<List<Member>> {

            override fun onFailure(call: Call<List<Member>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<Member>>,
                response: Response<List<Member>>
            ) {

                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }
}