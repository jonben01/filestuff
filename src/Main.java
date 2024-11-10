import java.io.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static String filePath = "C:\\Users\\Jonas\\Desktop\\MyFile.txt";
    public static void main(String[] args) {
        addTextAtLine("beans", 3);
        //addToFile("Some line");
        printFileContents();
    }

    private static void addToFile(String text) {
        try (FileWriter fw = new FileWriter(filePath, true);BufferedWriter bw = new BufferedWriter(fw);) {
            bw.append(text).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addTextAtLine (String text, int lineNumber) {
        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr);)
            {
            String fileInput = "";
            int countLines = 0;
            String line;

            while ((line = br.readLine()) != null)
            {
            if (countLines == lineNumber)
                fileInput += text + "\r\n";
            fileInput += line + "\r\n";
            countLines++;
            }

            try (FileWriter fw = new FileWriter(filePath);
                 BufferedWriter bw = new BufferedWriter(fw);)
            {
                bw.append(fileInput);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printFileContents() {

            try (FileReader fr = new FileReader(filePath);BufferedReader br = new BufferedReader(fr);) {
                String line;
                while ((line = br.readLine()) != null) { System.out.println(line); }
            } catch (IOException e) {
                throw new RuntimeException(e);


            }

    }
}
