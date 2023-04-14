package org.dropproject.samples.itcompanyassignment;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// in Drop Project, all test classes must begin with "Test"
@TestMethodOrder(MethodOrderer.MethodName.class)
@Timeout(1)
public class TestProject {

    @Test
    public void test_01_constructor_getters_Company() {

        Company company = new Company("Tech Innovation Company");

        assertEquals("Tech Innovation Company", company.getName(), "fn getName is not returning the expected value.");

        assertNotNull(company.getEmployees(), "fn getEmployees must return an initialized data structure.");
        assertEquals(0, company.getEmployees().size(), "A fn getEmployees must return a data structure with 0 elements");
    }

    @Test
    public void test_02_attributes_Company() {

        Class<Company> clazz = Company.class;
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            if (!field.getType().equals(List.class) && !field.getType().equals(ArrayList.class)) {
                continue;
            }

            Type generic = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

            if (generic.equals(HRWorker.class)
                    || generic.equals(ITConsultant.class)) {
                fail("The Company class must not include attributes of class HRWorker or ITConsultant");
            }
        }
    }

    @Test
    public void test_03_instance_Employee() {

        Class<Employee> clazz = Employee.class;
        assertTrue(Modifier.isAbstract(clazz.getModifiers()), "It must not be possible to instantiate an object of class Employee.");
    }

    @Test
    public void test_04_constructor_getters_HRWorker() {

        HRWorker employee = new HRWorker(1, "Ana Bela", 1200);

        assertEquals(1, employee.getId(), "fn getid didn't return the expected value.");
        assertEquals("Ana Bela", employee.getName(), "fn getName didn't return the expected value.");
        assertEquals(1200, employee.getSalary(), "fn getSalary didn't return the expected value.");

    }

    @Test
    public void test_05_constructor_getters_ITConsultant() {

        ITConsultant ITConsultant = new ITConsultant(1, "João Mário", 1500, 22, "Nova Base");

        assertEquals(1, ITConsultant.getId(), "fn getid didn't return the expected value.");
        assertEquals("João Mário", ITConsultant.getName(), "fn getName didn't return the expected value.");
        assertEquals(1500, ITConsultant.getSalary(), "fn getSalary didn't return the expected value.");
        assertEquals(22, ITConsultant.getValue(), "fn getValue didn't return the expected value.");
        assertEquals("Nova Base", ITConsultant.getClient(), "fn getClient didn't return the expected value.");

    }

    @Test
    public void test_06_addEmployee() {

        Company company = new Company("Tech Innovation Company");

        HRWorker rh = new HRWorker(1, "Ana Bela", 1200);
        ITConsultant ITConsultant = new ITConsultant(2, "João Mário", 1500, 22, "Nova Base");


        company.addEmployee(rh);
        assertEquals(1, company.getEmployees().size(), "fn addEmployee didn't update the employees data structure as expected.");

        company.addEmployee(ITConsultant);
        assertEquals(2, company.getEmployees().size(), "fn addEmployee didn't update the employees data structure as expected.");

    }

    @Test
    public void test_07_getEmployeeWithHighestHourlyRate() {

        Company company = new Company("Tech Innovation Company");

        HRWorker rh = new HRWorker(1, "Ana Bela", 1200);

        ITConsultant ITConsultant1 = new ITConsultant(2, "João Mário", 1500, 22, "Nova Base");
        ITConsultant ITConsultant2 = new ITConsultant(3, "Pedro Carapinha", 1810, 18, "Accenture");


        company.addEmployee(rh);

        company.addEmployee(ITConsultant1);
        company.addEmployee(ITConsultant2);


        Employee employee = company.getEmployeeWithHighestHourlyRate();

        assertEquals(2, employee.getId(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (id) " +
                "with the highest hourly rate as expected.");
        assertEquals("João Mário", employee.getName(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (name) " +
                "with the highest hourly rate as expected.");
        assertEquals(1500, employee.getSalary(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (salary) " +
                "with the highest hourly rate as expected.");
        assertEquals(22, ((ITConsultant) employee).getValue(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (value) " +
                "with the highest hourly rate as expected.");
        assertEquals("Nova Base", ((ITConsultant) employee).getClient(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (client) " +
                "with the highest hourly rate as expected.");

    }

    @Test
    public void test_08_getEmployeeWithHighestHourlyRate_SameValue() {

        Company company = new Company("Tech Innovation Company");

        HRWorker rh = new HRWorker(1, "Ana Bela", 1200);

        ITConsultant ITConsultant1 = new ITConsultant(2, "João Mário", 1500, 22, "Nova Base");
        ITConsultant ITConsultant2 = new ITConsultant(3, "Ana Falcão", 1670, 22, "Opensoft");
        ITConsultant ITConsultant3 = new ITConsultant(4, "Pedro Carapinha", 1810, 18,
                "Accenture");


        company.addEmployee(rh);

        company.addEmployee(ITConsultant1);
        company.addEmployee(ITConsultant2);
        company.addEmployee(ITConsultant3);


        Employee employee = company.getEmployeeWithHighestHourlyRate();

        assertEquals(3, employee.getId(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (id) " +
                        "with the highest hourly rate as expected.");
        assertEquals("Ana Falcão", employee.getName(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (name) " +
                "with the highest hourly rate as expected.");
        assertEquals(1670, employee.getSalary(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (salary) " +
                "with the highest hourly rate as expected.");
        assertEquals(22, ((ITConsultant) employee).getValue(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (value) " +
                "with the highest hourly rate as expected.");
        assertEquals("Opensoft", ((ITConsultant) employee).getClient(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (client) " +
                "with the highest hourly rate as expected.");


        ITConsultant ITConsultant4 = new ITConsultant(5, "João Pedro Simões", 2810, 120, "Accenture");

        company.addEmployee(ITConsultant4);

        employee = company.getEmployeeWithHighestHourlyRate();

        assertEquals(5, employee.getId(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (id) " +
                "with the highest hourly rate as expected.");
        assertEquals("João Pedro Simões", employee.getName(), "fn getEmployeeWithHighestHourlyRate didn't return the employee (name) " +
                        "with the highest hourly rate as expected.");
    }

    @Test
    public void test_09_HRWorker_toString() {

        HRWorker rh = new HRWorker(1, "Maria Vieira", 1200);
        String result = rh.toString();
        assertEquals("1 | Maria Vieira | 1200 (HR)", result, "fn toString didn't return the expected value");


        rh = new HRWorker(2, "Pablito", 1275);
        result = rh.toString();
        assertEquals("2 | Pablito | 1275 (HR)", result, "fn toString didn't return the expected value");
    }

    @Test
    public void test_10_ITConsultant_toString() {

        ITConsultant ITConsultant = new ITConsultant(1, "Américo Batista", 1500, 40, "TAP");

        String result = ITConsultant.toString();

        assertEquals("1 | Américo Batista | 1500 (IT Consultant)\n" +
                        "TAP | 40 (hourly value)",
                result, "fn toString didn't return the expected value");


        ITConsultant = new ITConsultant(2, "Fernando Batista", 1600,
                38, "CP");

        result = ITConsultant.toString();

        assertEquals("2 | Fernando Batista | 1600 (IT Consultant)\n" +
                        "CP | 38 (hourly value)",
                result, "fn toString didn't return the expected value");

    }

    @Test
    public void test_11_Company_withoutEmployees_toString() {

        Company company = new Company("Smart Consulting");
        assertEquals("The company Smart Consulting does not have employees.", company.toString(), "fn toString didn't return the expected value");
    }

    @Test
    public void test_12_Company_toString() {

        Company company = new Company("Clever Smart Reloaded Consulting");

        HRWorker rh = new HRWorker(1, "Ana Bela", 1200);

        company.addEmployee(rh);

        assertEquals("The company Clever Smart Reloaded Consulting has 1 employees:\n" +
                        "1 | Ana Bela | 1200 (HR)",
                company.toString(), "fn toString didn't return the expected value");


        ITConsultant ITConsultant1 = new ITConsultant(2, "João Mário", 1500, 22, "Nova Base");
        ITConsultant ITConsultant2 = new ITConsultant(3, "Ana Falcão", 1670, 24, "Opensoft");

        company.addEmployee(ITConsultant1);
        company.addEmployee(ITConsultant2);

        assertEquals("The company Clever Smart Reloaded Consulting has 3 employees:\n" +
                        "1 | Ana Bela | 1200 (HR)\n" +
                        "2 | João Mário | 1500 (IT Consultant)\n" +
                        "Nova Base | 22 (hourly value)\n" +
                        "3 | Ana Falcão | 1670 (IT Consultant)\n" +
                        "Opensoft | 24 (hourly value)",
                company.toString(),
                "fn toString didn't return the expected value");


        ITConsultant ITConsultant3 = new ITConsultant(4, "Filipa Martins", 1100, 18,
                "TVI");

        company.addEmployee(ITConsultant3);

        assertEquals("The company Clever Smart Reloaded Consulting has 4 employees:\n" +
                        "1 | Ana Bela | 1200 (HR)\n" +
                        "2 | João Mário | 1500 (IT Consultant)\n" +
                        "Nova Base | 22 (hourly value)\n" +
                        "3 | Ana Falcão | 1670 (IT Consultant)\n" +
                        "Opensoft | 24 (hourly value)\n" +
                        "4 | Filipa Martins | 1100 (IT Consultant)\n" +
                        "TVI | 18 (hourly value)",
                company.toString(),
                "fn toString didn't return the expected value");

    }

    @Test
    public void test_13_euEmpresa() {

        Company company = Main.myCompany();

        String id = System.getProperty("dropProject.currentUserId");
        String parsedId = id.substring(id.length() - 4);


        assertEquals(parsedId, company.getName());

    }

}
