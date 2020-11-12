# Baccarat Client-Server Game: The Client Application

This application is one component of the overall Baccarat Client-Server game project. The client application connects to a server application [baccarat-server](https://github.com/N19htw1n9/Baccarat-Server) using the server's IP Address and a unique port number specified by the server. The client will then send user input data to the server, which will prompt the server to run the game and return the results to the client for output on the client GUI.

_(Note: This application was designed using Java 8, Apache Maven, Java Sockets, JavaFX 12, SceneBuilder, and JUnit 4)_

## Usage

For a detailed diagram of all action events in the GUI, see the wireframe

For instructions on how the game is played, see the instructions pdf

Once you are running the application and see the Welcome Screen, enter the IP Address of the machine you are running [baccarat-server](https://github.com/N19htw1n9/Baccarat-Server) on and enter `5555` (which was defined by the server program) in the Port Number field. If you are running the server program on the same machine as the client server, you may simply enter `127.0.0.1` or `localhost`

## Build/Compile (_with Maven_)

First, `cd` into the project directory, then:

```
mvn package
```

_This makes a folder named `target` in the project root, containing the `.jar` and `.class` files._

## Run (_with Maven_)

Ensure that you are running [baccarat-server](https://github.com/N19htw1n9/Baccarat-Server). Once you have built/compiled, execute this command within the same directory:

```
mvn exec:java
```
