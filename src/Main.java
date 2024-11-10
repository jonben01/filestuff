import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String fileStringPath = "C:\\Users\\Jonas\\Desktop\\MyFile.txt";
    private static final Path filePath = Path.of(fileStringPath);
    public static void main(String[] args) {
        addTextAtLine("beans", 0);
        //addToFile("Some line");
        printFileContents();
    }

    private static void addToFile(String text) {
        try {
            Files.write(filePath, text.getBytes(), APPEND);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTextAtLine (String text, int lineNumber) {
        String fileInput = "";
        try {
            //READ OLD FILE
            List<String> allLines = Files.readAllLines(filePath);
            allLines.add(lineNumber, text);
            //CREAT NEW TEMP FILE
            Path tempFilePath = Path.of(fileStringPath + "_TEMP");
            Files.createFile(tempFilePath);
            // ADD ALL LINES INCLUDING NEW LINE TO TEMP FILE
            int countLines = 0;
            for (String line : allLines) {
                Files.write(tempFilePath, (line + "\r\n").getBytes(), APPEND);
                countLines++;

            }
            //overwrite old file with temp file, then delete temp.
            Files.copy(tempFilePath, filePath, StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(tempFilePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printFileContents() {
        try {
            System.out.println (Files.readString(filePath));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
