package com.lise.modals.todos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ToDoPostBody {
    public int user_id;
    public String title;
    public String due_on;
    public String status;
}
