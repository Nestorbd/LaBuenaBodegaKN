package com.example.labuenabodegakn.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.labuenabodegakn.models.Wine
import org.json.JSONObject

class WineServiceImpl : IWineService {
    override fun getAll(context: Context, completionHandler: (response: ArrayList<Wine>?) -> Unit) {
        val path = WineSingleton.getInstance(context).baseUrl + "/api/wines"
        val arrayRequest = JsonArrayRequest(Request.Method.GET, path, null,
            { response ->
                var wines: ArrayList<Wine> = ArrayList()
                for (i in 0 until response.length()) {
                    val wine = response.getJSONObject(i)
                    val id = wine.getInt("id")
                    val name = wine.getString("name")
                    val type = wine.getString("type")



                    wines.add(Wine(id, name, type))
                }
                completionHandler(wines)
            },
            { error ->
                completionHandler(ArrayList<Wine>())
            })
        WineSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

     override fun getById(context: Context, wineId: Int, completionHandler: (response: Wine?) -> Unit) {
        val path = WineSingleton.getInstance(context).baseUrl + "/api/wines/" + wineId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
            { response ->
                if (response == null) completionHandler(null)

                val id = response.getInt("id")
                val name = response.getString("name")
                val type = response.getString("type")


                val wine = Wine(id, name, type)
                completionHandler(wine)
            },
            { error ->
                completionHandler(null)
            })
        WineSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deleteById(context: Context, wineId: Int, completionHandler: () -> Unit) {
        val path = WineSingleton.getInstance(context).baseUrl + "/api/wines/" + wineId
        val objectRequest = JsonObjectRequest(Request.Method.DELETE, path, null,
            { response ->
                Log.v("Hola caracola", "se borró")
                completionHandler()
            },
            { error ->
                Log.v("Hola caracola", "dió error")
                completionHandler()
            })
        WineSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun updateWine(context: Context, wine: Wine, completionHandler: () -> Unit) {
        val path = WineSingleton.getInstance(context).baseUrl + "/api/wines/" + wine.id
        val wineJson: JSONObject = JSONObject()
        wineJson.put("id", wine.id.toString())
        wineJson.put("name", wine.name)
        wineJson.put("type", wine.type)


        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, wineJson,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        WineSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createWine(context: Context, wine: Wine, completionHandler: () -> Unit) {
        val path = WineSingleton.getInstance(context).baseUrl + "/api/wines"
        val wineJson: JSONObject = JSONObject()
        wineJson.put("id", wine.id.toString())
        wineJson.put("name", wine.name)
        wineJson.put("type", wine.type)


        val objectRequest = JsonObjectRequest(Request.Method.POST, path, wineJson,
            { response -> completionHandler() },
            { error -> completionHandler() })
        WineSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}