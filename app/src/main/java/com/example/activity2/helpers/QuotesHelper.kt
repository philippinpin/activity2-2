package com.example.activity2.helpers

import kotlin.random.Random

class QuotesHelper {

    private val quotes = mapOf(
        "Comedy" to listOf(
            "Comedy is simply a funny way of being serious.",
            "Laughter is timeless, imagination has no age.",
            "Life is better when you're laughing."
        ),
        "Funny" to listOf(
            "I'm not lazy, I'm on energy-saving mode.",
            "Why don’t scientists trust atoms? Because they make up everything!",
            "My bed is a magical place where I remember everything I forgot to do."
        ),
        "Love" to listOf(
            "Love is composed of a single soul inhabiting two bodies.",
            "Where there is love, there is life.",
            "You are my today and all of my tomorrows."
        )
    )

    fun getRandomQuoteByCategory(category: String): String {
        val categoryQuotes = quotes[category] ?: emptyList()
        return if (categoryQuotes.isNotEmpty()) {
            categoryQuotes[Random.nextInt(categoryQuotes.size)]
        } else {
            "No quotes found for this category."
        }
    }

    fun getRandomQuote(): String {
        val allQuotes = quotes.values.flatten()
        return allQuotes[Random.nextInt(allQuotes.size)]
    }
}
