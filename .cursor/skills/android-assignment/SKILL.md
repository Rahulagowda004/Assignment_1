---
name: android-assignment
description: Guides a complete Android beginner step by step through building the ENGG*6400 Assignment 1 "Intents and Multiple Activities" app (UniversityNavigator) in Kotlin with XML Views. Use when the user works on the UniversityNavigator project, Android Activities, explicit or implicit Intents, Intent extras, XML layouts, AndroidManifest, device rotation / onSaveInstanceState, Back/Exit buttons, Google Maps or website Intents, building the APK, or mentions the A1 assignment.
---

# Android Assignment 1 (UniversityNavigator)

The user is a **complete beginner** in Android development, building this for a university assignment while learning. Teach while building. Never dump a large amount of code at once.

## Project context

- Repo: `Intent/`. Android project: `UniversityNavigator/`. Assignment PDF: `Documents/A1 - Intents and Multiple Activities.pdf`.
- Package: `com.example.universitynavigator`. Code: `UniversityNavigator/app/src/main/java/com/example/universitynavigator/`. Layouts: `UniversityNavigator/app/src/main/res/layout/`.
- Build config: `UniversityNavigator/app/build.gradle.kts` (Kotlin DSL, version catalog in `gradle/libs.versions.toml`). Existing deps: appcompat, activity-ktx, core-ktx, constraintlayout, material. Do not add more.
- Starting state: `MainActivity.kt` and `activity_main.xml` are the Android Studio "Empty Views Activity" template (with `enableEdgeToEdge()` and a window-insets listener on `R.id.main`). `minSdk = 24`, `targetSdk = 37`.
- Division of tools:
  - **Cursor**: writing and editing all code and XML.
  - **Android Studio**: Gradle sync, building, running on emulator/device, Logcat, building the APK.
- Whenever a step requires Android Studio (Sync Project with Gradle Files, Run, emulator rotation, Build APK), say so explicitly with the exact menu path.

## Assignment requirements (check every change against these)

```
- [ ] R1  Main screen has two options: "Search University" and "Exit"
- [ ] R2  Search University -> new screen listing >= 4 universities; user selects one
- [ ] R3  Department screen -> >= 4 departments of the selected university; user selects one
- [ ] R4  Professor screen -> shows one professor of the selected department, and lets the user:
          - open the department location in Google Maps (implicit Intent)
          - open the professor's website (implicit Intent)
- [ ] R5  Exit terminates the application
- [ ] R6  At least two different widget types
- [ ] R7  Multiple Activities; selected university/department passed between Activities
- [ ] R8  Every Activity except MainActivity and implicit-Intent targets has a "Back" button
- [ ] R9  Handles rotation (portrait <-> landscape) and preserves relevant information
- [ ] R10 At most 7 Activities
- [ ] R11 Targets API 21 and later
```

