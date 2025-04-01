#!/bin/bash

IP="172.31.252.202"
USER="server"
PWD="server"

PATH_BACK_VM="/home/server/xmart-zity-backend.jar"
PATH_BACK_ABDEL="/mnt/c/Users/56/Desktop/WorkSpace2/PROJET_SIRIUS-main/xmart-city-backend/target/xmart-zity-backend-1.0-SNAPSHOT-jar-with-dependencies.jar"
PATH_BACK_HOSSAME="/mnt/c/Users/arsal/OneDrive/Bureau/workspace/prototype-ing1/xmart-city-backend/target/xmart-zity-backend-1.0-SNAPSHOT-jar-with-dependencies.jar"
PATH_BACK_MOHAMMED="/mnt/c/Users/OMEN/Desktop/prototype-ing1/xmart-city-backend/target/xmart-zity-backend-1.0-SNAPSHOT-jar-with-dependencies.jar"
# Transfert du fichier vers le serveur
#scp "$PATH_BACK_ABDEL" "$USER@$IP:$PATH_BACK_VM"
scp "$PATH_BACK_MOHAMMED" "$USER@$IP:$PATH_BACK_VM"


# Exécution du fichier sur le serveur
sshpass -p "$PWD" ssh "$USER@$IP" "java -jar $PATH_BACK_VM"
