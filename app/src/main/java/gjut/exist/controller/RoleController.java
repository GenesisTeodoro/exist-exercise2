package gjut.exist.controller;

import gjut.exist.repository.RoleRepository;

public class RoleController {

    private final RoleRepository repository;

    RoleController(RoleRepository repository){
        this.repository = repository;
    }


}
