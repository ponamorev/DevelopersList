package com.andersen.developersList;

class Developer {
    private String firstName;
    private String secondName;
    private long id;

    Developer(String fName, String sName, long i) {
        firstName = fName;
        secondName = sName;
        id = i;
    }

    String getFirstName() {
        return firstName;
    }

    String getSecondName() {
        return secondName;
    }

    long getId() {
        return id;
    }

    // Next three methods will not use in this version of the app
    // This methods will be realize if it would be need
    void setFirstName(String fName) {
        firstName = fName;
    }

    void setSecondName(String sName) {
        secondName = sName;
    }

    void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + ". " + firstName + " " + secondName;
    }
}
