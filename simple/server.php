<?php
	
/* Create a TCP/IP socket. */
$server=stream_socket_server('tcp://127.0.0.1:3000');

if($server){
	echo "Server listening ...\n";

#server loop
while($connection=stream_socket_accept($server)){	
	#read one line from the request
	echo fgets($connection);
	#send data
	fwrite($connection,"Hello world from a php server.");
	#close the connection
	fclose($connection);	
}	
}else{
echo"An error occured in creating the server ...";
}
       
?>