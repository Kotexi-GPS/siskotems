package org.lorem.profilesservice.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Location {

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}