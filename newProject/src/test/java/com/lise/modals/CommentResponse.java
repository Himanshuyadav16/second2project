package com.lise.modals;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CommentResponse {
    int id;
    int post_id;
    String name;
    String email;
    String body;
}
