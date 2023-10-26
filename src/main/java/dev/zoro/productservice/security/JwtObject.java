package dev.zoro.productservice.security;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class JwtObject {
    private UUID userID;
    private String email;
    private List<Role> roles = new ArrayList<>();
    private String issuer;
    private Date issuedAt;
    private Date expiration;
}
