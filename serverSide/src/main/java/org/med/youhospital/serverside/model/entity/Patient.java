package org.med.youhospital.serverside.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class Patient extends Person {


    private LocalDate dateOfBirth;

    private String patientType;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Bill> bills;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Operation> operations;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Examination> examinations;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department department;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_PATIENT"));
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
