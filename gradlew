#!/bin/sh
# Gradle wrapper script
DIRNAME=$(dirname "$0")
APP_BASE_NAME=$(basename "$0")
CLASSPATH="$DIRNAME/gradle/wrapper/gradle-wrapper.jar"
JAVACMD="java"
if [ -n "$JAVA_HOME" ]; then
    JAVACMD="$JAVA_HOME/bin/java"
fi
exec "$JAVACMD" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
