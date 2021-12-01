package com.exist.core.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Contact")
public class Contact {

    @Id
    @Column(name="contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "contact_type")
    private String contactType;
    @Column(name = "contact_info")
    private String contactInfo;
    @Column(name = "contact_order")
    private int contactOrder;
    @Column(name = "person_id")
    private long personId;

    public Contact(String contactType,
                   String contactInfo,
                   int contactOrder){
        this.contactType = contactType;
        this.contactInfo = contactInfo;
        this.contactOrder = contactOrder;

    }

}
