FROM tomcat:latest
COPY target/chargeManeger-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

