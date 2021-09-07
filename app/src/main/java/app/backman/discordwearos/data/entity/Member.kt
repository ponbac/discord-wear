package app.backman.discordwearos.data.entity

data class Member (
    val nick: String?,
    val avatar: String?,
    val isStreaming: Boolean?,
    val channelId: String?,
    val channelName: String?
)