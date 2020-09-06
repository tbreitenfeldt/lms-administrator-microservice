# Library Management System
## Administrator Microservice

This microservice is apart of the Library Management System project. To clone the entire project use the recursive flag and this repository:

git clone --recursive https://github.com/tbreitenfeldt/library-management-system.git

### Description

Administrator service for Library Management System. Made with Spring Boot. For library system administrators, it provides full CRUD functionality for all tables in the LMS system.

|Task|Method|Body|
|---|---|---|
|Get all authors|GET /admin/authors||
|Create new author|POST /admin/authors|{"name": "Jim Jones"}|
|Update author|PUT /admin/author/:id|{"id":5, "name": "James Joyce"}|
|Delete author|DELETE /admin/author/:id||

Collections include: `authors`, `books`, `borrowers`, `loans`, `genres`, `branches`, and `publishers`.

