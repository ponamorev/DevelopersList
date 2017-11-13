package com.andersen.developersList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class DeveloperDAOTest {
    public static void main(String[] args) {
        String menu = "\nChoose the action with the file:\n\t1. Add the developer.\n\t" +
                "2. Remove the developer.\n\t3. Get the developer by the ID.\n\t" +
                "4. Update information about the developer.\n\t5. List developers.\n\t" +
                "6. Exit from the app.";
        boolean temp = true;
        File file = new File(DeveloperDAO.fileName);
        try {
            if (!file.createNewFile());
        } catch (IOException e) {
            System.nanoTime();
        }
        DeveloperDAO dev = new DeveloperDAO();
        Scanner reader = new Scanner(System.in);

        System.out.println("Hello! This app works with file which contains the list of developers.");

        while (temp) {

            System.out.println(menu);
            System.out.print("Number: ");
            int number = reader.nextInt();
            switch (number) {
                case 1:
                    dev.addDeveloper();
                    break;
                case 2:
                    dev.removeDeveloper();
                    break;
                case 3:
                    System.out.print("\nEnter the ID of the developer: ");
                    long ID = reader.nextLong();
                    if (dev.getByID(ID) != null)
                        System.out.println(dev.getByID(ID).toString());
                    break;
                case 4:
                    dev.updateDeveloper();
                    break;
                case 5:
                    dev.printListDevelopers(dev.getAll());
                    break;
                case 6:
                    temp = false;
                    break;
                default:
                    System.out.println("\nTry enter. Choose number from the menu.");
                    break;
            }
        }
    }
}
