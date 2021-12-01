package com.exist.core.data.dto;

import lombok.Data;

@Data
public class RoleDTO {

    private long roleId;
    private String roleType;
    private boolean isActive;

}
