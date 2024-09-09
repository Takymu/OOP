javac -d ./build/test ../src/test/java/ru/nsu/pereverzev/Main.java

jar -cfm ./jar/main.jar ../manifest.txt ./build/main/ru/nsu/pereverzev/Main.class

javac -cp "../lib/junit-platform-console-standalone-1.7.0-all.jar" ../src/main/java/ru/nsu/pereverzev/Main.java ../src/test/java/ru/nsu/pereverzev/MainTest.java -d ./build/junit

java -jar "../lib/junit-platform-console-standalone-1.7.0-all.jar" -cp "./build/junit" --select-class ru.nsu.pereverzev.MainTest --reports-dir './reports'
