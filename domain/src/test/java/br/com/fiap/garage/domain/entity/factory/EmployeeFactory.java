package br.com.fiap.garage.domain.entity.factory;

import br.com.fiap.garage.domain.entity.Employee;
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