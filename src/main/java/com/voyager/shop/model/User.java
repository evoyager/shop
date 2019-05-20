package com.voyager.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "login")
    @Length(min = 3, message = "*Your login must have at least 3 characters")
    @Pattern(regexp = "\\w+", message = "*Special characters not allowed in login")
    @NotEmpty(message = "*Please provide login")
    private String login;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "name")
    @Length(min = 5, message = "*Your name must have at least 5 characters")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    @Length(min = 5, message = "*Your last name must have at least 5 characters")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "middle_name")
    @Length(min = 5, message = "*Your middle name must have at least 5 characters")
    @NotEmpty(message = "*Please provide your middle name")
    private String middleName;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
