#!/bin/bash

javac -d bin src/*.java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls
java -cp bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls AppliVAE