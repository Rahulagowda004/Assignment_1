package com.example.universitynavigator

data class Department(
    val name: String,
    val professorName: String,
    val mapQuery: String,
    val professorWebsite: String
)

object UniversityData {

    // Must stay in the same order as the radio buttons in activity_university.xml
    val departmentsByUniversity: List<List<Department>> = listOf(
        // University of Guelph
        listOf(
            Department(
                "School of Computer Science",
                "Dr. Stefan Kremer",
                "Reynolds Building, University of Guelph, Guelph, ON",
                "https://www.uoguelph.ca/profile/stefan-c-kremer"
            ),
            Department(
                "School of Engineering",
                "Dr. Petros Spachos",
                "Thornbrough Building, University of Guelph, Guelph, ON",
                "https://www.uoguelph.ca/engineering/people/petros-spachos-phd"
            ),
            Department(
                "Department of Physics",
                "Dr. Paul Garrett",
                "MacNaughton Building, University of Guelph, Guelph, ON",
                "https://www.uoguelph.ca/profile/paul-garrett"
            ),
            Department(
                "Department of Mathematics and Statistics",
                "Dr. Hermann Eberl",
                "MacNaughton Building, University of Guelph, Guelph, ON",
                "https://www.heberl.uoguelph.ca/"
            )
        ),
        // University of Waterloo
        listOf(
            Department(
                "Cheriton School of Computer Science",
                "Dr. Ian Goldberg",
                "Davis Centre, University of Waterloo, Waterloo, ON",
                "https://cs.uwaterloo.ca/~iang/"
            ),
            Department(
                "Electrical and Computer Engineering",
                "Dr. Catherine Rosenberg",
                "EIT Building, University of Waterloo, Waterloo, ON",
                "https://uwaterloo.ca/electrical-computer-engineering/profile/cath"
            ),
            Department(
                "Mechanical and Mechatronics Engineering",
                "Dr. Kevin Musselman",
                "Engineering 5, University of Waterloo, Waterloo, ON",
                "https://uwaterloo.ca/mechanical-mechatronics-engineering/profile/kmusselm"
            ),
            Department(
                "Civil and Environmental Engineering",
                "Dr. Mahesh Pandey",
                "Carl A. Pollock Hall, University of Waterloo, Waterloo, ON",
                "https://uwaterloo.ca/civil-environmental-engineering/profile/mdpandey"
            )
        ),
        // University of Toronto
        listOf(
            Department(
                "Department of Computer Science",
                "Dr. Sheila McIlraith",
                "Bahen Centre for Information Technology, University of Toronto, Toronto, ON",
                "https://www.cs.toronto.edu/~sheila/"
            ),
            Department(
                "Electrical and Computer Engineering",
                "Dr. Deepa Kundur",
                "Sandford Fleming Building, University of Toronto, Toronto, ON",
                "https://www.ece.utoronto.ca/people/kundur-d/"
            ),
            Department(
                "Mechanical and Industrial Engineering",
                "Dr. Mark Fox",
                "5 King's College Road, Toronto, ON",
                "https://www.mie.utoronto.ca/faculty_staff/fox/"
            ),
            Department(
                "Department of Physics",
                "Dr. Aephraim Steinberg",
                "McLennan Physical Laboratories, 60 St George St, Toronto, ON",
                "https://www.physics.utoronto.ca/members/steinberg-aephraim-m/"
            )
        ),
        // McMaster University
        listOf(
            Department(
                "Department of Computing and Software",
                "Dr. Ryszard Janicki",
                "Information Technology Building, McMaster University, Hamilton, ON",
                "https://www.cas.mcmaster.ca/~janicki/"
            ),
            Department(
                "Electrical and Computer Engineering",
                "Dr. Ali Emadi",
                "Information Technology Building, McMaster University, Hamilton, ON",
                "https://www.eng.mcmaster.ca/ece/faculty/dr-ali-emadi/"
            ),
            Department(
                "Department of Mechanical Engineering",
                "Dr. Stephen Veldhuis",
                "John Hodgins Engineering Building, McMaster University, Hamilton, ON",
                "https://www.eng.mcmaster.ca/mech/faculty/dr-stephen-c-veldhuis/"
            ),
            Department(
                "Department of Chemical Engineering",
                "Dr. Todd Hoare",
                "John Hodgins Engineering Building, McMaster University, Hamilton, ON",
                "https://www.eng.mcmaster.ca/chemeng/faculty/dr-todd-hoare/"
            )
        )
    )
}
