package com.bank.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="account")
public class Account{

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @ManyToOne
    private Long id;

//    @Column(name="user", nullable=false)
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

// Todo: make userName and account type as a composite primary key

    @Column(name="account_type")
    private String accountType;

    @OneToMany(mappedBy="toAccountID", cascade = CascadeType.ALL)
    private List<Transaction> transactions= new ArrayList<>();

    @Column(name="account_balance")
    private Long balance;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_accessed")
    private Long lastAccessed;

    @Version
    @Column(name="version")
    private Long version;

}
