package com.example.activity2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.activity2.databinding.ActivityMainBinding
import com.example.activity2.helpers.QuotesHelper
import com.example.activity2.helpers.SharedPrefHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var quotesHelper: QuotesHelper
    private lateinit var sharedPrefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quotesHelper = QuotesHelper()
        sharedPrefHelper = SharedPrefHelper(this)

        // Show a random quote of the day
        binding.txtQuoteOfTheDay.text = quotesHelper.getRandomQuote()

        // Navigate to categories
        binding.btnComedy.setOnClickListener {
            openCategory("Comedy")
        }

        binding.btnFunny.setOnClickListener {
            openCategory("Funny")
        }

        binding.btnLove.setOnClickListener {
            openCategory("Love")
        }

        // Navigate to favourites
        binding.btnFavourites.setOnClickListener {
            val intent = Intent(this, FavouritesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Check if favourite exists, enable/disable button
        val favQuote = sharedPrefHelper.getString("favourite_quote")
        binding.btnFavourites.isEnabled = !favQuote.isNullOrEmpty()
    }

    private fun openCategory(category: String) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }
}
