# Speedo Transfer
**here is a SS of Our Live APP** : https://drive.google.com/drive/folders/1aF2IJDrFDoHasnnnaAmOz0cUjnbuyXD1?usp=sharing

**Speedo Transfer** is an Android application utilizing Jetpack Compose for online authentication and advanced Android topics. This app is designed to provide **secure and convenient money transfers**, incorporating features similar to online banking and mobile money apps. It also implements a **distributed system** requirement, with the backend accessed via Retrofit.

## Jetpack Compose

This project leverages **Jetpack Compose**, a modern UI toolkit for building native Android applications. Compose allows for creating intuitive, responsive, and dynamic UIs with less code and reduced complexity compared to the traditional XML-based UI approach.

### Key Features of Jetpack Compose:
- **Declarative UI**: Compose follows a declarative programming model, allowing you to describe what the UI should look like for a given state, with automatic handling of rendering and updates.
- **Kotlin Integration**: Built entirely with Kotlin, making it concise, powerful, and highly interoperable with existing Android code.
- **State Management**: Powerful state handling with `State`, `MutableState`, and `remember`, making UI state management easier across components.
- **Composable Functions**: The core building blocks of Compose are `@Composable` functions, which define reusable UI components.

## Architecture

The **MVVM (Model-View-ViewModel)** architecture pattern is used in this project, providing a clear separation of responsibilities and better organization of the codebase. MVVM helps abstract the logic of actions that can be performed in the app and ensures maintainability and testability.


![Alt Text](https://camo.githubusercontent.com/f6f33630466cd0414b9f93eae4fcfbd0c6ef02e8ac0f919f428b1dae4d3413f1/68747470733a2f2f6d69726f2e6d656469756d2e636f6d2f6d61782f3730302f312a4d58763452366c70595a505657466f55656158626a672e706e67)

## Built With 🛠

- **Kotlin**: A modern programming language for Android development, offering powerful language features.
- **Jetpack Compose**: A modern toolkit for building Android UIs, used to create a reactive and flexible UI.
- **Android Architecture Components**: A collection of libraries that help in building robust, testable, and maintainable apps.
  - **ViewModel**: Stores UI-related data that persists through configuration changes.
  - **Navigation Component**: Manages in-app navigation, allowing users to move seamlessly between screens.
  - **LiveData**: Reactive data holders that notify UI components when the data changes.
  - **Data Binding**: Binds UI elements in layouts to data sources in a declarative manner.
- **Kotlin Coroutines**: Simplifies asynchronous programming in Android by handling long-running tasks efficiently.
- **Retrofit**: A type-safe HTTP client for Android and Java, used for making API requests. Includes `converter-gson` for converting JSON responses into Kotlin data classes.
- **Accompanist Pager & Pager Indicators**: Extensions to Jetpack Compose that add advanced functionality like page swiping and indicators.
- **Glide**: An efficient open-source media management and image loading framework, used for loading and caching images.
