package dev.zoro.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Role {
    private UUID id;
    private String role;
}
