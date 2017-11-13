package com.andersen.developersList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DeveloperDAO {

    static final String fileName = "Developers.txt";
    private long globalID;

    // Constructor
    // Initiate value of the globalID
    DeveloperDAO() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {

            int tempValue;
            String currentLine;
            String arrString[];
            if ((tempValue = reader.read()) == -1)
                globalID = 0;
            else {
                char ch = (char) tempValue;
                currentLine = Character.toString(ch);
                currentLine = currentLine.concat(reader.readLine());
                arrString = currentLine.split(" ");
                globalID = Math.round(Double.valueOf(arrString[0]));
                while (!Objects.equals(currentLine = reader.readLine(), null) &
                        !Objects.equals(currentLine, "")) {
                    arrString = currentLine.split(" ");
                    globalID = Math.round(Double.valueOf(arrString[0]));
                }
            }
        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        }
    }


    // Method for adding the developer in the file
    private void save(Developer developer) {

        try (FileWriter fileBuffered = new FileWriter(fileName, true)) {

            if (developer.getId() != globalID)
                globalID = developer.getId();
            String value = developer.toString();
            fileBuffered.write(value);

        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        }

        System.out.println("Success!");
    }

    // Method for creating an object of the class <Developer>
    // This object transmit to method save(Developer developer);
    void addDeveloper() {
        System.out.println("\nEnter the name of the developer:");

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("First name: ");
            String firstName = reader.readLine();
            System.out.print("Second name: ");
            String secondName = reader.readLine();

            Developer developer = new Developer(firstName, secondName, ++globalID);
            save(developer);

        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        }
    }


    // Next method realizes getting the developer by his ID
    Developer getByID(long ID) {
        Developer developer = null;
        String currentLine, arrString[];
        boolean found = false;
        int tempValue;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {

            // Check the file for emptiness
            if ((tempValue = reader.read()) == -1)
                System.out.println("\nThe file is empty. Please, add the developer " +
                        "before calling this method again.");
            else {
                char ch = (char) tempValue;
                currentLine = Character.toString(ch);
                currentLine = currentLine.concat(reader.readLine());
                arrString = currentLine.split(" ");
                if (Math.round(Double.valueOf(arrString[0])) == ID) {
                    developer = new Developer(arrString[1], arrString[2],
                            Math.round(Double.valueOf(arrString[0])));
                    found = true;
                }
                while (!Objects.equals(currentLine = reader.readLine(), null) &
                        !Objects.equals(currentLine, "")) {
                    arrString = currentLine.split(" ");
                    if (Math.round(Double.valueOf(arrString[0])) == ID) {
                        developer = new Developer(arrString[1], arrString[2],
                                Math.round(Double.valueOf(arrString[0])));
                        found = true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Name of this developer has wrong format. Please, write the name right.\n" +
                    "Write first and second names. If names consists of three and more words - you should" +
                    "write two names which you uses most of all.");

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("First name: ");
                String firstName = reader.readLine();
                System.out.print("Second name: ");
                String secondName = reader.readLine();

                update(new Developer(firstName, secondName, ID));

            } catch (IOException eio) {
                System.err.println("The exception of IO, the entry failed. Try again.");
            }

        }

        if (found)
            return developer;
        else {
            System.out.println("This ID doesn't apply to any of the developers.");
            return null;
        }
    }


    // Next method lets to get all of the developers
    List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        String currentLine, arrString[];
        int tempValue;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {

            // Check the file for emptiness
            if ((tempValue = reader.read()) == -1)
                System.out.println("\nThe file is empty. Please, add the developer " +
                        "before calling this method again.");
            else {
                char ch = (char) tempValue;
                currentLine = Character.toString(ch);
                currentLine = currentLine.concat(reader.readLine());
                arrString = currentLine.split(" ");
                try {
                    Developer firstDeveloper = new Developer(arrString[1], arrString[2],
                            Math.round(Double.valueOf(arrString[0])));
                    developerList.add(firstDeveloper);
                    while (!Objects.equals(currentLine = reader.readLine(), null) &
                            !Objects.equals(currentLine, "")) {
                        arrString = currentLine.split(" ");
                        Developer newDeveloper = new Developer(arrString[1], arrString[2],
                                Math.round(Double.valueOf(arrString[0])));
                        developerList.add(newDeveloper);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Name of this developer has wrong format. Please, write the name right.\n" +
                            "Write first and second names. If names consists of three and more words - you should" +
                            "write two names which you uses most of all.");

                    try {
                        System.out.print("First name: ");
                        String firstName = reader.readLine();
                        System.out.print("Second name: ");
                        String secondName = reader.readLine();

                        update(new Developer(firstName, secondName, Math.round(Double.valueOf(arrString[0]))));

                    } catch (IOException eio) {
                        System.err.println("The exception of IO, the entry failed. Try again.");
                    }

                }
            }
        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        }

        return developerList;
    }

    // Next method outputs the list of the developers on the screen
    void printListDevelopers(List<Developer> developers) {
        System.out.println();
        for (Developer dev : developers)
            System.out.println(dev.toString());
    }


    // Next method lets to remove the developer
    private void remove(Developer developer) {
        String currentLine, arrString[];
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {

            while (!Objects.equals(currentLine = reader.readLine(), null) &
                    !Objects.equals(currentLine, "")) {
                arrString = currentLine.split(" ");
                try {
                    if (Math.round(Double.valueOf(arrString[0])) != developer.getId() &
                            !Objects.equals(arrString[1], developer.getFirstName()) &
                            !Objects.equals(arrString[2], developer.getSecondName()))
                        builder.append(currentLine).append("\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Name of this developer has wrong format. Please, write the name right.\n" +
                            "Write first and second names. If names consists of three and more words - you should" +
                            "write two names which you uses most of all.");

                    try {
                        System.out.print("First name: ");
                        String firstName = reader.readLine();
                        System.out.print("Second name: ");
                        String secondName = reader.readLine();

                        update(new Developer(firstName, secondName, developer.getId()));

                    } catch (IOException eio) {
                        System.err.println("The exception of IO, the entry failed. Try again.");
                    }

                }
            }

        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        }

        try (FileWriter writer = new FileWriter(fileName, false)) {

            writer.write(builder.toString());

        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        }

        System.out.println("Success!");
    }

    // Next method lets to choose the developer for removing
    void removeDeveloper() {
        String currentLine, arrString[];
        int tempValue;
        System.out.println("\nEnter name of the developer for removing:");

        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(fileName))) {

            // Check the file for emptiness
            if ((tempValue = fileReader.read()) == -1)
                System.out.println("\nThe file is empty. Please, add the developer " +
                        "before calling this method again.");
            else {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("First name: ");
                String firstName = reader.readLine();
                System.out.print("Second name: ");
                String secondName = reader.readLine();

                char ch = (char) tempValue;
                currentLine = Character.toString(ch);
                currentLine = currentLine.concat(fileReader.readLine());
                try {

                    if (currentLine.contains(firstName + " " + secondName)) {
                        arrString = currentLine.split(" ");
                        Developer developer = new Developer(arrString[1], arrString[2],
                                Math.round(Double.valueOf(arrString[0])));
                        remove(developer);
                        return;
                    }

                    while (!Objects.equals(currentLine = fileReader.readLine(), null) &
                            !Objects.equals(currentLine, ""))
                        if (currentLine.contains(firstName + " " + secondName)) {
                            arrString = currentLine.split(" ");
                            Developer developer = new Developer(arrString[1], arrString[2],
                                    Math.round(Double.valueOf(arrString[0])));
                            remove(developer);
                            return;
                        }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Name of this developer has wrong format. Please, write the name right.\n" +
                            "Write first and second names. If names consists of three and more words - you should" +
                            "write two names which you uses most of all.");

                    try {
                        System.out.print("First name: ");
                        String firstNameToUp = reader.readLine();
                        System.out.print("Second name: ");
                        String secondNameToUp = reader.readLine();

                        arrString = currentLine.split(" ");
                        update(new Developer(firstNameToUp, secondNameToUp, Math.round(Double.valueOf(arrString[0]))));

                    } catch (IOException eio) {
                        System.err.println("The exception of IO, the entry failed. Try again.");
                    }

                }
            }

            System.out.println("\nThe developer wasn't found.");

        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        }
    }


    // Next method lets to update information about the developer
    private void update(Developer developer) {
        String changes = developer.toString(), currentLine, arrString[];
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {

            while ((currentLine = reader.readLine()) != null) {
                arrString = currentLine.split(" ");
                if (Math.round(Double.valueOf(arrString[0])) != developer.getId() &
                        !Objects.equals(arrString[1], developer.getFirstName()) &
                        !Objects.equals(arrString[2], developer.getSecondName()))
                    builder.append(currentLine).append("\n");
                else builder.append(changes).append("\n");
            }

        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Name of this developer has wrong format. Please, write the name right.\n" +
                    "Write first and second names. If names consists of three and more words - you should" +
                    "write two names which you uses most of all.");

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("First name: ");
                String firstName = reader.readLine();
                System.out.print("Second name: ");
                String secondName = reader.readLine();

                update(new Developer(firstName, secondName, developer.getId()));

            } catch (IOException eio) {
                System.err.println("The exception of IO, the entry failed. Try again.");
            }

        }

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write(builder.toString());

        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        }

        System.out.println("Success!");
    }

    // Next method checks the availability of the developer in the file
    // If the developer won't be found the app will add a new developer
    void updateDeveloper() {
        String currentLine = null, arrString[];
        int tempValue;
        System.out.println("\nEnter the name of the developer. If the developer won't be found, " +
                "the app will add a new developer.\nFirst of all, the app checks that the file isn't empty.\n");

        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get(fileName))) {

            // Check the file for emptiness
            if ((tempValue = fileReader.read()) == -1)
                System.out.println("The file is empty. Please, add the developer " +
                        "before calling this method again.");

            else {
                System.out.println("File isn't empty.\n");

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("First name: ");
                String firstName = reader.readLine();
                System.out.print("Second name: ");
                String secondName = reader.readLine();

                char ch = (char) tempValue;
                currentLine = Character.toString(ch);
                currentLine = currentLine.concat(fileReader.readLine());

                if (currentLine.contains(firstName + " " + secondName)) {
                    System.out.println("The developer was found.\nEnter a new name.");
                    System.out.print("First name: ");
                    String changeFirstName = reader.readLine();
                    System.out.print("Second name: ");
                    String changeSecondName = reader.readLine();

                    arrString = currentLine.split(" ");
                    Developer developer = new Developer(changeFirstName, changeSecondName,
                            Math.round(Double.valueOf(arrString[0])));

                    update(developer);
                    return;
                }

                while (!Objects.equals(currentLine = fileReader.readLine(), null) &
                        !Objects.equals(currentLine, "")) {
                    if (currentLine.contains(firstName + " " + secondName)) {
                        System.out.println("The developer was found.\nEnter a new name.");
                        System.out.print("First name: ");
                        String changeFirstName = reader.readLine();
                        System.out.print("Second name: ");
                        String changeSecondName = reader.readLine();

                        arrString = currentLine.split(" ");
                        Developer developer = new Developer(changeFirstName, changeSecondName,
                                Math.round(Double.valueOf(arrString[0])));

                        update(developer);
                        return;
                    }
                }

                // This code will be work if the developer wouldn't be found
                // Offer to add a new developer
                System.out.println("The developer wasn't found.");
                boolean status = true;
                while (status) {
                    System.out.println("Do you want to add a new developer? (Y/N)");
                    String answer = reader.readLine();

                    switch (answer) {
                        case "Y":
                            addDeveloper();
                            status = false;
                            break;
                        case "N":
                            status = false;
                            break;
                        default:
                            System.out.println("You wrote other letter. Repeat the enter.");
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("The exception of IO, the entry failed. Try again.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Name of this developer has wrong format. Please, write the name right.\n" +
                    "Write first and second names. If names consists of three and more words - you should" +
                    "write two names which you uses most of all.");

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.print("First name: ");
                String firstNameToUp = reader.readLine();
                System.out.print("Second name: ");
                String secondNameToUp = reader.readLine();

                arrString = currentLine.split(" ");

                update(new Developer(firstNameToUp, secondNameToUp, Math.round(Double.valueOf(arrString[0]))));

            } catch (IOException eio) {
                System.err.println("The exception of IO, the entry failed. Try again.");
            }

        }
    }
}
