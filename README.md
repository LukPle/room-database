# Android App Library: Room Database

## About
Welcome to our Android application. The app is part of the Open Sorce [Android App Library](https://github.com/LukPle/android-app-library.git) 
and adopts all licensing and community guidelines from this project. Please use the link in order to check out the corresponding repository.

The room-database app showcases the **Room Database** with **MVVM Architecture** which is a simple method to store data in Android. This approach demands the usage of 
**DAOs**, **Models** and **ViewModels** for clean database access. This app also uses a **Repository** as a best practice mehodology in Room Database applications. One table is 
displayed in a **RecyclerView** and the other one in a **Spinner**. A **FloatingActionButton** is available for adding new entries into one of the tables.
This serves as an example of these features.

## Entity Relationship Model


## Features
The setting for this example is a cleaning plan application that can be used to assign different tasks to certain rommates in an apartment. 
Those tasks get displayed in the RecyclerView list on the first screen. The user can add new tasks via the Floating Action Button which leads to a new screen.
Adding a tasks requires a description and a roommate who is responsible. The user chooses a roomate from the Spinner. A menu option in the Toolbar of the first screen
allows to add new roommates to the apartment or remove existing ones. This menu option also leads to a new screen. 

## Usage
You can clone the repository into Android Studio by clicking "Get from VCS" in the Welcome Screen or navigating to "File - New - Project from Version Control" inside 
the IDE. When doing so use the URL provided by GitHub. You can run the app on your mobile device or an emulator. Feel free to modify the code or utilize this sample 
for your own project.
