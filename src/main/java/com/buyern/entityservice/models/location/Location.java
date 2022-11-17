package com.buyern.entityservice.models.location;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "locations")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String tag;
    private String address;
    @ManyToOne(cascade = CascadeType.DETACH)
    private City city;
    @ManyToOne(cascade = CascadeType.DETACH)
    private State state;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Country country;
    private Long zipcode;
    private Double latitude;
    private Double longitude;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Location location = (Location) o;
        return id != null && Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
