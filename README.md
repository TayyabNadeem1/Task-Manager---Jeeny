# 📋 TaskManagerApp – Android To-Do App with Firebase 🔥

A simple and clean task management Android application that lets users sign in with Google, add categorized tasks, filter and search them, and store everything securely on Firebase.

---

## 🚀 How to Run the Project

### 🔧 Prerequisites
- Android Studio Hedgehog or later
- Android SDK (API 33+)
- Firebase Project Setup
- Internet connectivity

### 🔌 Firebase Setup
1. Go to [Firebase Console](https://console.firebase.google.com/).
2. Create a new project (or use an existing one).
3. Register your app's package name (e.g. `com.example.taskmanager`) in Firebase.
4. Download the `google-services.json` file and place it in `app/` folder.
5. Enable **Authentication → Sign-in method → Google** in Firebase console.
6. Enable **Cloud Firestore** in test mode and create a collection named `tasks`.

### 🛠️ Project Setup
1. Clone this repo or import the project into Android Studio.
2. Sync Gradle files and let dependencies resolve.
3. Update `default_web_client_id` in `strings.xml` with your Firebase client ID.
4. Run the project on an emulator or device.

---

## ✅ Features

- 🔐 Google Sign-In with Firebase Authentication
- ➕ Add task with category: `Personal`, `Work`, or `Learning`
- 🔍 Search tasks by name
- 🧮 Filter tasks by category
- ☁️ Firebase Firestore integration for cloud storage
- ✨ Responsive UI using Material Design
- ⛔ Sign-out with popup menu

---

## 🔍 Assumptions Made

- The user is always authenticated using Google Sign-In before using the app.
- Only authenticated users can access and manage their tasks.
- Each task belongs to only one category (enforced via checkboxes).
- All tasks are stored in a single `tasks` collection, scoped by `userId`.

---

## 🚧 Possible Improvements

If I had more time, I would:
- ✅ Add task **completion toggle** and **status tracking**
- ⏰ Add **due date**, **priority** levels, and sorting options
- 📦 Use **MVVM architecture** with **ViewModel** and **LiveData**
- 🎨 Add more animations, Lottie feedbacks, and dark mode support
- 🔔 Add **local + push notifications** for upcoming tasks
- 💬 Add support for **offline mode** and sync
- 👥 Enable **collaborative tasks** with multiple users
- 🔒 Implement Firestore **security rules** for tighter access control

---

## 📱 Screenshots
## Login Page
![image](https://github.com/user-attachments/assets/3c57ff14-f7a3-4140-901b-010fb74f67bc)
## Home Page
![image](https://github.com/user-attachments/assets/93c7c2f5-6d2c-45f9-98d2-9e9f93f1b53c)
## Category filer Implemented
![Screenshot 2025-07-06 232015](https://github.com/user-attachments/assets/cd43b1f9-0190-466d-a3a5-54e93d29fc8a)
## Add Task Page
![image](https://github.com/user-attachments/assets/a54dc64c-3730-45fa-86c4-3dd503f508a3)

---

## 👨‍💻 Author
**Tayyab Nadeem** – [LinkedIn](https://linkedin.com)

---

## 📝 License
This project is open-sourced under the MIT License.
