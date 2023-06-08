package com.example.installerwizard;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;

public class InstallationWizard extends Application {
    private ProgressBar progressBar;
    private Button nextButton;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Installation Wizard");

        progressBar = new ProgressBar();
        progressBar.setPrefWidth(200);

        nextButton = new Button("Next");
        nextButton.setOnAction(event -> {
            nextButton.setDisable(true);
            installJDK();
        });

        statusLabel = new Label("Click 'Next' to begin installation.");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(progressBar, nextButton, statusLabel);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void installJDK() {
        Task<Void> installationTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(500);
                    updateProgress(i, 10);
                }

                String jdkDownloadUrl = "https://download.oracle.com/java/20/latest/jdk-20_windows-x64_bin.exe";
                String jdkInstallerPath = "C:\\Users\\user\\Downloads\\jdk-installer.exe";
                downloadFile(jdkDownloadUrl, jdkInstallerPath);

                File installerFile = new File(jdkInstallerPath);
                if(!installerFile.exists() || installerFile.isDirectory()){
                    updateProgress(0, 10);
                    statusLabel.setText("JDK installer not found!");
                    nextButton.setDisable(false);
                    return null;
                }
                //check if jdk installer exists
                try{
                    if(Desktop.isDesktopSupported()){
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(installerFile);
                    }
                } catch (IOException e) {
                    updateProgress(0, 10);
                    statusLabel.setText("Failed to run JDK installer!");
                    nextButton.setDisable(false);
                    return null;
                }

                // Clean up the temporary installer file
                setPathEnvironmentVariable();
                installerFile.delete();
                return null;
            }
        };





        installationTask.setOnSucceeded(event -> {
            statusLabel.setText("Installation completed!");
            nextButton.setDisable(false);
        });

        installationTask.setOnFailed(event -> {
            statusLabel.setText("Installation failed!");
            nextButton.setDisable(false);
        });

        progressBar.progressProperty().bind(installationTask.progressProperty());

        Thread installationThread = new Thread(installationTask);
        installationThread.start();
    }

    private void setPathEnvironmentVariable() {
        String path = System.getenv("PATH");

        String jdkInstallationDir = "C:\\Program Files\\Java\\jdk-20";
        String newPath = path + ";" + jdkInstallationDir;

        try {
            System.setProperty("java.library.path", newPath);
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
            statusLabel.setText("Path environment variable set successfully!");
        } catch (Exception e) {
            statusLabel.setText("Path environment variable set failed!");
        }
    }


    private void downloadFile(String fileUrl, String destinationPath) throws IOException {
        URL url = new URL(fileUrl);
        try(InputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(destinationPath)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }
    }

    public static void main(String[] args) {launch(args);}
}

