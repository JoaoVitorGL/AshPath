package org.featherlessbipeds.ashpath.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_admin")
@DiscriminatorValue("ADMIN")
//@PrimaryKeyJoinColumn(name = "admin_id", referencedColumnName = "user_id") // doesnt work
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id") // works
public class Admin extends User
{
    @Enumerated(EnumType.STRING)
    @Column(name = "admin_role", nullable = false)
    private AdminRole role;

    public AdminRole getRole() {
        return role;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }
}
