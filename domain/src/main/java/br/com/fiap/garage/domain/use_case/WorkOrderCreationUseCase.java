package br.com.fiap.garage.domain.use_case;

import br.com.fiap.commons.exception.ResourceNotFoundException;
import br.com.fiap.garage.domain.entity.*;
import br.com.fiap.garage.domain.publisher.NotifyCustomerForApprovalPublisher;
import br.com.fiap.garage.domain.repository.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class WorkOrderCreationUseCase {

    private final EmployeeRepository employeeRepository;

    private final VehicleRepository vehicleRepository;

    private final ServiceRepository serviceRepository;

    private final WorkOrderRepository workOrderRepository;

    private final NotifyCustomerForApprovalPublisher notifyCustomerForApprovalPublisher;

    public WorkOrder create(WorkOrder workOrder, Set<UUID> servicesIds) {
        var foundEmployee = findEmployee(workOrder);
        var foundVehicle = findVehicle(workOrder);
        var foundServices = findServices(servicesIds);
        workOrder.updateReferences(foundVehicle, foundEmployee, foundServices);

        workOrder.calculateTotalAmount();

        var createdWorkOrder = workOrderRepository.save(workOrder);

        notifyCustomerForApprovalPublisher.notify(createdWorkOrder);

        return createdWorkOrder;
    }

    private Employee findEmployee(WorkOrder workOrder) {
        var employeeId = Optional.of(workOrder.getEmployee())
                .map(User::getId)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class, "id", null));

        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class, "id", employeeId));
    }

    private Vehicle findVehicle(WorkOrder workOrder) {
        var vehicleId = Optional.of(workOrder.getVehicle())
                .map(Vehicle::getId)
                .orElseThrow(() -> new ResourceNotFoundException(Vehicle.class, "id", null));

        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException(Vehicle.class, "id", vehicleId));
    }

    private Set<Service> findServices(Set<UUID> servicesIds) {
        return servicesIds.stream()
                .map(serviceId -> serviceRepository.findById(serviceId)
                        .orElseThrow(() -> new ResourceNotFoundException(Service.class, "id", serviceId)))
                .collect(Collectors.toSet());
    }
}
