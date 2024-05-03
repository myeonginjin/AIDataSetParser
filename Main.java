import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static String[] words;

    public static void main(String[] args) {
        int type = 0;
        String filePath = "data.txt";
        System.out.println("1.image catagory 2.style category");

        try (Scanner sc = new Scanner(System.in)) {
            type = sc.nextInt();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while((line = br.readLine()) != null) {
                String imageData = parsing(line, type);
                System.out.println(imageData);
            }

        } catch (IOException e) {
            System.out.print(e.getMessage());
        }   

    }

    public static String parsing(String str, int type) {
        switch (type) {
            case 1:         
                words = str.split(":");       
                return words[0];
            case 2:
                words = str.split(":"); 
                return words[1];
            default:
                return "";
        }

    }

}
