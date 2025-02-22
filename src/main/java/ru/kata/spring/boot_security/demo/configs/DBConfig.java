package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DBConfig {
    private final RoleService roleService;
    private final UserService userService;

    DBConfig (RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void createData() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.save(roleAdmin);
        Role roleUser = new Role("ROLE_USER");
        roleService.save(roleUser);
        Set<Role> roles = new HashSet<>();
        roles.add(roleAdmin);
        User userAdmin = new User("admin", "Igor", 35,
                "Igor@gmail.com",
                "100",
                roles);
        userService.saveNewUser(userAdmin);
        roles.clear();
        roles.add(roleUser);
        User userUser = new User("user", "Ivan", 34,
                "Ivan@gmail.com",
                "100",
                roles);
        userService.saveNewUser(userUser);
    }
}