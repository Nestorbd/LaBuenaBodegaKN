package com.example.labuenabodegakn.service

import android.content.Context
import com.example.labuenabodegakn.models.Wine
import org.json.JSONArray
import org.json.JSONObject

interface IWineService {
    fun getAll(context: Context, completionHandler: (response: ArrayList<Wine>?) -> Unit)

    fun getById(context: Context, wineId: Int, completionHandler: (response: Wine?) -> Unit)

    fun deleteById(context: Context, wineId: Int, completionHandler: () -> Unit)

    fun updateWine(context: Context, wine: Wine, completionHandler: () -> Unit)

    fun createWine(context: Context, wine: Wine, completionHandler: () -> Unit)
}