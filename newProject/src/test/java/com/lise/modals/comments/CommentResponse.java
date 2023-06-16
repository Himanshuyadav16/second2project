package com.lise.modals.comments;

import lombok.*;

@Getter
@Setter
@ToString
public class CommentResponse {
    int id;
    int post_id;
    String name;
    String email;
    String body;
}
