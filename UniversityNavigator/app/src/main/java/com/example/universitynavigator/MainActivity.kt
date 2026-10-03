package com.example.universitynavigator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val searchUniversityButton = findViewById<Button>(R.id.searchUniversityButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        searchUniversityButton.setOnClickListener {
            Toast.makeText(this, R.string.search_coming_soon, Toast.LENGTH_SHORT).show()
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}
