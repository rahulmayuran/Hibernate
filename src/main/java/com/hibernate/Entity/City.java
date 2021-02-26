package com.hibernate.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CITY")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String population;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name="addressLine1",column = @Column(name = "Flat_number")),
            @AttributeOverride(name="addressLine2",column = @Column(name = "Street_number"))
    })
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", Population='" + population + '\'' +
                ", address=" + address +
                '}';
    }
}
