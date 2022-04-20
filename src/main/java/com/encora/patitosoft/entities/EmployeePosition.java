package com.encora.patitosoft.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee_position")
public class EmployeePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_position_id")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "salary", nullable = false)
    private Double salary;

    public EmployeePosition(Employee employee, Position position, Double salary) {
        this.employee = employee;
        this.position = position;
        this.date = LocalDateTime.now();
        this.salary = salary;
    }

}
