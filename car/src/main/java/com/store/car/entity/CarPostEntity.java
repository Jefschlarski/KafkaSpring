package com.store.car.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //define que essa classe vai se relacionar com o banco de dados
@Table(name = "car_post") //caso não exista a tabela car_post sera criada
@Data //cria os metodos getter y setter para cada atributo da classe
@NoArgsConstructor
public class CarPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //atributo do persistence que define que o id vai se autoincrementar
    private long id;

    @Column(name= "car_model")
    private String model;

    @Column(name= "car_brand")
    private String brand;

    @Column(name= "car_price")
    private Double price;

    @Column(name= "car_description")
    private String description;

    @Column(name= "car_engine")
    private String engineVersion;

    @Column(name= "car_city")
    private String city;

    @Column(name= "car_created")
    private String createdDate;

    @Column(name= "car_post_contact")
    private String contact;

    @ManyToOne
    @JoinColumn(name = "owner_post_id", nullable = false, referencedColumnName = "id")
    private OwnerPostEntity ownerPost;
}
