## How is our quiz system implemented?
* This is a secure AES encrypted quiz platform implmented in Java8.
* The very first step is to make a client and a server in two separate windows.
* The whole conversation between client and server is done with AES encryption technique.
* The server allocates a separate thread to each upcoming client.
* The client will always have a thread in listening mode to get updates from the server.


## Requirements
* Needs Java8 platform
* (IntelliJ IDEA Community Edition IDE)[https://www.jetbrains.com/idea/download/] used
* MySQL for database 

## How to run this project
* You will have two separate package `server` and `client`. Open these two packages in separate windows in IntelliJ IDE.
* Also create a database with the name "softaQuiz" and import the file named softaQuiz.sql to your localHost(XAMPP).


### A random picture from our project
![screenshot](https://github.com/UnixLoverSaurabh/SoftaQuiz/blob/master/image/demo.png)
