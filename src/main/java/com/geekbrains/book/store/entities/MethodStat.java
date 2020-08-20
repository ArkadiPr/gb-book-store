package com.geekbrains.book.store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "methodstat")
@Data
@NoArgsConstructor
public class MethodStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "methodname")
    private String methodName;

    @Column(name = "callamount")
    private Long callAmount;

    public MethodStat(String methodName) {
        this.methodName = methodName;
        this.callAmount = 0L;
    }

    public void incrementCallAmount() {
        callAmount += 1;
    }
}
