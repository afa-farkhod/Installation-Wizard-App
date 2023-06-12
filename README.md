# Installation-Wizard-App
Application is an installation wizard for the JDK (Java Development Kit). It is a JavaFX-based GUI application that guides users through the process of downloading and installing the JDK, as well as setting the path environment variable.

- [Oracle official documentation shows the basics of taking JavaFX application from code to deployment](https://docs.oracle.com/javafx/2/deployment/deploy_quick_start.htm) - Starting from JDK 7 update 6, JavaFX applications can be packaged as a platform-specific, self-contained application. These applications include all application resources, the Java and JavaFX runtimes, and a launcher, and they provide the same install and launch experience as native applications for the operating system.
- [Package JavaFX applications in IntelliJ IDEA IDE](https://www.jetbrains.com/help/idea/packaging-javafx-applications.html) - package JavaFX application by building the corresponding artifact (a Java archive). For JavaFX applications, IntelliJ IDEA provides a dedicated artifact type: JavaFx Application.
- [JavaFX application packaging Stackoverflow](https://stackoverflow.com/questions/30145772/what-is-the-best-way-to-deploy-javafx-application-create-jar-and-self-contained/30162808#30162808) - What is the best way to deploy JavaFX application, create JAR and self-contained applications and native installers?
- [Self-Contained Application Packaging](https://docs.oracle.com/javase/8/docs/technotes/guides/deploy/self-contained-packaging.html#BCGIBBCI) - This topic describes how to generate the package for a self-contained application. A self-contained application contains your Java or JavaFX application and the JRE needed to run the application.
- [Export JavaFX 11, 15 or 17 projects into an executable jar file with IntelliJ [2022]](https://youtu.be/F8ahBtXkQzU) - YouTube Video shows how to create a jar file from JavaFX 11 or above with IntelliJ IDEA.
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

----------------------------

- `launch.bat` file should be placed in the same directory as the `*.jar` file. And instead of running jar file launch file can be run

![image](https://github.com/af4092/Installation-Wizard-App/assets/24220136/42cfd247-b6f5-4991-a1b1-b286d325e72d)

- `launch.bat` script is the  batch script written in the Windows command prompt  sets up the necessary environment variables (JAVA_HOME, PATH, MODULE_PATH, MAIN_CLASS) and then uses the java command to run a Java application with the specified module path, classpath, and main class.
```
@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-17.0.7
set PATH=%JAVA_HOME%\bin;%PATH%
set MODULE_PATH="C:\Program Files\Java\javafx-sdk-20.0.1\lib"
set MAIN_CLASS=com.example.instalwizard.InstalWizar

java --module-path %MODULE_PATH% --add-modules javafx.controls,javafx.fxml -cp InstalWizard-1.0-SNAPSHOT.jar %MAIN_CLASS%
```
