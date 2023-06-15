package com.lise.modals;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    int id;
    int user_id;
    String title;
    String body;
}
