package com.exist.core.service;

import com.exist.core.data.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> listRoles();
    RoleDTO getRoleById(long roleId);
    RoleDTO createRole(RoleDTO roleDto);
    RoleDTO updateRole(long roleId, RoleDTO roleDto);
    void deleteRole(long roleId);
}
