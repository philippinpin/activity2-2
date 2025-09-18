package com.example.activity2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.activity2.databinding.ActivityCategoryBinding
import com.example.activity2.helpers.QuotesHelper
import com.example.activity2.helpers.SharedPrefHelper

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var quotesHelper: QuotesHelper
    private lateinit var sharedPrefHelper: SharedPrefHelper
    private var currentCategory: String? = null
    private var currentQuote: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize helpers
        quotesHelper = QuotesHelper()
        sharedPrefHelper = SharedPrefHelper(this)

        // Get category name from Intent
        currentCategory = intent.getStringExtra("CATEGORY")

        // Show random quote for category
        currentQuote = quotesHelper.getRandomQuoteByCategory(currentCategory ?: "")
        binding.txtCategoryQuote.text = currentQuote
        binding.txtCategoryTitle.text = "$currentCategory Quotes"

        // Save favourite
        binding.btnSaveFavourite.setOnClickListener {
            if (currentQuote.isNotEmpty()) {
                val timestamp = System.currentTimeMillis().toString()
                sharedPrefHelper.saveString("favourite_quote", currentQuote)
                sharedPrefHelper.saveString("favourite_time", timestamp)
                Toast.makeText(this, "Saved as favourite!", Toast.LENGTH_SHORT).show()
            }
        }
        // Show new random quote
        binding.btnNewQuote.setOnClickListener {
            currentQuote = quotesHelper.getRandomQuoteByCategory(currentCategory ?: "")
            binding.txtCategoryQuote.text = currentQuote
        }
    }
}
