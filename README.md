# SSSS (Super Simple Scheduling System)

# Architecture

The tools used for the application are:

- **Gradle:** as dependency and task manager. It is simpler than maven because it uses groovy and it is easier to read and modify.
- **SpringBoot:** for the application start up. It generates a simple jar file and executes it from command line, independent from an application server.
- **Spring Data:** for the data management. It has the repositories, and they makes the data management and the queries simpler.
- **Spring MVC:** for the rest controllers. It makes the rest mapping and management in a simple way. And also with the autowired annotation, it helps to manage the components in every place of the application.

For data management, it is using H2 database in memory.

The structure of the application is:

- **Main application:** `org.exercise.ssss`
- **Model entities:** `org.exercise.ssss.model`
- **Repositories:** `org.exercise.ssss.dao`
- **Services:** `org.exercise.ssss.services`
- **Rest controllers:** `org.exercise.ssss.controller`
- **Database initialization:** `org.exercise.ssss.initdb`

# Running

Run the application with the command:

    gradle bootRun

# Endpoints

## Students

### List

```
GET /api/students/
```

### Search

```
GET /api/students/?keyword=[keyword]
```

### Get

```
GET /api/students/{id}
```

### Add

```
POST /api/students/{id}
{
  "studentId": String,
  "lastName": String,
  "firstName": String
}
```

### Update

```
PUT /api/students/{id}
{
  "studentId": String,
  "lastName": String,
  "firstName": String
}
```

### Delete

```
DELETE /api/students/{id}
```

### Classes

```
GET /api/students/{id}/classes
```

## Classes

### List

```
GET /api/classes/
```

### Search

```
GET /api/classes/?keyword=[keyword]
```

### Get

```
GET /api/classes/{id}
```

### Add

```
POST /api/classes/{id}
{
  "code": String,
  "title": String,
  "description": String
}
```

### Update

```
PUT /api/classes/{id}
{
  "code": String,
  "title": String,
  "description": String
}
```

### Delete

```
DELETE /api/classes/{id}
```

### Classes

```
GET /api/classes/{id}/students
```
