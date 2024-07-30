#!/bin/bash
# Démarrer Xvfb
Xvfb :99 -screen 0 1024x768x16 &
export DISPLAY=:99

# Démarrer x11vnc
x11vnc -forever -usepw -create &
sleep 5

# Démarrer l'application Java Swing
java -jar /app/projetjavajenkins.jar