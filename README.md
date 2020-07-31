# Serialization and Deserialization with the Jackson Object Mapper

Simple Spring Boot app to show how we can use Jackson to convert Java objects to JSON strings and JSON strings to Java objects.

Mainly based on that most excellent resource:  https://www.baeldung.com/jackson-object-mapper-tutorial


## Features Included
- Serializing to a JSON file
- Serializing to a JSON string
- Deserializing from a JSON file
- Deserializing from a JSON string
- Reading a json string into a JsonNode structure and then fishing out individual nodes
- Reading json array data into a List

## ObjectMapper Methods Used
- readValue()
- writeValue()
- readTree()

... but wait, there's more... 
- Pretty printing JSON using the JSONObject library


# Running the App
It's a very simple Maven Spring project

- Download
- Open project in IDE to bring down the maven dependencies
- Run As Spring Boot App

The console log has simple statements to show you what it is serializing and deserializing.  See JacksonApplication.java for how it's doing it.

