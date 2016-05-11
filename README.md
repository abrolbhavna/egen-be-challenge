# egen-be-challenge

The goal of this exercise is to build a system that works like an IoT platform – in this case, a personal weight tracker. This system is responsible for,
1. Consuming data sent from different sensors (emulators) - Assuming sensor sends the recorded weight to your API every 5 seconds. 
2. Storing the received data in MongoDB - In form of two collections Metrics
3. Running the data through different rules to make basic predictions - and create Alerts collection if condition is met

uses MongoDB as your datastore with two collections
metrics – stores the data that comes from sensor
alerts – stores the alerts that were created by the rules

#Spring Boot microservice
consumes data from the emulator via HTTP API and stores it in a MongoDB collection using Morphia API 

Below Metric APIs using Spring MVC,
create – this is the API that will consume data from the sensor emulator
read – reads all the metrics stored in your database
readByTimeRange – reads all the metrics that were created between the given two timestamps

Below Alert APIs using Spring MVC
read – reads all alerts that are stored in the database
readByTimeRange – reads all alerts that are created between the given two timestamps

#Rules
Rules will be triggered as and when the ‘create API’ receives new metrics from the sensor. Create these 2 rules using EasyRules,
Detects under weight – if the weight of the person drops below 10% of his base weight. Create a new alert and save it in MongoDB
Detects over weight – if the weight of the person shoots 10% over his base weight. Create a new alert and save it in MongoDB
