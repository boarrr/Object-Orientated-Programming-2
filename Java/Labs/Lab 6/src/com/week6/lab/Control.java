/*
 * Control: The main control class of the program
 * Author: Ryan Pitman
 * Date: 13-03-2024
 */

package com.week6.lab;

public class Control {
   public static void main(String[] args) {
        // Create sample job objects
        // Job job1 = new Job(30000, "Software Developer", 1);
        // Job job2 = new Job(40000, "Software Tester", 2);

        // Print out the job objects
        // System.out.println(job1);
        // System.out.println(job2);

        // Create a job object with an invalid role
        //Job job3 = new Job(50000, "Invalid Role");

        // Create a job object with a valid role
        Job job4 = new Job(60000, "Nurse");

         // Print out the job object
         //System.out.println(job3);       
         System.out.println(job4); 
   } 
}
