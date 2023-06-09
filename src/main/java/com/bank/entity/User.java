package com.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //mark java class as JPA entity
@Table(name="user") //specify a name to a table
public class User{

    @Column(name="user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    private List<Account> accounts;
    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false)
    private String email;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

}
