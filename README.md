## How is our quiz system implemented ?
* This is a secure AES encrypted quiz platform implmented in Java8.
* The very first step is to make a client and a server in two separate window.
* The whole conversation between client and server is done with AES encryption technique.
* The server allocate a separate thread to each upcoming client.
* The client is having a thread always in listening mode to get update from server.


## Requirement
* Need java8 platform
* IDE used IntelliJ idea community edition `https://www.jetbrains.com/idea/download/`
* MySQL for database 

## How to run this project
* You have two separate package `server` and `client`. Open these two package in separate window from IntelliJ IDE.
* Also create a database with name "softaQuiz" and import the file named softaQuiz.sql to your localHost(XAMPP).


### A random picture from our project
![alt text](https://github.com/UnixLoverSaurabh/SoftaQuiz/image/demo.png)
