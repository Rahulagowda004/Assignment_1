package com.example.universitynavigator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DepartmentActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DEPARTMENT_INDEX = "extra_department_index"
        const val EXTRA_DEPARTMENT_NAME = "extra_department_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_department)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val universityIndex = intent.getIntExtra(UniversityActivity.EXTRA_UNIVERSITY_INDEX, -1)
        val universityName = intent.getStringExtra(UniversityActivity.EXTRA_UNIVERSITY_NAME)

        if (universityIndex !in UniversityData.departmentsByUniversity.indices || universityName == null) {
            finish()
            return
        }

        val universityNameText = findViewById<TextView>(R.id.universityNameText)
        val departmentRadioGroup = findViewById<RadioGroup>(R.id.departmentRadioGroup)
        val backButton = findViewById<Button>(R.id.backButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        universityNameText.text = universityName

        val departments = UniversityData.departmentsByUniversity[universityIndex]
        for (department in departments) {
            val radioButton = layoutInflater.inflate(
                R.layout.item_department_option, departmentRadioGroup, false
            ) as RadioButton
            radioButton.id = View.generateViewId()
            radioButton.text = department.name
            departmentRadioGroup.addView(radioButton)
        }

        backButton.setOnClickListener {
            finish()
        }

        nextButton.setOnClickListener {
            val checkedId = departmentRadioGroup.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, R.string.select_department_first, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedButton = findViewById<RadioButton>(checkedId)
            val departmentIndex = departmentRadioGroup.indexOfChild(selectedButton)
            val departmentName = selectedButton.text.toString()

            val intent = Intent(this, ProfessorActivity::class.java)
            intent.putExtra(UniversityActivity.EXTRA_UNIVERSITY_INDEX, universityIndex)
            intent.putExtra(UniversityActivity.EXTRA_UNIVERSITY_NAME, universityName)
            intent.putExtra(EXTRA_DEPARTMENT_INDEX, departmentIndex)
            intent.putExtra(EXTRA_DEPARTMENT_NAME, departmentName)
            startActivity(intent)
        }
    }
}
