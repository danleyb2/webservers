#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <string.h>

#define PORT    3000

void die(char* message)
{
    printf("%s\n",message);
    exit(1);
}

int main(){
    
    struct sockaddr_in serverAddress;
    char *sendBuffer="Hello from C server\n";
    char receiveBuffer[2048];
    
    int serverSocket;
    if((serverSocket = socket(AF_INET,SOCK_STREAM,0))<0)
    {
        die("socket() failed.");
    }
    
    bzero(&serverAddress,sizeof(serverAddress));
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_port   = htons(PORT);
    serverAddress.sin_addr.s_addr = INADDR_ANY;
    
    if(bind(serverSocket, (struct sockaddr*)&serverAddress, sizeof(serverAddress))!=0)
    {
        die("bind() failed.");
    }
    (listen(serverSocket,20)!=0)?die("listen() failed."):printf("Server listening for connections.\n");
    
    int clientSocket;
    struct sockaddr_in client_addr;
    int addrlen=sizeof(client_addr);
    while(1)        /* server loop */
    {
        clientSocket = accept(serverSocket,(struct sockaddr*)&client_addr,&addrlen);
        
        printf("%s: %d connected.\n",inet_ntoa(client_addr.sin_addr),ntohs(client_addr.sin_port));
        
        recv(clientSocket,receiveBuffer,sizeof(receiveBuffer),0);
        send(clientSocket,sendBuffer,strlen(sendBuffer),0);
        
        close(clientSocket);
        
    }
    
    close(serverSocket);
    return 0;
}