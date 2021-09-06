package app.backman.discordwearos

import android.app.Activity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import app.backman.discordwearos.data.DiscordRepository
import app.backman.discordwearos.data.DiscordService
import app.backman.discordwearos.data.entity.Member
import app.backman.discordwearos.databinding.ActivityMainBinding
import app.backman.discordwearos.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        linearLayoutManager = LinearLayoutManager(this)
        binding.memberRecyclerView.layoutManager = linearLayoutManager

        // Call API and act on response
        val request = ServiceBuilder.buildService(DiscordService::class.java)
        val call = request.getMembers()
        call.enqueue(object : Callback<List<Member>> {
            override fun onResponse(call: Call<List<Member>>, response: Response<List<Member>>) {
                if (response.isSuccessful){
                    // Hide loading text
                    binding.progressBar.visibility = GONE
                    // Populate RecyclerView
                    binding.memberRecyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = linearLayoutManager
                        adapter = RecyclerAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<Member>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        /*val onlineMembers = DiscordRepository().fetchAllMembers()
        binding.text.text = onlineMembers.toString()*/
    }
}