Marking: 8/10 for meeting the specs, 2/10 for UI/UX quality.
Submission: zip with the final `.apk` and all project files, named `A1FirstLast.zip` (user's first and last name), uploaded to Dropbox on the course site by Sunday Oct 4, 11:59 pm. The user must demo the app to the instructor.

## Development rules (from the user)

- Use Kotlin.
- Use XML Views, NOT Jetpack Compose.
- Prefer simple Android APIs suitable for a beginner.
- Do not introduce architecture patterns, libraries, databases, networking, or other technologies unless the assignment actually requires them.
- Keep the implementation simple and easy to understand.
- Follow the assignment requirements exactly.
- Before making a significant change, briefly explain what you are changing and why.
- When I ask for code, give me the complete relevant file or clearly indicate exactly where the code goes.
- Do not modify unrelated files.
- If something needs to be configured in Android Studio rather than Cursor, tell me explicitly.
- If something needs to be tested in Android Studio, tell me explicitly.
- I am learning while building this. Do not just generate a large amount of code. Build the application incrementally with me and explain each major step.

Additional agent rules:
- No ViewModel, LiveData, Fragments, Navigation component, View Binding setup, Room, Retrofit, coroutines, or DI. Use `findViewById`.
- Do not use `android:configChanges` to avoid rotation handling.
- Put user-visible text in `res/values/strings.xml`.

## Teaching style

1. Work on **one roadmap step at a time**. At the start of a step, state in 1-3 sentences what will be built and why.
2. The first time an Android concept appears, explain it in 2-4 plain sentences. Concepts to cover when they come up:
   - **Activity**: one screen of the app, a Kotlin class extending `AppCompatActivity`.
   - **Layout XML**: describes what the screen looks like; loaded with `setContentView(R.layout.x)`.
   - **`R` class / `R.id`**: auto-generated references to resources; `findViewById(R.id.x)` gets a view from the layout.
   - **AndroidManifest.xml**: every Activity must be registered here or the app crashes when opening it.
   - **Explicit Intent**: "open this specific Activity of my app" (`Intent(this, X::class.java)`).
   - **Implicit Intent**: "open whatever app can handle this action/data" (e.g. Maps, browser).
   - **Intent extras**: key-value data attached to an Intent to pass information to the next Activity.
   - **Activity lifecycle / back stack**: Activities stack on top of each other; `finish()` closes the current one and reveals the previous.
   - **Configuration change**: rotation destroys and recreates the Activity; `onSaveInstanceState` saves state into a `Bundle` that comes back in `onCreate(savedInstanceState)`.
3. After writing code, briefly walk through the key lines (not every line).
4. End every step with a **"Test in Android Studio"** checklist (exact actions and expected results), then **stop and wait** for the user to confirm it works before starting the next step.
5. If a build error is reported, ask for the exact error text from Android Studio's Build or Logcat window if not provided, explain the cause, and fix only what is needed.

## Recommended design (keep it this simple)

**Activities (4 of max 7):**

| Activity | Shows | Back button |
|---|---|---|
| `MainActivity` | App title, "Search University", "Exit" | No |
| `UniversityActivity` | List of >= 4 universities | Yes |
| `DepartmentActivity` | Selected university name + >= 4 departments | Yes |
| `ProfessorActivity` | University, department, professor name/info, "Open in Google Maps", "Professor Website" | Yes |

**Data:** one file `UniversityData.kt` with a Kotlin `object` holding hard-coded data classes, e.g. `University(name, departments)` and `Department(name, mapQuery, professorName, professorTitle, professorWebsite)`. Use real universities, real department addresses for `mapQuery`, and real professor pages where possible.

**Widgets (satisfies R6):** `Button` (Search, Exit, Back, Maps, Website), `RadioGroup` + `RadioButton` or `ListView` for selection, `TextView` for labels/professor info. Pick one selection approach and use it consistently for universities and departments.

**Passing data (R7):** define key constants once (e.g. in `UniversityData` or a companion object) and use them on both sides:

```kotlin
val intent = Intent(this, DepartmentActivity::class.java)
intent.putExtra(UniversityData.EXTRA_UNIVERSITY_INDEX, universityIndex)
startActivity(intent)
// receiving side:
val universityIndex = intent.getIntExtra(UniversityData.EXTRA_UNIVERSITY_INDEX, -1)
```

Passing indexes (Int) is simplest; validate they are in range.

**Back (R8):** a `Button` that calls `finish()`.
**Exit (R5):** `finishAffinity()` in `MainActivity`.

**Implicit Intents (R4):**

```kotlin
val mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(department.mapQuery))
try {
    startActivity(Intent(Intent.ACTION_VIEW, mapUri))
} catch (e: ActivityNotFoundException) {
    // fallback: open Google Maps in the browser
    val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(department.mapQuery))
    startActivity(Intent(Intent.ACTION_VIEW, webUri))
}
```

Website: `Intent(Intent.ACTION_VIEW, Uri.parse(department.professorWebsite))` in a try/catch that shows a `Toast` if no browser exists. Use try/catch rather than `resolveActivity()`, which needs `<queries>` in the manifest on Android 11+.
Testing note: the default emulator image may lack Google Maps; use an emulator image with Google Play, or rely on the browser fallback.

**Rotation (R9):** in each Activity with a selection, save the selected index in `onSaveInstanceState(outState)` and restore it in `onCreate` from `savedInstanceState`. Data received via Intent extras survives rotation automatically (the same Intent is redelivered); explain this to the user. `RadioButton`/`ListView` views with IDs may restore some state on their own, but save the selection explicitly so the behavior is clear and gradeable. Make every layout usable in landscape (wrap content in a `ScrollView` where it could overflow).

**Window insets:** the template's `enableEdgeToEdge()` + insets listener on `R.id.main` keeps content out from under the status bar. Keep the same pattern in each new Activity (root view id `main`) and explain it briefly once.

## Known decision: minSdk

The assignment says "target API 21 and later", but the project has `minSdk = 24`. Before changing anything, explain to the user:
- `minSdk` is the lowest Android version the app installs on.
- Setting `minSdk = 21` matches the wording literally, but recent AndroidX library versions in the version catalog may require a higher minSdk and fail the build.
- Ask the user which they prefer (or whether to confirm with the instructor). Changing it is an edit to `app/build.gradle.kts` followed by **File > Sync Project with Gradle Files** in Android Studio.

## Incremental roadmap

Copy and track:

```
- [ ] Step 0: Orientation - explain project structure (manifest, java/, res/layout, res/values, build.gradle.kts); resolve minSdk decision; run the template app once in Android Studio
- [ ] Step 1: Main screen - activity_main.xml with title, "Search University" and "Exit" buttons; Exit works (R1, R5)
- [ ] Step 2: UniversityData.kt + UniversityActivity with >= 4 universities and Back button; register in manifest; Search University opens it (R2, R8)
- [ ] Step 3: DepartmentActivity - receives university via extras, shows >= 4 departments, Back button (R3, R7, R8)
- [ ] Step 4: ProfessorActivity - receives university + department, shows professor info, Back button (R4 display, R7, R8)
- [ ] Step 5: Maps and website implicit Intents with fallbacks (R4)
- [ ] Step 6: Rotation - onSaveInstanceState/restore on every screen with state; landscape-friendly layouts (R9)
- [ ] Step 7: UI/UX polish - strings.xml, consistent margins/text sizes, clear titles, disable "Next" until a selection is made or navigate on tap, app name/label
- [ ] Step 8: Build and package - Android Studio: Build > Generate App Bundles or APKs > Generate APKs (debug APK is at app/build/outputs/apk/debug/app-debug.apk); create A1FirstLast.zip with APK + project (exclude build/ and .gradle/ folders to keep it small)
```

Each step's "Test in Android Studio" checklist should include: Run the app (green Run button), the exact taps to perform, expected result, and for Step 6 onward, rotating the emulator (rotate buttons in the emulator toolbar; ensure auto-rotate is on in the emulator's quick settings).

## Final verification

Before declaring the app done, produce a table mapping each requirement R1-R11 to the file(s) and code that satisfy it, confirm the Activity count (<= 7), confirm all Activities are in `AndroidManifest.xml`, and have the user run through the full flow in portrait and landscape on the emulator.
