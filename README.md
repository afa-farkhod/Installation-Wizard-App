# Installation-Wizard-App
Application is an installation wizard for the JDK (Java Development Kit). It is a JavaFX-based GUI application that guides users through the process of downloading and installing the JDK, as well as setting the path environment variable.

```
+ DOWNLOAD => jdk-20 installer download is successful
+ INSTALLATION => jdk installation is failing
- PATH => setting the environmental path is failing
```
- When running `jar` application `javafx-sdk-20.0.1` should be also installed in the local environment, after that from the terminal following command is required to start the application:
```
java --module-path "C:/Program Files/Java/javafx-sdk-20.0.1/lib" --add-modules javafx.controls,javafx.fxml -jar InstallerWizard-1.0-SNAPSHOT.jar
```

![image](https://github.com/af4092/Installation-Wizard-App/assets/24220136/52ff325d-426d-4f49-85a9-32f7dcedcad9)
