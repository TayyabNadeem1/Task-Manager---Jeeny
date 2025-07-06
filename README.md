# 📱 TaskManagerApp

TaskManagerApp is an Android application that allows users to efficiently manage their daily tasks. It supports Google Sign-In for authentication, task categorization, real-time filtering, and an intuitive, modern user interface built with Material Design principles. The app is backed by Firebase Firestore, ensuring real-time database operations and scalability.

---

## 🚀 Features

- ✅ **Google Sign-In** for secure authentication
- 📝 **Add New Tasks** with custom names
- 📂 **Task Categorization** — choose from `Personal`, `Work`, or `Learning`
- 🔍 **Search Functionality** to find tasks quickly
- 🎯 **Category Filtering** to display only selected types of tasks
- 🎨 **Material Design UI** with consistent theming and responsiveness
- 🔐 **User Authentication** via Firebase Auth
- ☁️ **Real-Time Data** storage using Firebase Firestore
- 🚪 **Popup Sign-Out Option** for easy logout

---

## 📸 Screenshots (Optional)

<!--
If available, include screenshots here:
- Home screen with user greeting and task list
- Add Task screen with category checkboxes
- Google Sign-In screen
-->

---

## 🛠️ Tech Stack

| Technology        | Purpose                         |
|-------------------|----------------------------------|
| Java              | Main language for Android code   |
| XML               | UI layouts and component styling |
| Firebase Auth     | Google Sign-In authentication    |
| Firebase Firestore| Task storage and queries         |
| RecyclerView      | Displaying task list             |
| Material Components| Modern UI/UX elements          |

---

## 📁 Folder Structure

TaskManagerApp/
│
├── app/
│ ├── java/com/example/taskmanager/
│ │ ├── model/TaskModel.java # Data model for task object
│ │ ├── adapter/TaskListAdapter.java # Adapter for RecyclerView
│ │ ├── AddTaskActivity.java # Add new task UI + logic
│ │ ├── HomeActivity.java # Main screen after login
│ │ ├── MainActivity.java # Handles Google Sign-In
│ │ └── ... # Additional Activities if needed
│ └── res/layout/
│ ├── activity_main.xml # Google Sign-In screen
│ ├── activity_home.xml # Home task list screen
│ ├── activity_add_task.xml # Task creation form
│ └── ...
│
└── README.md

📂 Firestore Database Schema
🔸 Collection: tasks
Each document in this collection represents a task created by a user.

📄 Document Structure (TaskModel)
json
Copy
Edit
{
  "taskId": "auto-generated", // Document ID (can be stored separately)
  "taskName": "Submit Assignment",
  "status": "PENDING", // or "COMPLETED", etc.
  "userId": "UID_FROM_FIREBASE_AUTH",
  "category": "Personal" // or "Work", "Learning"
}
🔑 Fields Explained:
Field	Type	Description
taskId	String	Unique task ID, assigned by Firestore and mapped locally
taskName	String	The name or title of the task
status	String	The current status of the task (e.g., PENDING, DONE)
userId	String	Firebase Auth User ID who created the task
category	String	Task classification: Personal, Work, or Learning



