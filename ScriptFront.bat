@echo off
echo Vérification de l'existence du fichier JAR...
cd /d "C:\Users\56\Desktop\WorkSpace2\PROJET_SIRIUS-main\xmart-frontend\target"

java --module-path "C:\Users\56\Desktop\JavaFx\javafx-sdk-23.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar xmart-frontend-1.0-SNAPSHOT-jar-with-dependencies.jar
w