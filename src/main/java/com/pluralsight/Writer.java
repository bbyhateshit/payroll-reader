package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Writer {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the employee file to process: ");
        String inputFile = scanner.nextLine();

        System.out.print("Enter the name of the payroll file to create: ");
        String outputFile = scanner.nextLine();

        boolean isJson = outputFile.toLowerCase().endsWith(".json");

        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/employees.csv"))) {

            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");

                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hours = Double.parseDouble(tokens[2]);
                double rate = Double.parseDouble(tokens[3]);

                employees.add(new Employee(id, name, hours, rate));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }


        try (PrintWriter writer = new PrintWriter(outputFile)) {

            if (!isJson) {
                writer.println("id|name|gross pay");

                for (Employee emp : employees) {
                    writer.printf("%d|%s|%.2f%n",
                            emp.getEmployeeId(),
                            emp.getName(),
                            emp.getGrossPay());
                }

            } else {
                writer.println("[");
                for (int i = 0; i < employees.size(); i++) {
                    Employee emp = employees.get(i);

                    writer.printf(
                            "  { \"id\": %d, \"name\": \"%s\", \"grossPay\": %.2f }",
                            emp.getEmployeeId(),
                            emp.getName(),
                            emp.getGrossPay()
                    );

                    if (i < employees.size() - 1) writer.println(",");
                }
                writer.println("\n]");
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }


    }
}
