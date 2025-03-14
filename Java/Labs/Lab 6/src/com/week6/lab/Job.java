/*
 * Job: A class to store information about a job
 * Author: Ryan Pitman
 * Date: 13-03-2024
 */

package com.week6.lab;

public class Job {
    private double salary;
    private String role;
    private int jobID;

    // Static counter for job IDs starting at 100.
    private static int jobCounter = 100;

    // Constructor: sets salary and validates role; jobID is auto-assigned.
    public Job(double salary, String role) {
        // Validate salary.
        if (salary <= 0 || salary >= 100000) {
            System.out.println("Invalid salary. Salary must be > 0 and < 100000. Setting salary to 0.");
            this.salary = 0;
        } else {
            this.salary = salary;
        }
        // Validate and set role (this will also assign a jobID).
        setRole(role);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0 || salary >= 100000) {
            System.out.println("Invalid salary. Salary must be > 0 and < 100000.");
        } else {
            this.salary = salary;
        }
    }

    public String getRole() {
        return role;
    }

    // Validates that the role exists in roles.txt using FileProcessor.
    public void setRole(String role) {
        FileProcessor fp = new FileProcessor("roles.txt");
        String[] validRoles = fp.readFile();

        boolean isValid = false;

        // Check if the role is in the list of valid roles.
        for (String validRole : validRoles) {
            if (validRole.equals(role)) {
                isValid = true;
                break;
            }
        }

        // If the role is valid, set it. Otherwise, set it to "Invalid".
        if (isValid) {
            this.role = role;

            // Assign jobID using static counter and then increment the counter.
            this.jobID = jobCounter;
            jobCounter += 100;
        } else {
            System.out.println("Invalid role provided: " + role + ". Role must be one of the values in roles.txt.");
            this.role = "Invalid";
        }
    }

    public int getJobID() {
        return jobID;
    }

    // The toString() method formats the Job attributes into a string.
    // It also writes the Job ID into jobs.txt.
    public String toString() {
        String jobInfo = "Job ID: " + jobID + ", Salary: " + salary + ", Role: " + role;
        
        // Write the jobID to jobs.txt using FileProcessor.
        if (jobID == 0) {
            System.out.println("Job ID not set. Job ID must be set before writing to file.");
            return jobInfo;
        }
        FileProcessor fp = new FileProcessor("jobs.txt");
        fp.writeLine("Job ID: " + jobID);
        
        return jobInfo;
    }
}
