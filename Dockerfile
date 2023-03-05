FROM tomcat:8.5.78
COPY StudentsMarksManager.war /usr/local/tomcat/webapps/
COPY server.xml /usr/local/tomcat/conf/server.xml
EXPOSE 8080
CMD ["catalina.sh", "run"]