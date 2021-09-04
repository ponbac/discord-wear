package app.backman.discordwearos.data

import app.backman.discordwearos.data.entity.MembersResponse
import retrofit2.http.GET

interface DiscordService {
    companion object {
        const val BASE_URL = "https://api.backman.app/"
    }

    @GET("/members")
    suspend fun getMembers(): MembersResponse
}