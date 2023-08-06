# TodoList
## Getting Started
1, Clone the repository:
```
git clone https://github.com/prakash-aathi/TodoList.git
```
2, configure your system mysql properties in application.properties

 3, To run 
```
mvn spring-boot:run
```
4, API documentation can be accessed
```
http://localhost:8080/swagger-ui/index.html#/
```

## API Endpoints
### User Endpoints:

- POST /admin/signup: Register a new admin user.
- POST /user/signup: Register a new regular user.
- POST /login: Authenticate user and get a JWT token.

### TodoList Endpoints:

-  POST /api/v1/todolist: Create a new todo list.
- GET /api/v1/todolist/{id}: Get a specific todo list by ID.
- GET /api/v1/todolist: Get all todo lists for the authenticated user.
= PUT /api/v1/todolist/{id}: Update the name of a todo list.
- DELETE /api/v1/todolist/{id}: Delete a todo list.

### TodoItem Endpoints:

- POST /api/v1/todoitem/{todolistId}: Create a new todo item in a specific todo list.
- GET /api/v1/todoitem/{id}: Get a specific todo item by ID.
- GET /api/v1/todoitem/todolist/{todolistId}: Get all todo items for a specific todo list.
- PUT /api/v1/todoitem/{id}: Update the details of a todo item.
- PUT /api/v1/todoitem/{id}/complete: Mark a todo item as completed.
- DELETE /api/v1/todoitem/{todolistId}/{id}: Delete a todo item
