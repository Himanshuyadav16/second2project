package com.lise.modals.posts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPutBody {
    public int user_id;
    public String title;
    public String body;
}