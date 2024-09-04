# CMMSmobile
This is a Computer Maintenance Management System written in Kotlin for Android Application only

# My Android Application

## Overview

This application is designed to manage various aspects of Maintenance documentation,Checklist per Work Order,Contract management,History of Asset,Ticketing System. 
It leverages modern Android development practices, including Clean Architecture and MVVM (Model-View-ViewModel), to ensure a robust and maintainable codebase.

## Features

- **User Authentication:** Secure login and user management.
- **Contract Management:** Create, update, and view contracts.
- **Customer Management:** Manage customer information and view details.
- **Dashboard Views:** Comprehensive dashboards for quick insights and data access.
- **Settings and Configuration:** Customize application settings and configurations.

## Project Structure

The project is organized into several key directories, each serving a specific purpose within the application:

### Directory Breakdown

- **Adapter:** Contains adapters for handling data binding in RecyclerViews and other list-based UI components. Custom adapters are created to provide a seamless and dynamic user experience.

- **api:** Defines network-related operations, including communication with backend services.
  - **Login:** Manages user authentication endpoints and services.
  - **SyncData:** Handles data synchronization tasks with remote servers.

- **Dao:** Houses Data Access Object (DAO) interfaces for Room database operations, enabling efficient CRUD operations on local data.

- **DataClasses:** Contains various data models used across the application, such as entities and data transfer objects (DTOs).

- **DataClasseUNused:** (Deprecated or unused data classes, which should be either removed or archived to keep the codebase clean.)

- **Fragments:** Comprises various fragments for different application features and screens.
  - **Configuration:** Fragments related to application settings and configuration options.
  - **Contracts:** Fragments for managing contract-related data.
  - **Customers:** Fragments for customer management and details.
  - **dashboardCustomer:** Fragments for displaying customer dashboard views.
  - **Equipments:** Fragments for managing equipment and inventory.
  - **HomeDashboard:** Main dashboard for a quick overview of the application's key metrics and features.
  - **Inventory:** Fragments for managing and tracking inventory items.
  - **Settings:** Fragments for application settings.
  - **SpecialTools:** Fragments for specialized tools and features.
  - **TechnicalCases:** Fragments for managing technical cases and support tickets.
  - **UserCreationFragment:** Fragments for user creation and management.
  - **WorkOrders:** Fragments for handling work orders and related tasks.

- **LoginPageComposed:** Contains composable functions and UI components specifically for the login page, using Jetpack Compose for modern UI development.

- **Models:** Includes ViewModels that act as a bridge between the UI and the data layer, handling data operations and UI updates.

- **Repository:** Manages data operations, abstracting data sources such as local databases and remote APIs. This layer ensures a clean separation between data and business logic.

## Architecture

The application follows Clean Architecture principles to ensure a separation of concerns:

- **Data Layer:** Handles data operations and interactions with external sources (e.g., API, database). Implemented in the Dao, api, and Repository directories.
- **Domain Layer:** Contains business logic and domain entities. Repositories are defined in the Repository directory.
- **Presentation Layer:** Manages UI components and interactions, implemented in the Fragments and Models directories. ViewModels in the Models directory handle data retrieval and state management.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss any changes or improvements.

## Contact

For more information, please contact [giorgoskouvarakis@gmail.com](mailto:giorgoskouvarakis@gmail.com).

## License

This project is licensed under the MIT License.
