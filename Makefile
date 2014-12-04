#
# java -jar tetris.jar
#
tetris.jar : src\tetrisobj.java src\tetrisview.java
	javac src/*.java -d class
	jar cvfm tetris.jar META-INF/MANIFEST.MF -C class .
