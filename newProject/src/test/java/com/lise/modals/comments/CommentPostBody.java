package com.lise.modals.comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPostBody {
    public int post_id;
    public String name;
    public String email;
    public String body;
}
