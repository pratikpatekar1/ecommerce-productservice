package dev.zoro.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserDto {
    private String email;
    private Set<Role> roles;
}
