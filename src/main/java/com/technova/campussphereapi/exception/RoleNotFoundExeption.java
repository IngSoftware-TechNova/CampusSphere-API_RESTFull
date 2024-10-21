package com.technova.campussphereapi.exception;

public class RoleNotFoundExeption extends RuntimeException{
    public RoleNotFoundExeption() {
        super("Role not found for the user");
    }

    public RoleNotFoundExeption(String message) {
        super(message);
    }
}
