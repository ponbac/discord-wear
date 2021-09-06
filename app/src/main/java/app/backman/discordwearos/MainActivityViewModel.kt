package app.backman.discordwearos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.backman.discordwearos.data.DiscordRepository
import app.backman.discordwearos.data.entity.Member

class MainActivityViewModel : ViewModel() {
    var onlineMembersLiveData: LiveData<List<Member>>? = null

    fun getMembers() : LiveData<List<Member>>? {
        onlineMembersLiveData = DiscordRepository().fetchAllMembers()
        return onlineMembersLiveData
    }
}