package com.example.universitynavigator

object UniversityData {

    // Must stay in the same order as the radio buttons in activity_university.xml
    val departmentsByUniversity: List<List<String>> = listOf(
        // University of Guelph
        listOf(
            "School of Computer Science",
            "School of Engineering",
            "Department of Physics",
            "Department of Mathematics and Statistics"
        ),
        // University of Waterloo
        listOf(
            "Cheriton School of Computer Science",
            "Electrical and Computer Engineering",
            "Mechanical and Mechatronics Engineering",
            "Civil and Environmental Engineering"
        ),
        // University of Toronto
        listOf(
            "Department of Computer Science",
            "Electrical and Computer Engineering",
            "Mechanical and Industrial Engineering",
            "Department of Physics"
        ),
        // McMaster University
        listOf(
            "Department of Computing and Software",
            "Electrical and Computer Engineering",
            "Department of Mechanical Engineering",
            "Department of Chemical Engineering"
        )
    )
}
