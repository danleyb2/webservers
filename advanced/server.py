import argparse

# Simple server
__author__ = 'brian'
import socket

#run on localhost at port 3000
#HOST, PORT = '', 3000

def get_args():
    '''parse and return arguments passed in'''
    # Assign description to the help doc
    parser = argparse.ArgumentParser(
        description='Specify the Host and port for the server')
    # Add arguments
    parser.add_argument(
        '-l', '--host', type=str, help='Server host', required=False,default='')
    parser.add_argument(
        '-p', '--port', type=int, help='Port number', required=False,default=3000)
    
    # Array for all arguments passed to script
    args = parser.parse_args()
    # Assign args to variables
    host = args.host
    port = args.port

    print(args)
    # Return all variable values
    return host, port
    
#create socket
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)


if __name__ == '__main__':
    HOST,PORT = get_args()
    server.bind((HOST, PORT))
    server.listen(5)

    print('Server running on port  %s ...' % PORT)

    #Server Loop
    while True:
        connection, connection_address = server.accept()
        request = connection.recv(1024)


        print("From ", connection_address, request.decode('utf-8').split('\n')[0],end='\n')
        #send a response
        connection.sendall('hello world from python server'.encode())
        connection.close()
        connection=None

