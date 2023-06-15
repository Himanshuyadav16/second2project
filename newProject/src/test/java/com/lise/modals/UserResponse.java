package com.lise.modals;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    int id;
    String name;
    String email;
    String gender;
    String status;
}
