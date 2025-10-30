package org.lorem.profilesservice.domain.model.commands;

// Comando minimal para crear empleado: solo los datos básicos necesarios
public record EmployeeCreateCommand(String documentNumber, String firstName, String lastName, String phone) {}
