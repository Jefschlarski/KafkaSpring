package com.store.car.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // define que essa classe vai se relacionar com o banco de dados
@Table(name = "owner_post") // caso não exista a tabela car_post sera criada
@Data // cria os metodos getter y setter para cada atributo da classe
@NoArgsConstructor
public class OwnerPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // atributo do persistence que define que o id vai se
                                                    // autoincrementar
    private long id;

    private String name;

    @Column(name = "owner_type")
    private String type;

    @Column(name = "owner_contact")
    private String contactNumber;
}
