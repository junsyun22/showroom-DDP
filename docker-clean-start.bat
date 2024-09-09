# clear-docker-build.bat
@echo off

echo Stopping all running containers...
docker stop $(docker ps -aq)

echo Removing all containers...
docker rm $(docker ps -aq)

echo Removing all images...
docker rmi $(docker images -q)

echo Removing dangling volumes...
docker volume prune -f

echo Removing dangling networks...
docker network prune -f

echo Removing dangling build cache...
docker builder prune -f

echo Cleaning complete. Starting docker-compose build...
docker-compose down
docker-compose up -d --build

echo Done!
