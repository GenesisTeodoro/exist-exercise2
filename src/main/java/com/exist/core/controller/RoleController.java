package com.exist.core.controller;

import com.exist.core.repository.RoleRepository;

public class RoleController {

    private final RoleRepository repository;

    RoleController(RoleRepository repository){
        this.repository = repository;
    }


}
