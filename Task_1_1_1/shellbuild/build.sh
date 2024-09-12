javac -cp "../lib/junit-platform-console-standalone-1.7.0-all.jar" ../src/main/java/ru/nsu/pereverzev/Main.java ../src/test/java/ru/nsu/pereverzev/MainTest.java -d ./build/main

if [[ $? -ne 0 ]]; then
    echo "compilation failed"
    exit 1
fi

jar -cfm ./main.jar ../manifest.txt ./build/main/ru/nsu/pereverzev/Main.class

if [[ $? -ne 0 ]]; then
    echo "building to jar failed"
    exit 2
fi

java -javaagent:"../lib/jacoco-0.8.12/lib/jacocoagent.jar=destfile=./jacoco_ex.exec" \
    -jar  "../lib/junit-platform-console-standalone-1.7.0-all.jar" -cp "./build/main" --scan-classpath

if [[ $? -ne 0 ]]; then
    echo "junit test execution failed"
    exit 3
fi

javadoc ../src/main/java/ru/nsu/pereverzev/Main.java -d ./javadoc

if [[ $? -ne 0 ]]; then
    echo "documentation generation failed"
    exit 4
fi

java -jar ../lib/jacoco-0.8.12/lib/jacococli.jar report ./jacoco_ex.exec \
    --classfiles ./build/main/ \
    --sourcefiles ../src/main/java \
    --html ./coverage \
    --name "coverage"

if [[ $? -ne 0 ]]; then
    echo "coverage report generation failed"
    exit 6
fi

echo "script finished successfully"
exit 0
