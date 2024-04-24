import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.util.ArrayList;
import java.util.List;

public class MergerProcessor {
    private PDFMergerUtility mergerUtility;
    private List<String> filePaths; // Store file paths for merging
    public static String generateRandomHex(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(random.nextInt()));
        }
        // substring to exact required length to handle overshoot
        return sb.substring(0, length);
    }
    String randomHex = generateRandomHex(32);



    public MergerProcessor() {
        mergerUtility = new PDFMergerUtility();
        filePaths = new ArrayList<>(); // Initialize the list to store file paths
    }

    public void addFilePath(String path) {
        if (path != null && !path.isEmpty()) {
            filePaths.add(path);
            System.out.println("File added for merging: " + path);
        }
    }

    public void manageMergeButton(Button mergeButton, Stage primaryStage) {
        mergeButton.setOnAction(event -> {
            if (filePaths.size() < 2) {
                System.out.println("Please upload at least two PDF files before merging.");
                return;
            }
            try {
                for (String filePath : filePaths) {
                    mergerUtility.addSource(filePath);
                }
                String outputFilename = randomHex + ".pdf";
                mergerUtility.setDestinationFileName(outputFilename);
                mergerUtility.mergeDocuments(null);
                System.out.println("Documents merged successfully into " + outputFilename);
                filePaths.clear(); // Clear file paths after merging
            } catch (FileNotFoundException e) {
                System.err.println("One of the selected files was not found. Please check the file paths.");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("An IO error occurred during document merging.");
                e.printStackTrace();
            }
        });
    }
}
