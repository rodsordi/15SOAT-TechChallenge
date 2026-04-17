package br.com.fiap.mecanica.domain.entity.factory;

import br.com.fiap.mecanica.domain.entity.Employee;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class EmployeeFactory {

    private final Employee.EmployeeBuilder<?, ?> builder;

    public static EmployeeFactory createEmployee() {
        return new EmployeeFactory(Employee.builder());
    }

    public Employee.EmployeeBuilder<?, ?> withAllFields() {
        return builder;
    }
}