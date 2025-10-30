package org.lorem.profilesservice.domain.model.valueobjects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ContactInfo {

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    public ContactInfo(String email, String telefono) {
        this.email = email;
        this.telefono = telefono;
    }
}