import socket
import sys
import threading

clients = []
ServerSocket = socket.socket()
host = 'localhost'
port = 9999
ThreadCount = 0
PClient = ""
try:
    ServerSocket.bind((host, port))
except socket.error as e:
    print(str(e))

print('Waitiing for a Connection..')
ServerSocket.listen(5)

def saysomething():

     msg1 = input("Say something to all clients  :")
     msg = "Server Says :  " + msg1
     for i in clients:

         i[0].send(msg.encode())

def addClinets():
    global ThreadCount,PClient
    while True:
        try:
            Client, address = ServerSocket.accept()
        except socket.error as e:
            break
        print('Connected to: ' + address[0] + ':' + str(address[1]))
        clients.append((Client,address))
        print("Added a client ")
        ThreadCount += 1
        # Making the first Client as primary client
        if(ThreadCount == 1):
            PClient = Client
        print("1 to say something to all clients and 3 to exit ...")

def recvMsg():
    while True:
        if(PClient != ""):
            Response = PClient.recv(1024)
            print(Response.decode('utf-8'))
            print("1 to say something to all clients and 3 to exit ...")

if __name__ == '__main__':
    t1 = threading.Thread(target=addClinets, args=())
    t2 = threading.Thread(target= recvMsg,args=())
    t1.start()
    t2.start()
    print("Add client thread has started !!")

    print("1 to say something to all clients and 3 to exit ...")
    choice = int(input())
    while(choice != 3):
        if(choice == 1):
            saysomething()

        print("1 to say something to all clients and 3 to exit ...")
        choice = int(input())

    print("Exiting....")
    ServerSocket.close()
    quit(0)