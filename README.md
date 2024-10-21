# GitHub Application
This is my GitHub application project, where I have used the frameworks/libraries like dagger, retrofit, coroutines, MVVM, Room Db etc.

# Architecture used is MVVM(Model View View Model)
![MVVMImage](https://github.com/siddhant123-geek/AssignmentNewsApp/assets/82453362/6e8b338c-7c6c-43e9-aa2f-997679c22a1b)

# Detailed summary of the screens implemented
# HomeScreen
- It has a searchBar where you can search for any repositories
- Once the search button is hit, it shows a list of repositories related to the searched keyword
- It also has offline caching, where the first 15 repositories for seacrhed keyword are stored
- The cached repositories are shown in case there is no internet and with each successful api call, the cached repos are updated
# Repository Detail Screen
- When any repo on the HomeScreen is clicked, it navigates to the detail screen
- It shows various meta data related to the clicked repository
- It shows information like name, fullName, description, language, projectLink etc for the clicked repository
- When the projectLink is clicked, it naviges to the repository details on the Github website(In browser)
# HomeScreen with pagination screen
- Near to the search button on home page, there is a pagination button too.
- When the pagination button is clicked, it navigates to the home screen with pagination
- This screen shows the repositories for the searched keyword with pagination
- Initially it loads 10 items and it loads more on demand as the user scrolls down
# HomeScreen
![HomeScreenGitHubApp](https://github.com/user-attachments/assets/506abf8b-bc17-4184-9f19-60e55723faa3)

# Repository detail screen 
![repoDetailScreenGitHubApp](https://github.com/user-attachments/assets/b6469453-c39f-4954-b53b-582ba2c9f450)

# offline support
![offlineRepoDataStoredInRoomDb](https://github.com/user-attachments/assets/c7465e41-9eaf-4490-af3d-2c3705555c39)

# Dependencies used
# Dagger(For providing dependency)
- implementation "com.google.dagger:dagger:2.42"
- kapt "com.google.dagger:dagger-compiler:2.42"
# Glide(For image loading)
- implementation 'com.github.bumptech.glide:glide:4.11.0'
# Room Db(For offline support)
- implementation ("androidx.room:room-runtime:2.5.0")
- implementation ("androidx.room:room-ktx:2.5.0")
- kapt ("androidx.room:room-compiler:2.5.0")
# Paging3
- implementation 'androidx.paging:paging-runtime-ktx:3.2.1'
# Retrofit
- implementation 'com.squareup.retrofit2:retrofit:2.9.0'
- implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
