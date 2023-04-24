package org.dropproject.samples.itcompanyassignment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Company {

    private String name;

    private ArrayList<Employee> employees;

    public Company(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public Employee getEmployeeHighestHourlyRate() {

        ArrayList<Employee> aux = this.employees.stream()
                .filter(employee -> employee.hasHourlyRate())
                .collect(Collectors.toCollection(ArrayList::new));

        aux.sort(new Comparator<Employee>() {

            @Override
            public int compare(Employee o1, Employee o2) {

                Integer valueO1 = ((ITConsultant) o1).getValue();
                Integer valueO2 = ((ITConsultant) o2).getValue();

                if (valueO1.compareTo(valueO2) == 0) {
                    return o1.getName().compareTo(o2.getName());
                }

                return valueO1.compareTo(valueO2) * -1;

            }

        });

        return aux.get(0);

    }

    @Override
    public String toString() {

        if (employees.isEmpty()) {
            return "The company " + name + " does not have employees.";
        }


        StringBuilder result = new StringBuilder();

        result.append("The company " + name + " has " + employees.size() + " employees:\n");


        for (Employee employee : employees) {
            result.append(employee.toString()).append("\n");
        }


        String aux = result.toString();

        return aux.substring(0, aux.length() - 1);

    }

}
