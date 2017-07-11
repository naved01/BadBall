# BadBall
Classic Breakout Game.

![gamePlay](https://image.ibb.co/ebe9Sv/Screen_Shot_2017_07_11_at_11_07_24_AM.png)

## About:

Bad Ball is my attempt at building the famous game. The goal of the game is to complete the levels by breaking the bricks.
There are 3 types of bricks. Purple one requires one collision to be destroyed. The light purple requires 2 and
the gray one needs 3 collisions to be destroyed. The user has 3 lives. If he loses 3 times, he cannot play again.
When running the game, the user can pass the speed of the ball as a parameter thus making the game more challenging. The default
speed is medium. The game also contains soundtrack which is, however, commented out in order to reduce the load time. Uncomment music()
in Breakout.java to have the soundtrack. Have fun playing!


## How to Run:
To compile, type:
```
make
```
Then you can run it by typing
```
java Breakout <parameter 1> <parameter2>
```
where:
* parameter 1 is optional and indicates the FPS (Can be anything from 25-40).
* parameter 2 is also optional and indicates the speed. Can be anything among __"slow"__, __"medium"__, __"fast"__, and __"ultraFast"__

Alternatively you can both compile it and run it with the following command:
```
make run
```

To remove the .class files, type
```
make clean
```
