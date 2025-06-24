#!/bin/bash

# Directory of the script
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Path to JavaFX SDK (adjust if needed)
JAVA_FX_LIB="/Users/murat/Documents/Murat's Folder/Coding Folders/Java/SDKs/javafx-sdk-22.0.2/lib"

# Path to your jar or classes
APP_JAR="$DIR/target/StatsGUI-1.0-SNAPSHOT.jar"

# Run Java with JavaFX modules
java --module-path "$JAVA_FX_LIB" --add-modules javafx.controls,javafx.fxml -jar "$APP_JAR" "$@"