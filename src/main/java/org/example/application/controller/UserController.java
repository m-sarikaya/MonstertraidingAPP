package org.example.application.controller;

import org.example.application.entity.UserEntity;
import org.example.application.services.UserService;
import org.example.server.http.Method;
import org.example.server.http.Request;
import org.example.server.http.Response;
import org.example.server.http.Status;

import java.util.List;

public class UserController extends Controller {
    private final UserService userService = new UserService();

    @Override
    public Response handle(Request request) {

        if (request.getMethod().equals(Method.POST)) {
            return create(request);
        }
        if (request.getMethod().equals(Method.GET)) {
            return readAll();
        }

        return json(Status.METHOD_NOT_ALLOWED, "HTTP-Method not supported");

    }

    private Response create(Request request) {
        UserEntity user = fromBody(request.getBody(), UserEntity.class);
        user = userService.create(user);
        return json(Status.CREATED, user);
    }

    private Response readAll() {
        List<UserEntity> users = userService.getAll();
        return json(Status.OK, users);
    }
}