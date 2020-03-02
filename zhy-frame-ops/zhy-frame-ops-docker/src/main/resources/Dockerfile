FROM java:8
VOLUME /home
ADD zhy-frame-ops-docker-null.jar app1197408082100228096.jar
RUN sh -c 'touch /app1197408082100228096.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app1197408082100228096.jar" ]