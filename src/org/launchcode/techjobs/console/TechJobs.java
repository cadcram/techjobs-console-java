package org.launchcode.techjobs.console;

import java.util.*;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();


            //    try {
                    if (searchField.equals("all")) {
                        printJobs(JobData.findByValue(searchTerm));
                    } else {
                        printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                    }
               // }
//                catch (NullPointerException ex) {
//                    System.out.println(ex.getMessage());
//                }
                // Catches the exception
                // The error message in the thrown in the JobData class under the findByColumnAndValue method

            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        Integer counter = 0;

        System.out.println("Debugging here " +someJobs);

        // There is an ArrayList of HashMaps so I will iterate through them
        for (int a = 0; a < someJobs.size(); a++) {
            // Declaring a new HashMap by getting the index from the ArrayList
            HashMap<String, String> jobOpening = (HashMap<String, String>) someJobs.get(a);
            // Creating a set of key values from the jobOpening HashMap
            Set<String> key = jobOpening.keySet();
            // Not exactly sure what the iterator does but I think it orders it so that the
            // keys can be iterated through
            Iterator it = key.iterator();



            System.out.println("*****\n");
            while (it.hasNext()) {
                String jobKey = (String)it.next();
                String jobValue = (String) jobOpening.get(jobKey);

                System.out.println(jobKey + ": " + jobValue);

                //it.remove(); //avoids a ConcurrentModificationException
            }
            System.out.println("\n*****");
            counter++;
        }
        System.out.println(counter);
    }
}
