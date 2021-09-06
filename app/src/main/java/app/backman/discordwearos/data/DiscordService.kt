package app.backman.discordwearos.data

import app.backman.discordwearos.data.entity.Member
import retrofit2.Call
import retrofit2.http.GET

interface DiscordService {
    @GET("/members")
    suspend fun getMembers(): Call<List<Member>>
}