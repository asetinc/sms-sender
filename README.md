# SMS sender
This is an application that is hosted on AWS and is can send SMS 
trough web interface to Android device which will physically resend SMS
to specified receivers.

````
SMS object
------------
String         from
List<String>   to
String         message
int            messageLength
LocalDateTime  messageSendTime
````
