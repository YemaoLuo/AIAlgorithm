import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Experiment {

    public static void main(String[] args) {
        SolutionHelper1_1_ex sh11 = new SolutionHelper1_1_ex();

        String filePath = "/Users/yemaoluo/Documents/CODING/AIAlgorithm/experiment/os11.log";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            int m = 54, k = 7, j = 5, s = 3;
            for (int n = 7; n <= 25; n++) {
                bw.write("n = " + n + "\n");
                bw.write("====================================\n");

                long startTime = System.currentTimeMillis();

                long tempTime = System.currentTimeMillis();
                List<Integer> chosenSamples = sh11.generateChosenSamples(m, n);
                bw.write("Chosen samples: " + chosenSamples + "\n");
                bw.write("Time cost: " + (System.currentTimeMillis() - tempTime) + " ms\n");

                tempTime = System.currentTimeMillis();
                List<List<Integer>> possibleResults = sh11.generatePossibleResults(chosenSamples, k);
                bw.write("Possible results size: " + possibleResults.size() + "\n");
                bw.write("Time cost: " + (System.currentTimeMillis() - tempTime) + " ms" + "\n");

                tempTime = System.currentTimeMillis();
                List<List<Integer>> coverList = sh11.generateCoverList(chosenSamples, j);
                Map<List<Integer>, List<List<Integer>>> coverListMap = sh11.generateCoverListMap(coverList, s);
                bw.write("Cover list map size: " + coverListMap.size() + "\n");
                bw.write("Time cost: " + (System.currentTimeMillis() - tempTime) + " ms\n");

                tempTime = System.currentTimeMillis();
                List<List<Integer>> result = sh11.getResult(possibleResults, coverListMap);
                bw.write("Result: " + result + "\n");
                bw.write("Result size: " + result.size() + "\n");
                bw.write("Time cost: " + (System.currentTimeMillis() - tempTime) + " ms\n");
                bw.write("=====================================\n");

                bw.write("Total time cost: " + (System.currentTimeMillis() - startTime) + " ms");

                bw.write("====================================\n");
                bw.newLine();
                bw.newLine();
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}