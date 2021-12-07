package com.exist.core.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    @Column(name = "role_type")
    private String roleType;
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    public Role(
                String roleType,
                boolean isActive
    ){
        this.roleType = roleType;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Role obj2 = (Role)obj;
        if(this.roleType.equals(obj2.getRoleType()))
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.roleType.hashCode();
    }
}
