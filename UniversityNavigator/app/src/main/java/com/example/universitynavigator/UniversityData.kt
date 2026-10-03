package com.example.universitynavigator

data class Department(
    val name: String,
    val professorName: String
)

object UniversityData {

    // Must stay in the same order as the radio buttons in activity_university.xml
    val departmentsByUniversity: List<List<Department>> = listOf(
        // University of Guelph
        listOf(
            Department("School of Computer Science", "Dr. Stefan Kremer"),
            Department("School of Engineering", "Dr. Petros Spachos"),
            Department("Department of Physics", "Dr. Paul Garrett"),
            Department("Department of Mathematics and Statistics", "Dr. Hermann Eberl")
        ),
        // University of Waterloo
        listOf(
            Department("Cheriton School of Computer Science", "Dr. Ian Goldberg"),
            Department("Electrical and Computer Engineering", "Dr. Catherine Rosenberg"),
            Department("Mechanical and Mechatronics Engineering", "Dr. Kevin Musselman"),
            Department("Civil and Environmental Engineering", "Dr. Mahesh Pandey")
        ),
        // University of Toronto
        listOf(
            Department("Department of Computer Science", "Dr. Sheila McIlraith"),
            Department("Electrical and Computer Engineering", "Dr. Deepa Kundur"),
            Department("Mechanical and Industrial Engineering", "Dr. Mark Fox"),
            Department("Department of Physics", "Dr. Aephraim Steinberg")
        ),
        // McMaster University
        listOf(
            Department("Department of Computing and Software", "Dr. Ryszard Janicki"),
            Department("Electrical and Computer Engineering", "Dr. Ali Emadi"),
            Department("Department of Mechanical Engineering", "Dr. Stephen Veldhuis"),
            Department("Department of Chemical Engineering", "Dr. Shiping Zhu")
        )
    )
}
