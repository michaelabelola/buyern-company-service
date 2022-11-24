package com.buyern.entityservice.models;

import com.buyern.entityservice.enums.BusinessState;
import com.buyern.entityservice.enums.BusinessType;
import com.buyern.entityservice.models.location.Location;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uid;
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private BusinessState state = BusinessState.INACTIVE;
    private BusinessType type;
    @Column(columnDefinition = "LONGTEXT")
    private String shortAbout;
    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private BusinessDetail detail;
    private String logo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Business business = (Business) o;
        return id != null && Objects.equals(id, business.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
