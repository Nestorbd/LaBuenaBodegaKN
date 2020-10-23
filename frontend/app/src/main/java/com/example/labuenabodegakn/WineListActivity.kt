package com.example.labuenabodegakn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.labuenabodegakn.models.Wine
import com.example.labuenabodegakn.service.WineServiceImpl
import com.example.labuenabodegakn.service.WineSingleton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException

class WineListActivity : AppCompatActivity() {
    private lateinit var wine: ArrayList<Wine>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: WineAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wine_list)

        wine = ArrayList<Wine>()

        viewManager = LinearLayoutManager(this)

        viewAdapter = WineAdapter(wine, this)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewWine)
        // use a linear layout manager
        recyclerView.layoutManager = viewManager

        // specify an viewAdapter (see also next example)
        recyclerView.adapter = viewAdapter

        getAllWine()

        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener{
            val intent = Intent(this, WineDetailActivity::class.java)
            intent.putExtra("state", "Adding")
            startActivity(intent)
        }
    }

    private fun getAllWine() {

        val wineServiceImpl = WineServiceImpl()
        wineServiceImpl.getAll(this) { response ->
            run {
                if (response != null) {
                    viewAdapter.wineList = response
                }
                viewAdapter.notifyDataSetChanged()
            }
        }
    }

}