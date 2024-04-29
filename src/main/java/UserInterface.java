
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface extends Application {
    private Button uploadButton;
    private Button mergeButton;
    public static Label helloLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PDF Merger");

        Label nameLabel = new Label("Upload some PDFs to merge");
        nameLabel.setStyle("-fx-font-size: 16px;");

        helloLabel = new Label("Version 1.0.1");
        helloLabel.setStyle("-fx-font-size: 14px;");

        uploadButton = new Button("Upload");
        mergeButton = new Button("Merge");

        MergerProcessor mergerProcessor = new MergerProcessor();
        UploadManager uploadManager = new UploadManager(mergerProcessor);

        uploadManager.manageUploadButton(uploadButton, primaryStage);
        mergerProcessor.manageMergeButton(mergeButton, primaryStage);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 20, 10, 20));

        layout.getChildren().addAll(nameLabel, helloLabel, uploadButton, mergeButton);

        Scene scene = new Scene(layout, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
