# To-Do List App

The To-Do List app helps users efficiently manage their daily tasks with a simple and intuitive UI. Built using Jetpack Compose, the app allows users to add, update, and delete tasks, with data stored locally using Room Database. It follows the MVVM (Model-View-ViewModel) architecture for separation of concerns, ensuring scalability and maintainability.

## Features

- Add, edit, and delete tasks
- Persist data locally using Room Database
- Simple and responsive UI with Jetpack Compose
- MVVM architecture for scalable and maintainable code
- Real-time updates with ViewModel, LiveData, and StateFlow

## Architecture

This app follows the **MVVM (Model-View-ViewModel)** pattern:
- **Model:** Handles the data layer using Room Database.
- **View:** Jetpack Compose is used for building the UI.
- **ViewModel:** Manages the UI-related data and handles business logic using LiveData/StateFlow.

## Technologies Used

- **Jetpack Compose** - UI toolkit for building native Android UIs.
- **Room Database** - SQLite abstraction library for local data persistence.
- **MVVM Architecture** - For separation of concerns.
- **LiveData/StateFlow** - For observing changes in data and real-time UI updates.
- **Kotlin** - Language used for development.

## Installation

1. Clone this repository:
    ```bash
    git clone https://github.com/yourusername/todo-list-app.git
    ```
2. Open the project in Android Studio.
3. Build and run the app on an emulator or a physical device.

## Usage

- **Add Task:** Click the "Add Task" button, enter task details, and save.
- **Edit Task:** Tap on a task to modify its details.
- **Delete Task:** Swipe left or tap the delete icon to remove a task.

![Screenshot_20240908_145424](https://github.com/user-attachments/assets/9608ddeb-1ada-4396-ad8c-4844c5798f79)

