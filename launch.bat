@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-17.0.7
set PATH=%JAVA_HOME%\bin;%PATH%
set MODULE_PATH="C:\Program Files\Java\javafx-sdk-20.0.1\lib"
set MAIN_CLASS=com.example.instalwizard.InstalWizar

java --module-path %MODULE_PATH% --add-modules javafx.controls,javafx.fxml -cp InstalWizard-1.0-SNAPSHOT.jar %MAIN_CLASS%
