package com.exist.core.controller;

import com.exist.core.data.dto.RoleDTO;
import com.exist.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> listRoles(){
        return new ResponseEntity<>(service.listRoles(), OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") long roleId) {
        return new ResponseEntity<>(service.getRoleById(roleId), OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDto){
        return new ResponseEntity<>(service.createRole(roleDto), CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") long roleId, @RequestBody RoleDTO roleDto){
        return new ResponseEntity<>(service.updateRole(roleId, roleDto), OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") long roleId){
        service.deleteRole(roleId);
        return new ResponseEntity<>(OK);
    }


}
