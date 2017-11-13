This is a practice task. I created of a console app which works with a file contained list of developers.

This app uses input-output stream of Java (IO/NIO), Collection API. It consists of three classes: Developer.java, DeveloperDAO.java and DeveloperDAOTest.java

**Developer.java** contains constructor and three private field: *firstName (String)*, *secondName (String)* and *ID (long)*.

**DeveloperDAO.java** defines methods which works with objects of class Developer.java. For example, adding, removing developer, search developer by ID, output the list of developers, etc.

**DeveloperDAOTest.java** contains method *main*. This class is used to start the app.

For using this app, you must have JDK 7 and above and follow the instruction:
1. Click button Clone of download. Then click Download ZIP.
2. Unpack the downloaded archive in any folder.
3. Open the folder with unpacked archive in a terminal (Linux, Mac) or a command line (Windows).
4. Enter next command:
```
*cd src/main/java*
*javac com/andersen/developersList/DeveloperDAOTest.java*
*java com.andersen.developersList.DeveloperDAOTest*
```
Enjoy the result. Thank you for attending :)