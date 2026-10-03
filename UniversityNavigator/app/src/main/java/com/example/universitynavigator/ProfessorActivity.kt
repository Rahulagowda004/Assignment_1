package com.example.universitynavigator

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfessorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_professor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val universityIndex = intent.getIntExtra(UniversityActivity.EXTRA_UNIVERSITY_INDEX, -1)
        val universityName = intent.getStringExtra(UniversityActivity.EXTRA_UNIVERSITY_NAME)
        val departmentIndex = intent.getIntExtra(DepartmentActivity.EXTRA_DEPARTMENT_INDEX, -1)

        val departments = UniversityData.departmentsByUniversity.getOrNull(universityIndex)
        val department = departments?.getOrNull(departmentIndex)

        if (universityName == null || department == null) {
            finish()
            return
        }

        val universityText = findViewById<TextView>(R.id.universityText)
        val departmentText = findViewById<TextView>(R.id.departmentText)
        val professorText = findViewById<TextView>(R.id.professorText)
        val mapsButton = findViewById<Button>(R.id.mapsButton)
        val websiteButton = findViewById<Button>(R.id.websiteButton)
        val backButton = findViewById<Button>(R.id.backButton)

        universityText.text = universityName
        departmentText.text = department.name
        professorText.text = department.professorName

        mapsButton.setOnClickListener {
            openDepartmentOnMap(department.mapQuery)
        }

        websiteButton.setOnClickListener {
            openWebsite(department.professorWebsite)
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun openDepartmentOnMap(mapQuery: String) {
        // Implicit Intent: we describe WHAT to do (view a location), and Android
        // picks the app. setPackage asks for the Google Maps app specifically.
        val mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(mapQuery))
        val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        // If the Google Maps app is not installed, open Google Maps in a browser instead.
        val webMapUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(mapQuery))
        val webMapIntent = Intent(Intent.ACTION_VIEW, webMapUri)

        if (!tryStartActivity(mapIntent) && !tryStartActivity(webMapIntent)) {
            Toast.makeText(this, R.string.no_maps_app, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openWebsite(url: String) {
        // Implicit Intent: an http(s) link with ACTION_VIEW opens the default browser.
        val websiteIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        if (!tryStartActivity(websiteIntent)) {
            Toast.makeText(this, R.string.no_browser_app, Toast.LENGTH_SHORT).show()
        }
    }

    // startActivity throws ActivityNotFoundException when no installed app can handle the Intent.
    private fun tryStartActivity(intent: Intent): Boolean {
        return try {
            startActivity(intent)
            true
        } catch (e: ActivityNotFoundException) {
            false
        }
    }
}
