package org.dropproject.samples.itcompanyassignment;

public abstract class Employee {

    protected int id;
    protected String name;
    protected int salary; // monthly

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public abstract boolean hasHourlyRate();

}
