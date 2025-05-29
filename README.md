# 📱 Contact App

A modern **Contact Manager App** built using **Jetpack Compose** and **MVVM architecture**. This app allows you to add, edit, delete, and call contacts. It supports full CRUD operations, real-time updates using Kotlin **Flow** with **Room Database**, and lets you pick profile images. You can also sort contacts by name or date.

---

## 🚀 Features

- 📇 **Add, Edit & Delete Contacts**
- 🖼️ **Image Picker** for profile pictures
- 📞 **Call Contacts** directly from the app
- 🔁 **Real-time updates** with Flow & Room
- 🔠 **Sort by Name or Date**
- ⚙️ Built using **Jetpack Compose**
- 🧠 **MVVM architecture**
- 💾 **Offline First** with Room DB

---

## 🧱 Built With

- ✅ Jetpack Compose
- ✅ Kotlin
- ✅ Room Database
- ✅ Kotlin Flow
- ✅ MVVM Architecture
- ✅ Image Picker API
- ✅ Intent for Calling
- ✅ Coroutines

---

## 📸 Screenshots

### Home Screen
<img src="https://github.com/upend123/Contact-APP/blob/main/screenshots/home.jpg" width="300" height="600" alt="Description">

### Add Or Update 
<img src="https://github.com/upend123/Contact-APP/blob/main/screenshots/AddEdit.jpg" width="300" height="600" alt="Description">

---
## 🛠️ Technologies Used

| Category           | Technology / Library             | Purpose                                              |
|--------------------|----------------------------------|------------------------------------------------------|
| 👨‍💻 Language        | Kotlin                           | Primary language for Android development             |
| 🎨 UI Framework    | Jetpack Compose                  | Declarative UI for building responsive UIs           |
| 🧠 Architecture    | MVVM (Model-View-ViewModel)      | Clean separation of UI and business logic            |
| 🗃️ Database        | Room Database                    | Local database with SQLite support                   |
| 🧩 DAO             | ContactDao.kt                    | Interface to access Room database                    |
| 🔄 Reactive Flow   | Kotlin Flow                      | Observe database changes in real-time                |
| 🔗 Repository      | ContactRepository.kt             | Abstraction layer for data operations                |
| 🧠 ViewModel       | ContactViewModel.kt              | ViewModel to store and manage UI-related data        |
| 🖼️ Image Handling  | Image Picker + Compression       | Select and compress contact images                   |
| ☎️ System Service  | Android Intent (Call feature)    | Make phone calls directly from the app               |
| 📦 Dependency Mgmt | Gradle                           | Manage dependencies and project build setup          |
| 💉 Dependency Inj. | Hilt                             | Inject dependencies cleanly across the app           |

---

## 📂 Folder Structure

```
MyContactApp/
├── .gradle/                      # Gradle system files (auto-generated)
├── .idea/                        # Android Studio project settings (auto-generated)
├── gradle/                       # Gradle wrapper files
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── build.gradle                  # Project-level Gradle config
├── settings.gradle               # Gradle modules and project settings
├── gradle.properties             # Gradle global properties
├── local.properties              # Local SDK paths (not version controlled)
├── app/
│   ├── build.gradle              # App module Gradle config
│   ├── proguard-rules.pro        # ProGuard rules for release builds
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── contactapp/
│   │   │   │               ├── app/
│   │   │   │               │   ├── BaseApplication.kt           # 🚀 Application class (init/context)
│   │   │   │               │   └── MainActivity.kt              # 🏠 App entry, Compose setup
│   │   │   │               ├── data/
│   │   │   │               │   ├── database/
│   │   │   │               │   │   ├── ContactDao.kt            # 🗃️ Room DAO
│   │   │   │               │   │   ├── ContactDatabase.kt       # 🏛️ Room DB instance
│   │   │   │               │   │   ├── ContactRepository.kt     # 🔄 Data flow (DB ↔ ViewModel)
│   │   │   │               │   │   └── ContactViewModel.kt      # 📊 ViewModel for contacts
│   │   │   │               │   ├── di/
│   │   │   │               │   │   └── DiModule.kt              # 💉 Dependency injection
│   │   │   │               │   └── entity/
│   │   │   │               │       └── Contact.kt               # 📌 Contact entity class
│   │   │   │               ├── presentation/
│   │   │   │               │   ├── navigation/
│   │   │   │               │   │   ├── AppNavigation.kt         # 🧭 Navigation logic
│   │   │   │               │   │   └── Routes.kt                # 🚏 Route names
│   │   │   │               │   └── screens/
│   │   │   │               │       └── splash/
│   │   │   │               │           ├── splashScreenUI.kt            # ✨ Splash UI
│   │   │   │               │           └── splashScreenViewModel.kt     # ⚙️ Splash logic
│   │   │   │               │       ├── AddEditScreenUI.kt       # 📝 Add/edit contact UI
│   │   │   │               │       └── MainScreenUI.kt          # 📋 Main contact list UI
│   │   │   │               ├── utils/
│   │   │   │               │   └── ImageCompress.kt             # 🖼️ Image compression utility
│   │   │   │               └── ui/
│   │   │   │                   └── theme/
│   │   │   │                       ├── Color.kt
│   │   │   │                       ├── Theme.kt
│   │   │   │                       └── Type.kt
│   │   │   ├── res/                # App resources (layouts, drawables, etc.)
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   └── drawable/
│   │   │   ├── test/
│   │   │   │   └── com.example.contactapp/
│   │   │   │       └── ExampleUnitTest.kt
│   │   │   └── androidTest/
│   │   │       └── com.example.contactapp/
│   │   │           └── ExampleInstrumentedTest.kt
│   └── libs/                     # Optional: local libraries


```
### 📥 Direct APK Download:
Click below to download the latest version of the APK directly:

➡️ **[Download APK](https://github.com/upend123/Contact-APP/releases/download/v1.0/Contact_App.apk)**

### 🚀 Running the App Locally (Android Studio):
1. Clone the repository:
   ```bash
   git clone https://github.com/upend123/Contact-APP.git
   ```
2. Open the project in **Android Studio**.
3. Connect your Android device or start an emulator.
4. Click on **Run** ▶️ to install and launch the app.

---

## 📜 License
This project is licensed under the **MIT License** – you're free to use and modify it.

💡 **Suggestions & Contributions are Welcome!** Feel free to submit issues and pull requests to improve the project.

---

💡 **Developed by [Upendra Yadav]**



