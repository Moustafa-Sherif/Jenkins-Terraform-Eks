FROM openjdk:11 AS BUILD_IMAGE
RUN apt update && apt install maven -y
RUN git clone https://github.com/Moustafa-Sherif/Jenkins-Terraform-Eks.git
RUN cd Jenkins-Terraform-Eks && mvn clean install

FROM tomcat:9-jre11

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=BUILD_IMAGE Jenkins-Terraform-Eks/target/devopsacad-v2.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
