#!/bin/sh
JAVA_OPTS="-server -Xms64m -Xmx200m -XX:-UseGCOverheadLimit -XX:+HeapDumpOnOutOfMemoryError -DappName=jerry -Djava.net.preferIPv4Stack=true -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:ParallelGCThreads=8 -XX:+PrintGCDetails"
"$JAVA_HOME"/bin/java $JAVA_OPTS -jar jerry.jar>/dev/null&
