package com.example.labuenabodegakn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.labuenabodegakn.models.Wine
import com.example.labuenabodegakn.service.WineServiceImpl
import com.example.labuenabodegakn.service.WineSingleton
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class WineDetailActivity : AppCompatActivity() {
    private lateinit var state: String
    private lateinit var textInputEditTextName: EditText
    private lateinit var textInputEditTextType: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wine_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        state = this.intent.getStringExtra("state").toString()

        val wineId = this.intent.getIntExtra("wineId", 1)

        textInputEditTextName = findViewById(R.id.TextInputEditTextName)
        textInputEditTextType = findViewById(R.id.TextInputEditTextType)




        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            deleteWine(wineId)
        }

        if(state == "Showing") getWine(wineId)

        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            when(state){
                "Showing" -> {
                    changeButtonsToEditing()
                }
                "Editing" -> {
                    val wine = Wine(wineId, textInputEditTextName.text.toString(),
                            textInputEditTextType.text.toString())
                    updateWine(wine)
                }
                "Adding" -> {
                    val wine = Wine(wineId, textInputEditTextName.text.toString(), textInputEditTextType.text.toString())
                    createWine(wine)
                }
            }
        }

        if(state == "Adding") changeButtonsToAdding()
    }

    private fun updateWine(wine: Wine) {
        val wineServiceImpl = WineServiceImpl()
        wineServiceImpl.updateWine(this, wine) { ->
            run {
                changeButtonsToShowing(wine.id)
                val intent = Intent(this, WineListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createWine(wine: Wine) {
        val wineServiceImpl = WineServiceImpl()
        wineServiceImpl.createWine(this, wine) { ->
            run {
                changeButtonsToShowing(wine.id)
                val intent = Intent(this, WineListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeButtonsToAdding() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Add Wine")
        textInputEditTextName.isEnabled = true
        textInputEditTextType.isEnabled = true

        state = "Adding"
    }

    private fun changeButtonsToShowing(wineId: Int){
        buttonDelete.visibility = View.VISIBLE
        buttonDelete.isEnabled = true
        buttonEdit.setText("Edit Wine")
        textInputEditTextName.isEnabled = false
        textInputEditTextType.isEnabled = false

        state = "Showing"
    }

    private fun changeButtonsToEditing() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Apply changes")
        textInputEditTextName.isEnabled = true
        textInputEditTextType.isEnabled = true

        state = "Editing"
    }

    private fun getWine(wineId: Int) {
        val wineServiceImpl = WineServiceImpl()
        wineServiceImpl.getById(this, wineId) { response ->
            run {

                val txt_name: TextInputEditText = findViewById(R.id.TextInputEditTextName)
                val txt_type: TextInputEditText = findViewById(R.id.TextInputEditTextType)

                val img: ImageView = findViewById(R.id.imageViewWineDetail)

                txt_name.setText(response?.name ?: "")
                txt_type.setText(response?.type ?: "")


                val url = WineSingleton.getInstance(this).baseUrl + "/img/wine-"
                val imageUrl = url + (response?.id.toString() ?: "0" ) + ".png"
                Picasso.with(this).load(imageUrl).into(img);
            }
        }
    }

    private fun deleteWine(wineId: Int) {
        val wineServiceImpl = WineServiceImpl()
        wineServiceImpl.deleteById(this, wineId) { ->
            run {
                val intent = Intent(this, WineListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}