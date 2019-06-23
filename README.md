# Openrouteservice for EIFFEL Analytics
Fork of https://github.com/giscience/openrouteservice

This repository is prepared with settings for our own server. Our ORS server runs in a docker instance on the acdevelop remote desktop: `10.1.1.48:8080/ors` only available from EIFFEL Arnhem.

## How to use openrouteservice
Use it as you would use the service with your API key:

* POST or GET requests: https://openrouteservice.org/dev/#/api-docs
* Python API: https://github.com/GIScience/openrouteservice-py
    * Make sure you have an up-to-date install of the Python package openrouteservice.
* JS and R APIs are also avialable. See the [official website](https://openrouteservice.org/).

## How to start the container for the first time
Clone this repository to your computer.

The (large) OSM file is not included in the repo. So download netherlands-latest.osm.pbf from https://download.geofabrik.de/europe/netherlands.html and place it in the docker/data/ folder.

Open a terminal or PowerShell and run the following commands:
```
cd docker
docker-compose up -d
```
Which will build the image and starts it in detached mode. This will run  `ors-app` in the background and will return the prompt. When the image is (re)build, openrouteservice needs to (re)build its graphs. This may take 10+ minutes.

### Follow the progress
Use `docker ps` to see the running docker processes.

Use `docker stats` to see CPU and memory usage of the running containers.

Use `docker logs --follow ors-app` to follow the log files of `ors-app`. To stop following the logs, press `Ctrl`+`C`, maybe press twice on Windows.

### Tests
See `localhost:8080/ors/health` in the browser (or `10.1.1.48:8080/ors/health` when not on acdevelop) to check if ORS is ready to accept requests. 

Go to `localhost:8080/ors/routes?profile=driving-car&coordinates=5.8987,51.9851|4.8994,52.3791` to request the route from Arnhem to Amsterdam. If the browser returns a JSON with the navigation instructions you have succesfully set up openrouteservice.

## How to change the OSM file
I'm not exactly sure how to do this, but it should be something like this:
1. Shutdown `ors-app` with `docker-compose down`
1. Put the OSM file in the data folder
1. Change the parameter OSM_FILE in docker-compose.yml
1. Remove the folders graphs and elevation_cache
1. Run `docker-compose up -d --build` to rebuild the image with the new parameters

## How to change app.config
Again, I'm not entirely sure how to do this, but this works:
1. Shutdown `ors-app` with `docker-compose down`
1. Make changes in app.config
1. Run `docker-compose up -d --build` to rebuild the image with the new config

## Stop and start the container
Just stopping and starting the container doesn't require a rebuild. Use:
```
docker stop ors-app
docker start ors-app
```

## Original README
See [the original README](https://github.com/GIScience/openrouteservice/blob/master/README.md) from GIScience for more info.

## For developers
How to sync this fork with the upstream repository of GIScience: https://help.github.com/en/articles/syncing-a-fork

How to enter the container: `docker exec -it ors-app bash`. You'll find the actually loaded app.config in /usr/local/tomcat/webapps/ors/WEB-INF/classes/app.config. The graphs are mounted as volumes so you can access them from outside the container in the folder docker/graphs/.

Remove all images: `docker image rm $(docker image ls -a)`

Remove all images with "none" in the name: `docker images -a | grep "none" | awk '{print $3}' | xargs docker rmi`
