all: compileServer compileManager


compileManager : 
	javac -d ./classes/ -cp ./classes/ manager/HeartBeatReceiver.java
	javac -d ./classes/ -cp ./classes/ manager/StartServer.java
	javac -d ./classes/ -cp ./classes/ manager/Manager.java

compileServer : 
	javac -d ./classes/ -cp ./classes/ server/InstructionReceiver.java
	javac -d ./classes/ -cp ./classes/ server/HeartBeatSender.java
	javac -d ./classes/ -cp ./classes/ server/Server.java

clean:
	rm -rf ./classes/*

