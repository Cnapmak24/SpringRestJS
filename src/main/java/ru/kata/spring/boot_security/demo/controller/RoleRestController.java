package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;

import java.util.List;


@RestController
public class RoleRestController {

    private final RoleServiceImpl roleService;

    public RoleRestController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping("api/admin/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(roleService.getListRoles());
    }

}
