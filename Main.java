import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static class CategoryScore {
        String category;
        double score;

        public CategoryScore(String category, double score) {
            this.category = category;
            this.score = score;
        }

        @Override
        public String toString() {
            return category + " (" + score + ")";
        }
    }

    public static void main(String[] args) {
        String filePath = "data.txt"; // 데이터 파일 경로
        Map<String, List<CategoryScore>> wordMap = parseDocument(filePath);
        wordMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public static Map<String, List<CategoryScore>> parseDocument(String filePath) {
        Map<String, List<CategoryScore>> wordMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(": ");
                    String word = parts[0].trim();
                    String[] categories = parts[1].split(", ");
                    List<CategoryScore> scores = new ArrayList<>();

                    for (String category : categories) {
                        String[] detail = category.split("\\(");
                        String categoryName = detail[0].trim();
                        String scoreStr = detail[1].replace(")", "").trim();
                        
                        if (!scoreStr.matches("\\d+\\.\\d+")) { // 숫자 형식 확인
                            throw new IllegalArgumentException("Invalid score format: " + scoreStr);
                        }
                        
                        double score = Double.parseDouble(scoreStr);
                        scores.add(new CategoryScore(categoryName, score));
                    }

                    wordMap.put(word, scores);
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line + "; Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return wordMap;
    }
}
