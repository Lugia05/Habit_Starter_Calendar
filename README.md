# Habit_Starter_Calendar

## Introduction

My todo list project allows users to create, manage, and organize tasks efficiently. The program supports multiple types of tasks, including daily, weekly, and calendar-based tasks. It even provides tools for sorting, saving, loading, and undoing actions.

This project demonstrates the use of object-oriented programming principles and core data structures such as lists, maps, queues, and stacks.

Instructions:
    While the application is running, type in the corresponding numbers to navigate throughout the task manager. When promted, you can also type out the necessary information, then press ENTER to confirm.

Main Menu Options:
    Files
        -Save → Overwrites the current file
        -Save As → Creates a new file in the "Saves" folder
        -Load → Loads tasks from an existing file
    Add Task
        -Choose between Daily, Weekly, or Calendar task
        -Enter task details (title, description, priority, due time)
        -For weekly tasks: enter a day (e.g., Monday)
        -For calendar tasks: enter a date (YYYY-MM-DD)
    View Tasks
        -Displays all current tasks
    Complete Task
        -Select a task number to mark it as completed
    Undo Complete
        -Reverts the last completed task
    Sort Tasks
        -Sorts tasks by priority and due time using a custom algorithm
    Delete Task
        -Removes a task from the list
    Exit
        -Closes the application

Data Structures Used

ArrayList → Stores all tasks
HashMap → Stores weekly tasks by day
PriorityQueue → Orders tasks by priority and due time
Stack → Allows undoing completed tasks

Project Structure
Main.java → Handles user interface and menu navigation
Task.java → Base class for all tasks
TaskDaily.java → Daily recurring tasks
TaskWeekly.java → Weekly recurring tasks
CalendarTask.java → One-time scheduled tasks
TaskManager.java → Core logic, data handling, and file operations

Author: Erik Cabrera