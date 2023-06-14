package com.lise.modals;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserGetResponse {
    int id;
    String name;
    String email;
    String gender;
    String status;
}
