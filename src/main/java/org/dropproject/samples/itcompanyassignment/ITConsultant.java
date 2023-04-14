package org.dropproject.samples.itcompanyassignment;

public class ITConsultant extends Employee {

    private int value; // hourly

    private String client;

    public ITConsultant(int id, String nome, int salario, int value, String client) {
        super(id, nome, salario);
        this.value = value;
        this.client = client;
    }

    public int getValue() {
        return value;
    }

    public String getClient() {
        return client;
    }

    @Override
    public boolean hasHourlyRate() {
        return true;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + salary + " (IT Consultant)\n" +
                client + " | " + value + " (hourly value)";
    }

}
