FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y postgresql-client

ENTRYPOINT [ "/bin/bash" ]
CMD [ "-c", "while true ; do date; sleep 1; done;" ]