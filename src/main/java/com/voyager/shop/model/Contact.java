package com.voyager.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private int id;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide contact name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide contact last name")
    private String lastName;

    @Column(name = "middle_name")
    @NotEmpty(message = "*Please provide contact middle name")
    private String middleName;
}
