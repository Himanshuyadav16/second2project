package com.lise.modals;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class ResponseUserGet {
    int id;
    String name;
    String email;
    String gender;
    String status;
}
