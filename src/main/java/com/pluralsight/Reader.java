package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public static void main(String[] args) throws InterruptedException {

          try {
                    BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/employees.csv"));
                    reader.readLine();
                    String line;

                    while ((line = reader.readLine()) != null) {

                        String[] tokens = line.split("\\|");

                        int id = Integer.parseInt(tokens[0]);
                        String name = tokens[1];
                        double hours = Double.parseDouble(tokens[2]);
                        double rate = Double.parseDouble(tokens[3]);

                        Employee emp = new Employee(id, name, hours, rate);

                        System.out.printf("%d | %-20s | $%.2f%n",
                                emp.getEmployeeId(),
                                emp.getName(),
                                emp.getGrossPay());
                    }


                    reader.close();

                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }
            }
        }



