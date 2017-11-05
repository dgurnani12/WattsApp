# WattsApp - Whatsapp clone for Android
A Whatsapp clone I made for Android using a local [parse server](http://parseplatform.org/) for the backend and the standard Android Studio/Java for the front end.

## Screenshots
TODO: This section will be complete once I have a cooler looking GUI.

## Installation
1) App requires a connection to Parse server endpoint. You may be able to connect to my [test endpoint](http://gurnani.ca:1337/parse/test) but I don't guarantee it. You will need to either clone the [parse server repo](https://github.com/parse-community/parse-server) and follow the installation instructions (README.md) or Set up an AWS EC2 - Parse server instance (Free Tier eligible).
2) Clone/Fork + Download this repo and open with Android studio. 
3) Modify the following files: AndroidManifests.xml, StarterApplication.java, and strings.xml to connect to your server instead of mine. HINT: Replace the 'http://192.168.0.171:1337/parse' with your Server URI, and the 'TESTKEY123' with your MASTER_API KEY and CLIENT_API key.
4) Build and run.
