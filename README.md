# Datebook
Datebook

Google sheet - https://docs.google.com/spreadsheets/d/1V4k1WuBvvMfGZpooItVlFl47K9OmmV3TqHF4T3FKxZI/edit#gid=1386834576

Server (Java) launch:

1) Use "mvn install" command to build and test project;

2) Use "mvn spring-boot::run" to run the project;

Server stop:

1) Use Ctrl + C on your keyboard;

1.1) If it doesn't work, open another cmd (OS Windows exactly), and write below comand:

c:>netstat -ano | find "8080"  -- "command for searching 8080 open process"
TCP 0.0.0.0:8080 0.0.0.0:0 LISTENING 1196 
TCP [::]:8080 [::]:0 LISTENING 1196 
c:>taskkill /F /PID 1196 -- "command for killing process"
SUCCESS: The process with PID 1196 has been terminated.

... 