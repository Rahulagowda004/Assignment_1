package com.example.universitynavigator

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
            Toast.makeText(this, R.string.maps_coming_soon, Toast.LENGTH_SHORT).show()
        }

        websiteButton.setOnClickListener {
            Toast.makeText(this, R.string.website_coming_soon, Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
