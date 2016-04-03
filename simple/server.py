# Simple server
__author__ = 'brian'
import socket

#run on localhost at port 3000
HOST, PORT = '', 3000

#create socket
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)


if __name__ == '__main__':
    server.bind((HOST, PORT))
    server.listen(5)

    print('Server running on port  %s ...' % PORT)

    #Server Loop
    while True:
        connection, connection_address = server.accept()
        request = connection.recv(1024)


        print("From ",connection_address,request.decode('utf-8').split('\n')[0],end='\n')
        #send a response
        connection.sendall('Hello world from python server.\n'.encode())
        connection.close()
        connection=None

