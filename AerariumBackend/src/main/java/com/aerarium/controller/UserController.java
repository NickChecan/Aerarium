package com.aerarium.controller;

import com.aerarium.model.User;
import com.aerarium.projection.UserProjection;
import com.aerarium.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@BasePathAwareController
public class UserController {

    private final UserService userService;

    private ProjectionFactory projectionFactory;

    @Autowired
    public UserController(UserService userService, ProjectionFactory projectionFactory) {
        this.userService = userService;
        this.projectionFactory = projectionFactory;
    }

    @RequestMapping(value = "/users/{id}", produces = MediaTypes.HAL_JSON_VALUE,
            method = { RequestMethod.PUT, RequestMethod.PATCH } )
    public ResponseEntity<Resource<UserProjection>> update(@PathVariable("id") Long id, @RequestBody User user) {
        User existingUser = this.userService.update(id, user);
        UserProjection userProjection = this.projectionFactory
                .createProjection(UserProjection.class, existingUser);
        Resource<UserProjection> resource = new Resource<UserProjection>(userProjection);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

}
