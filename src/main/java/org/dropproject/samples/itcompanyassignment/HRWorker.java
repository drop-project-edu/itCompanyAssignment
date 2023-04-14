package org.dropproject.samples.itcompanyassignment;

public class HRWorker extends Employee {

    public HRWorker(int id, String name, int salary) {
        super(id, name, salary);
    }

    @Override
    public boolean hasHourlyRate() {
        return false;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + salary + " (HR)";
    }

}
