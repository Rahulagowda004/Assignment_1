package com.example.universitynavigator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UniversityActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_UNIVERSITY_INDEX = "extra_university_index"
        const val EXTRA_UNIVERSITY_NAME = "extra_university_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_university)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val universityRadioGroup = findViewById<RadioGroup>(R.id.universityRadioGroup)
        val backButton = findViewById<Button>(R.id.backButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        backButton.setOnClickListener {
            finish()
        }

        nextButton.setOnClickListener {
            val checkedId = universityRadioGroup.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, R.string.select_university_first, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedButton = findViewById<RadioButton>(checkedId)
            val universityIndex = universityRadioGroup.indexOfChild(selectedButton)
            val universityName = selectedButton.text.toString()

            val intent = Intent(this, DepartmentActivity::class.java)
            intent.putExtra(EXTRA_UNIVERSITY_INDEX, universityIndex)
            intent.putExtra(EXTRA_UNIVERSITY_NAME, universityName)
            startActivity(intent)
        }
    }
}
