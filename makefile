JFLAGS = -g
JC = javac
J = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Constants.java \
        Ball.java \
        Board.java \
        Breakout.java \
        Brick.java \
        Paddle.java \
        Sprite.java \
        SplashWindow.java 

default: classes

run: classes
	$(J) Breakout
	 

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class