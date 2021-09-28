import java.io.*;
import java.util.Scanner;

public class Main {

    public static void saveFile(String text) {

        // Skapa ett filobjekt som representerar den fysiska filen
        // Vi anger filens namn (och ev sökväg)
        File file = new File("myfile.txt");
        try {
            // FileWriter använder vi för att kunna skriva data
            // till filen som representeras av objektet file
            FileWriter fileWriter = new FileWriter(file);
            // Skriver data till filen
            fileWriter.write(text);
            // Viktigt att stänga filen när vi är klara
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile() {
        File file = new File("alice.txt");
        String inData = "";
        try {
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()){
                inData = inData + fileScanner.nextLine();
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inData;
    }

    public static void copyFile(String original, String copy) {
        // Representerar orginalfilen som vi skall läsa ifrån
        File originalFile = new File(original);
        // Representerar filen som skall bli en kopia av orginalet
        File copyFile = new File(copy);
        try {
            // Vi använder en scanner för att läsa orginalfilen
            Scanner inputFile = new Scanner(originalFile);
            // och en filewriter för att skriva till kopian
            FileWriter outputFile = new FileWriter(copyFile);

            // Eftersom vi inte vill kopiera alla rader i orginalfilen
            // så använder vi en boolean för att indikera om vi skall skriva
            // eller inte
            boolean timeToWrite = false;

            // Vi kör så länge som vi har rader kvar att läsa in från orginalfilen
            while(inputFile.hasNextLine()) {
                // Läs en rad från orginalet
                String textLine = inputFile.nextLine();

                // Om timeToWrite är sann vill vi skriva till kopian,
                // men bara om vi inte har hittat markeringen för att
                // själva boken är slut, alltså en *** till. Den sista kollen
                // har vi bara för att inte skriva den sista raden till kopian
                if(timeToWrite && !textLine.startsWith("*** ")) {
                    outputFile.write(textLine + "\n");
                }

                // Om vi har läst in en rad som börjar med
                // ***  så låter vi vår boolean byta värde
                if(textLine.startsWith("*** ")) {
                    // Om timeToWrite är sann blir den nu falsk och vice versa
                    timeToWrite = !timeToWrite;
                }

            }
            // När vi är klara stänger vi båda filerna
            inputFile.close();
            outputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        copyFile("alice111.txt", "alice_copy.txt");
        //saveFile("Hej hej");
        //String content = readFile();
        //System.out.println(content);
    }
}
