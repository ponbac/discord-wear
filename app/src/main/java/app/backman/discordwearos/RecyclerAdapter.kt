package app.backman.discordwearos

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.backman.discordwearos.data.entity.Member
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class RecyclerAdapter(private val members: List<Member>) : RecyclerView.Adapter<RecyclerAdapter.MemberHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MemberHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return MemberHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MemberHolder, position: Int) {
        val itemMember = members[position]
        holder.bindMember(itemMember)
    }

    override fun getItemCount() = members.size

    class MemberHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private val nickView: TextView = v.findViewById(R.id.itemNick)
        private val avatarView: ImageView = v.findViewById(R.id.itemImage)
        private var member: Member? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindMember(member: Member) {
            this.member = member
            Glide.with(view.context).load(member.avatar).transform(RoundedCorners(40)).into(avatarView)
            nickView.text = member.nick
            if (member.isStreaming == true) {
                nickView.setTextColor(Color.RED)
            }
        }

        companion object {
            private val MEMBER_KEY = "MEMBER"
        }
    }
}
