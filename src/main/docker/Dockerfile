FROM mikebychkov/alpine-jdk-11
RUN mkdir -p /usr/local/app
ADD @project.build.finalName@.jar /usr/local/app/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh