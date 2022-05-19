import socket
import threading
import sys
ClientSocket = socket.socket()
ClientSocket1 = socket.socket()

# Connection with Main server
host = 'localhost'
port = 9999

print('Waiting for connection')
try:
    ClientSocket.connect((host, port))
except socket.error as e:
    print(str(e))
def recvResp():
    while True:
        try:
            Response = ClientSocket.recv(1024)
        except socket.error as e:
            print(e)
            sys.exit()
        print("\n")
        print(Response.decode('utf-8'))
        print("Enter message to be sent : ")

#Connection with Main Client
host = 'localhost'
port = 9998

print('Waiting for connection')
try:
    ClientSocket1.connect((host, port))
except socket.error as e:
    print(str(e))

if __name__ == '__main__':
    t2 = threading.Thread(target=recvResp, args=())
    t2.start()
    msg = input("Enter message to be sent : ")
    while(msg != ""):
        ClientSocket1.send(msg.encode())
        msg = input("Enter message to be sent : ")

    t2.join()
    ClientSocket.close()
    ClientSocket1.close()