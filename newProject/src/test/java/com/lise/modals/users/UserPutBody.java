package com.lise.modals.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPutBody {
    public String name;
    public String email;
    public String gender;
    public String status;
}
