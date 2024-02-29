package com.example.e_m_test.api.domain.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Entity(name = "phone")
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
}
