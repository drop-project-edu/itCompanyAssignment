package org.dropproject.samples.itcompanyassignment;

public class Main {

    public static void main(String[] args) {

    }
    
    public static Company myCompany() {

        String userId = System.getProperty("dropProject.currentUserId");
        int numStudent= Integer.parseInt(userId.substring(1));

        return new Company(numStudent + "");

    }

}
