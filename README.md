# ImgurUserGallery

ImgurUserGallery is a Spring Boot-based RESTful application. The application allows users to register, manage basic information, and seamlessly interact with Imgur's API for uploading, viewing, and deleting images. The application employs an H2 in-memory database for storing user information and leverages Spring Data JPA for seamless data access.

## Features

- **User Registration:** Register users with basic information, including a username and password.
- **Image Management:** Associate, upload, view, and delete images seamlessly.
- **Imgur API Integration:** Interact with Imgur's API for efficient image operations.
- **Data Storage:** Utilizes H2 in-memory database for efficient and lightweight data storage.
- **RESTful Endpoints:** Clean and intuitive REST API for a straightforward user experience.

## Getting Started

1. Clone the repository.
2. Configure Imgur API credentials in `application.properties`.
3. Build and run the Spring Boot application.

##Endpoints

```
POST /api/user/register : Register a new user.
GET /api/user/{username} : View user basic information.
GET /api/images/view/{username} : View user's associated images.
POST /api/images/upload/{username} : Upload an image.
DELETE /api/images/delete/{imageId} : Delete an image.

```
##Configuration

Configure Imgur API credentials in the `application.properties` file:

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Imgur API configuration
imgur.api.client-id=YOUR_CLIENT_ID
imgur.api.client-secret=YOUR_CLIENT_SECRET
```

Note: This project does not include a user interface and focuses on REST API functionality.


Make sure to replace placeholders like `YOUR_CLIENT_ID` and `YOUR_CLIENT_SECRET` with your actual Imgur API credentials. Additionally, include any specific instructions or details relevant to your project.
