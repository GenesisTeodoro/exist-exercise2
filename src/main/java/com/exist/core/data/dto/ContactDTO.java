package com.exist.core.data.dto;


import lombok.Data;

@Data
public class ContactDTO {

    private long id;
    private String contactType;
    private String contactInfo;
    private int contactOrder;

}
