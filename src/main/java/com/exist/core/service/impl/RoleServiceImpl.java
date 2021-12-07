package com.exist.core.service.impl;

import com.exist.core.data.dto.PersonDTO;
import com.exist.core.data.dto.RoleDTO;
import com.exist.core.data.entity.Role;
import com.exist.core.data.exception.RoleNotFoundException;
import com.exist.core.repository.RoleRepository;
import com.exist.core.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoleRepository repository;


    @Override
    public List<RoleDTO> listRoles() {
        List<Role> roleList = repository.findAll();

        return roleList.stream()
                .map(role -> mapper.map(role, RoleDTO.class))
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

        return mapper.map(roleResponse, RoleDTO.class);
    }

    @Override
    public RoleDTO createRole(RoleDTO roleRequest) {

        Role role = mapper.map(roleRequest, Role.class);

        repository.save(new Role(
                role.getRoleType(),
                role.isActive()
        ));

        RoleDTO roleResponse = mapper.map(role, RoleDTO.class);
        return roleResponse;
    }

    @Override
    public RoleDTO updateRole(long roleId, RoleDTO roleDto) {
        RoleDTO roleResponse;

        Role role = mapper.map(roleDto, Role.class);

        Optional<Role> roleData = repository.findById(roleId);

        if(roleData.isPresent()){
            Role _role = roleData.get();
            _role.setRoleType(role.getRoleType());
            _role.setActive(role.isActive());

            repository.save(_role);
            roleResponse = mapper.map(_role, RoleDTO.class);
        }else{
            throw new RoleNotFoundException(roleId);
        }

        return roleResponse;
    }

    @Override
    public void deleteRole(long roleId) {
        Role role = repository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));

        repository.deleteById(roleId);
    }
}
