package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class AppUser{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String role;
    private boolean isEnabled;
    private Instant createdAt;
    private Instant updatedAt;
    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private Set<ShoppingCart> carts;

}
