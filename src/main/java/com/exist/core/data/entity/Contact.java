package com.exist.core.data.entity;

import javax.persistence.*;

@Entity
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

    public Contact(){}

    public Contact(long id, String contactType, String contactInfo){
        this.id = id;
        this.contactType = contactType;
        this.contactInfo = contactInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getContactOrder() {
        return contactOrder;
    }

    public void setContactOrder(int contactOrder) {
        this.contactOrder = contactOrder;
    }
}
