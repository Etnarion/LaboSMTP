# SMTP client to prank your friends

## Introduction
This program is used to send fake mails to a list of friends. By inputing several email addresses, the program will scatter them in groups and make one of the address send an email of you choice to the other addresses in the group.

In this document, we will explain how to set up a dummy SMTP server so you can try it without bugging your friends.

## Set up a mock SMTP server with Docker
A mock SMTP server is used exactly the same way as a normal SMTP server. However, it won't send any mail to anyone. It is therefore used to try this kind of program without spamming.

To help you set up a mock server with Docker, we will use <a href="http://nilhcem.github.com/FakeSMTP/downloads/fakeSMTP-latest.zip">FakeSMTP</a>. We will then run it in a Docker container.

First, you need to create a file named Dockerfile in any directory and extract the downloaded jar in the same directory. Then fill the Dockerfile file with these lines :
\#Docker server Java
FROM java:8
ADD ./fakeSMTP-2.0.jar /opt/src/
WORKDIR /opt/src/
ENTRYPOINT ["java","-jar","fakeSMTP-2.0.jar", "-s", "-b", "-p", "2525", "-a", "0.0.0.0"]

You can then run the command "sudo docker build -t fakesmtpserver ." to build the container.
To run it, type "sudo docker run -p 2525:2525 fakesmtpserver".

You now have a mock SMTP server running in a Docker container!

## How to run the program
In order to run the program, you have to indicate in the "victims.txt" file located in the root directory of the program every friends email address you want to prank. To do so, you just have to write each address on a new line like so:
mail1@ok.com
mail2@gmouille.com
mail3@grable.com

The second file to fill is named "messages.txt" and must contains each message you want to send to your friends. It must be formated as shown bellow :
START_MSG
First message
END_MSG
START_MSG
Second message
END_MSG
START_MSG
Last message
END_MSG

Finally, to run the program, you just have to open a terminal on the directory where the jar file is located then run "java -jar LaboSMTP.jar" command.
