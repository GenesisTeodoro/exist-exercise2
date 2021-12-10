package com.exist.core.service.impl;

import com.exist.core.data.dto.RoleDTO;
import com.exist.core.data.entity.Role;
import com.exist.core.data.exception.RoleNotFoundException;
import com.exist.core.data.mapper.CoreMapper;
import com.exist.core.repository.RoleRepository;
import com.exist.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    public CoreMapper mapper;

    @Autowired
    private RoleRepository repository;


    @Override
    public List<RoleDTO> listRoles() {
        List<Role> roleList = repository.findAll();

        return roleList.stream()
                .map(role -> mapper.toDto(role))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(long roleId) {

        Optional<Role> roleData = repository.findById(roleId);
        Role roleResponse;

        if (roleData.isPresent()) {
            roleResponse = roleData.get();
        } else {
            throw new RoleNotFoundException(roleId);
        }

        return mapper.toDto(roleResponse);
    }

    @Override
    public RoleDTO createRole(RoleDTO roleRequest) {

        Role role = mapper.toEntity(roleRequest);

        repository.save(role);

        return mapper.toDto(role);
    }

    @Override
    public RoleDTO updateRole(long roleId, RoleDTO roleDto) {

        Role role = mapper.toEntity(roleDto);

        Role roleData = repository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));

        roleData.setRoleType(role.getRoleType());
        roleData.setActive(role.isActive());

        mapper.updateEntity(roleDto, roleData);

        repository.save(roleData);

        return mapper.toDto(roleData);
    }

    @Override
    public void deleteRole(long roleId) {
        Role role = repository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));

        repository.deleteById(roleId);
    }
}
