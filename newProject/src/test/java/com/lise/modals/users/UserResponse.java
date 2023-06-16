package com.lise.modals.users;
import lombok.*;

@Getter
@Setter
@ToString
public class UserResponse {
    int id;
    String name;
    String email;
    String gender;
    String status;
}
