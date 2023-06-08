package com.example.installerwizard;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
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
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextButton.setDisable(true);
                installJDK();
            }
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
                // Download JDK installer
//                https://download.oracle.com/java/20/latest/jdk-20_windows-x64_bin.exe
//                protected Void call() throws Exception {
//                // Simulate installation process
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(500);
                    updateProgress(i, 10);
                }

                String jdkDownloadUrl = "https://download.oracle.com/java/20/latest/jdk-20_windows-x64_bin.exe";
//                String jdkInstallerPath = "C:\\Program Files\\Java\\jdk-installer.exe";

                String jdkInstallerPath = "C:\\Users\\user\\Downloads\\jdk-installer.exe";
                downloadFile(jdkDownloadUrl, jdkInstallerPath);

                // Run JDK installer
                ProcessBuilder processBuilder = new ProcessBuilder(jdkInstallerPath);
                Process process = processBuilder.start();
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    // JDK installation successful
                    // Set Path environment variable
                    setPathEnvironmentVariable();
                    updateProgress(10, 10);
                } else {
                    // JDK installation failed
                    updateProgress(0, 10);
                    statusLabel.setText("Installation failed!");
                    nextButton.setDisable(false);
//                    JOptionPane.showMessageDialog(null, "JDK installation failed.");
                    // JDK installation failed
                    // Handle the failure scenario
                    // For example, show an error message

                }

                // Clean up the temporary installer file
                File installerFile = new File(jdkInstallerPath);
                installerFile.delete();

                return null;
            }
        };




//    private void installJDK() {
//        Task<Void> installationTask = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                // Simulate installation process
//                for (int i = 0; i <= 10; i++) {
//                    Thread.sleep(500);
//                    updateProgress(i, 10);
//                }
//                // Install JDK and set Path here
//
//
//                return null;
//            }
//        };

//        installationTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//                statusLabel.setText("Installation completed!");
//                nextButton.setDisable(false);
//            }
//        });

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

        try{
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", "setx", "PATH", newPath);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if(exitCode == 0){
                statusLabel.setText("Path environment variable set successfully!");
                // Path environment variable set successfully
            }else{
                statusLabel.setText("Path environment variable set failed!");
                // Path environment variable set failed

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

    public static void main(String[] args) {
        launch(args);
    }
}

