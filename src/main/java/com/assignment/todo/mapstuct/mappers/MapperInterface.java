package com.assignment.todo.mapstuct.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.assignment.todo.dto.UserRequest;
import com.assignment.todo.dto.UserResponse;
import com.assignment.todo.dto.request.TodoItemRequest;
import com.assignment.todo.dto.response.TodoListResponse;
import com.assignment.todo.model.TodoItem;
import com.assignment.todo.model.TodoList;
import com.assignment.todo.model.UserModel;

@Mapper(componentModel = "spring")
public interface MapperInterface {
    
    MapperInterface INSTANCE = Mappers.getMapper(MapperInterface.class);

    @Mapping(target = "email", source = "email")
    UserModel UserRequestToUserModel(UserRequest userRequest);

    @Mapping(target = "id", source = "id")
    UserResponse UserModelToUserResponse(UserModel userModel);

    @Mapping(target = "id", source = "id")
    TodoListResponse TodoModelToTodoResponse(TodoList todoList);

    @Mapping(target = "title", source = "title")
    TodoItem TodoItemRequestToTodoItem(TodoItemRequest todoItemRequest);

}
