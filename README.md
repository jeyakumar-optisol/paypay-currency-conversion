# Paypay-Android

## Table of contents
1. [Project Structure](#project-structure)
    1. [Groups](#groups)
    1. [Dependencies](#dependencies)
2. [Architecture](#architecture)
    1. [Model](#model)
    2. [View](#view)
    3. [View Model](#view-model)
    4. [Repository] (#repository)
    5. [Interface] (#repository-contractor)
3. [Code Structuring]
    1. [Code Formatting]
    2. [Function Formatting]
    3. [Braces]
4. [Naming](#naming)
    1. [Layout Naming]
    2. [Class Naming]
    3. [Function Naming]
5. [Programming Practices](#programming-practices)
   1. [General](#general)
6. [Git Structure](#git-structure)
   1. [Commits]
   2. [Branch Names](#branch-names)
   3. [Merge Requests](#merge-requests)

# Project structure

## Groups

Paypay app is organized in a workspace consisting of several modules,

1. Navigation Component - responsible for the navigation of the application
2. Environment - Should have a production and testing environment
3. Support module - used for storing all the extensions used within the application, the module could contain utility
4. Data module - contains dependency injection and repository of the application
5. Domain module - contains contract, interface, model, enum of the application
6. Resources - the place for all the image files and fonts of the application
7. BuildSrc - Kotlin DSL build source have implemented to list the libraries used within the Paypay application
8. Architecture - MVVM with Clean Architecture should have to be followed with Multimodule for app extensibility in future
9. Code Inspection - **Sonarqube** are tool to inspect the code and prevent memory leaks and help to identify security leaks, **Kotlin Lint** should

## Dependencies

- Kotlin DSL as gradle
- AndroidX
- Jetpack Components
- Kotlin
- Hilt & Hilt testing
- Data Store
- Shared Preferences
- Firebase Crashlytics/Remote Config
- Coroutines & Coroutine test
- Room db

### Internal dependencies:
- Material Library
- Core Ktx
- Anko commons
- Gson
- Android's Activity/Fragment/Viewpager/Recyclerview
- Retrofit
- Okhttp & Okhttp interceptor
- Image Processing (Todo)
- Test tools (Junit4, Espresso)
- Intuit SDP & SSP
- ViewBinding/DataBinding


### Third-party dependencies:
- Timber
- Event bus
- Sonarqube

### Additional dependencies:
- Sonarqube - is used to inspect the code and detect the bugs within the code and security risks (https://www.sonarqube.org/success-download-community-edition/) and Android Studio also has a plugin to inspect a  code Settings > Plugin > Sonarqube


# Architecture

MVVM (Model-View-ViewModel) with Kotlin and Clean Architecture with SOLID (Single Responsibility) Pattern
- Multi-Moduled, modules should have been classified into 4  modules
    1. UI Layer (App Module)
    2. Data Layer (as a separate module)
    3. Domain Layer (as a separate module)
    4. BuildSrc Layer (as a separate module)

Ui Layer should have Activity/Fragment/Service/View Utility and Every activity/Fragment have implemented with ViewBinding/DataBinding and it has to be done with the BaseActivity/BaseFragment
eg.,

[Activity]
``` Kotlin
@AndroidEntryPoint  
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(  
    R.layout.activity_splash,  
  SplashViewModel::class.java  
) {
}
```
>  view binding will be accessed by extending BaseActivity with constructor parameters,
``eg. binding.someEditText``

[Fragment]
```kotlin
@AndroidEntryPoint  
class ExampleFragment :  
    BaseFragment<FragmentExampleBinding, ExampleFragmentViewModel>(  
        FragmentExampleBinding::inflate, ExampleFragmentViewModel::class.java  
  ) {}
```

## Model
The model should be present on the domain layer, and it could be divided into Networking models/Data models/Room Entity
1. Networking Models should be classified as,
- response model - `Response` suffix in the name
- request model - `Parameters` suffix in the name
  `` Could stored under Domain > ..package name.. > Repository > Remote > "ApiName"``
2. Data Transfer Object model - `DTO` suffix in the name, used for transferring data in navigation.
   ``could be stored under Domain > ..package name.. > model > "ShortNameModel" as naming convention``
3. Room Entity - entities have to be stored under Domain > entity


## View
- Views have to be built within **Android XML** and have to be bind within **ViewBinding**
- As possible data could be bounded with a Databinding
- Listener should be only handled within activity/fragment not with a ViewBinding
- Layout should support LTR-RTL


## View Model
ViewModel in Paypay should be extended with BaseViewModel and it has loader hide/show function and loader canceled event handling
- overridden onCreate method
- repository access
- implementing BaseViewModel override method and handling when needed

Template for basic ViewModel implementation:

```kotlin
@HiltViewModel  
class ExampleFragmentViewModel @Inject constructor(application: Application) :  
    BaseViewModel(application) {  
  
    @Inject  
  lateinit var restDataSource: RestDataSource  
  
  @Inject  
  lateinit var dataStoreDataSource: IDataStoreDataSource  
  
  @Inject  
  lateinit var preferencesDataSource: IPreferencesDataSource  
  
  override fun onCreate() {  
        //noop  
  }   
}
```

`@HiltViewModel` describes ViewModel could be accessible to dependency injections, and it could be done with `Inject`

Repository Interface could be injected with the `@Inject` annotation,

`Context` could be accessible from `BaseViewModel`

ViewModels and ViewData are subject to unit tests.

## Repository
the repository could be present on the Data layer and it will be implemented with the domain layer interface,

``` Kotlin
class RestRepository @Inject constructor(private val apiRestService: ApiRestService) :  
    RestDataSource {  
  
    override suspend fun fooPost(  
        foo: String,  
  requestFooPost: RequestFooPost,  
  ): Result<ResponseFooPost> {  
        return apiRestService.fooPost(foo, requestFooPost)  
    }
 }
```
In Dependency Injection module could be
``` Kotlin
@Module  
@InstallIn(SingletonComponent::class)  
object NetworkModule {  
  
    @Provides  
    @Singleton  fun restRepository(apiRestService: ApiRestService): RestDataSource {  
        return RestRepository(apiRestService)  
    }  
}
```

## Interface

``` Kotlin
interface RestDataSource {  
    suspend fun fooPost(foo: String, requestFooPost: RequestFooPost): Result<ResponseFooPost>  
}
```

# Code Structuring

## Code Formating

All code should be formatted with the Android Studio code formatting tool, *unused imports* will be cleared while committing

> File > Code > Reformat
> Shortcut key "Ctrl + Alt + L"

## Braces

Statement with Curly braces strictly followed with the curly brace,
strictly applicable for,
- If
- When
- While
- Do-While
- For

# Naming

## Layout Naming
will be in snack casing,

- Activity
- Fragment
>  type_short_name

eg.  
``activity_short_name``
``fragment_short_name``

**Adapter item:**
``item_short_name``

**Include layout:**
``include_short_name``

## Class Naming
will be in camel casing
- Activity
- Fragment
- ViewModel
>  ShortNameType

eg.  
``ShortNameActivity``
``ShortNameFragment``
``ShortNameViewModel``

**Function Naming:**
will be in camel case starts with lower case

eg.
``doSomething()``
``executeSomething()``

## Functions formatting
functions parameters could be separated into lines,

eg.
``` Kotlin
private fun doSomething(
params1:Int,
params2:Int
) {  

}
```
> Note: formatting not applicable for single parameterized function

# Programming practices
## General
- using !! Not-Null operator are strictly avoid, instead of use.
``` Kotlin
  if(nullableVariable?:value){
  ..
  }
  if(nullableBoolean == true){
  }
  if(nullableString.isEmpty() == true){
  }
  nullableVariable?.let{..}.orElse{..} //with orElse inline function
```

> use **orElse**  function to handle null check on **Let**, **also**, **run**
``` Kotlin
//?.let{}.orElse{}  
inline fun <R> R?.orElse(block: () -> R): R {  
    return this ?: block()  
}
```

# Git Structure

## Commits
- Commit **Squash/Rebase** will be done only on the task branch, if two more collaborators use same branch they should indicate on the channel before **Force Push**

## Branch names
Each Git Branch should be named in the following manner:
```
INITIALS/TICKET-NUMBER/TICKET-NAME
```
Example:
```
MD/DP-1/Create-UI-Components-Library
```


## Merge Requests
Each Merge Request should be structured in the following manner and also contain an appropriate description:

```
INITIALS/TICKET-NUMBER/TICKET NAME
```
Example:

```
MD/DP-1/Create UI Components Library
```
