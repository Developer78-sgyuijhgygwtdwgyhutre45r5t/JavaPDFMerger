import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class UploadManager {
    private FileChooser fileChooser;
    private MergerProcessor mergerProcessor;

    public UploadManager(MergerProcessor mergerProcessor) {
        this.mergerProcessor = mergerProcessor;
        fileChooser = new FileChooser(); // Initialize the file chooser here to reuse
        setupFileChooser();
    }

    private void setupFileChooser() {
        // Configure the file chooser
        fileChooser.setTitle("Open PDF File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
    }

    public void manageUploadButton(Button uploadButton, Stage primaryStage) {
        uploadButton.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                String selectedFilePath = file.getPath();
                System.out.println("File selected: " + selectedFilePath);
                mergerProcessor.addFilePath(selectedFilePath);  // Add file path to merger
            } else {
                System.out.println("File selection cancelled.");
            }
        });
    }
}
