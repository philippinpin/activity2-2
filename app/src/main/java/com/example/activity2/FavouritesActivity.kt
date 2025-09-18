package com.example.activity2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.activity2.databinding.ActivityFavouritesBinding
import com.example.activity2.helpers.SharedPrefHelper
import java.text.SimpleDateFormat
import java.util.*

class FavouritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouritesBinding
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefHelper = SharedPrefHelper(this)

        loadFavourite()

        // Delete favourite
        binding.btnDeleteFavourite.setOnClickListener {
            sharedPrefHelper.delete("favourite_quote")
            sharedPrefHelper.delete("favourite_time")
            Toast.makeText(this, "Favourite removed", Toast.LENGTH_SHORT).show()
            loadFavourite()
        }
    }

    private fun loadFavourite() {
        val favQuote = sharedPrefHelper.getString("favourite_quote")
        val favTime = sharedPrefHelper.getString("favourite_time")

        if (!favQuote.isNullOrEmpty() && !favTime.isNullOrEmpty()) {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val formattedTime = try {
                sdf.format(Date(favTime.toLong()))
            } catch (e: Exception) {
                "Unknown time"
            }

            binding.txtFavouriteQuote.text = favQuote
            binding.txtFavouriteTime.text = "Saved at: $formattedTime"
            binding.btnDeleteFavourite.isEnabled = true
        } else {
            binding.txtFavouriteQuote.text = "No favourite quote saved."
            binding.txtFavouriteTime.text = ""
            binding.btnDeleteFavourite.isEnabled = false
        }
    }
}
