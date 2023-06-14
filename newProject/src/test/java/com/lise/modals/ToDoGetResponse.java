package com.lise.modals;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ToDoGetResponse {
    int id;
    int user_id;
    String title;
    String due_on;
    String status;
}
