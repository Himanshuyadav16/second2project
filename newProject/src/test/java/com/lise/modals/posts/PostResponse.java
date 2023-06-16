package com.lise.modals.posts;

import lombok.*;

@Getter
@Setter
@ToString
public class PostResponse {
    int id;
    int user_id;
    String title;
    String body;
}
