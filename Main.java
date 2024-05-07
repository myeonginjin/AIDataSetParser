import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String[] words;
    public static String[] datas;

    public static void main(String[] args) {
        int type = 0;
        String filePath = "";
        System.out.println("1.image catagory 2.style category 3.check Passing result");

        try (Scanner sc = new Scanner(System.in)) {
            type = sc.nextInt();
        }

        if (type == 1 || type == 2) {
            filePath = "lowData.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
    
                while((line = br.readLine()) != null) {
                    String imageData = lowDataParsing(line, type);
                    System.out.println(imageData);
                }
    
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }   
        }

        else if (type == 3){
            filePath = "data.txt";
            int index = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                datas = new String[10000];
    
                while((line = br.readLine()) != null) {
                    datas[index++] = line;
                }
    
            } catch (IOException e) {
                System.out.print(e.getMessage());
            } 

            for (int i = 0; i<index; i++){
                System.out.println(datas[i]);
            }

            System.out.print(index);

        }

        else {
            System.out.print("plz choose 1~3");
        }
    }

    public static String lowDataParsing(String str, int type) {
        switch (type) {
            case 1:         
                words = str.split(":");       
                return words[0];
            case 2:
                words = str.split(":");
                String processedString = lowDataStringProcessing(words[1]);

                return processedString;
            default:
                return "";
        }

    }

    public static String lowDataStringProcessing(String line) {
        String outPut = "";
        boolean passData = false;
        
        for (int i = 0; i<line.length(); i++){
            if(line.charAt(i) == '('){
                passData = true;
            }

            else if(line.charAt(i) == ')'){
                passData = false;
                continue;
            }

            if(!passData && line.charAt(i) != ',') {
                outPut += line.substring(i, i+1);
            }
        }

        return outPut;
    }

}
