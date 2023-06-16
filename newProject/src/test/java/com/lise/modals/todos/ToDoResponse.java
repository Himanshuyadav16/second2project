package com.lise.modals.todos;

import lombok.*;

@Getter
@Setter
@ToString
public class ToDoResponse {
    int id;
    int user_id;
    String title;
    String due_on;
    String status;
}
