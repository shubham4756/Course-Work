# import socket programming library 
import socket 
from _thread import start_new_thread
import threading 
print_lock = threading.Lock() 
  
# thread function 
def threaded(c): 
    strg=  c.recv(1024).decode()
    if not strg:
        print('Bye')

        # lock released on exit
        print_lock.release()
        return
    strg1 = c.recv(1024).decode()
    if not strg1:
        print('Bye')
        print_lock.release()
        return
    while True: 
        # data received from client 
        data = c.recv(1024).decode()
        if not data: 
            print('Bye')   
            print_lock.release() 
            break
        if (data=='l'):
            data=str(len(strg))
            c.send(data.encode())
        elif (data=='r'):
            data = strg[::-1]
            c.send(data.encode())
        elif (data=='c') :
            data = strg.capitalize()
            c.send(data.encode())
        elif (data=='lo'):
            data = strg.lower()
            c.send(data.encode())
        elif (data=='u'):
            data = strg.upper()
            c.send(data.encode())
        elif(data == 'concat'):
            data = strg+ strg1
            c.send(data.encode())
        else:
            data = "Invalid Input"
            c.send(data.encode())
    # connection closed 
    c.close() 
  
  
def Main(): 
    host = "" 
    port = 12345
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    s.bind((host, port)) 
    # print("socket binded to port", port) 
  
    # put the socket into listening mode 
    s.listen(5) 
    print("socket is listening") 
    # a forever loop until client wants to exit 
    while True: 
        # establish connection with client 
        c, addr = s.accept() 
  
        # lock acquired by client 
        print_lock.acquire() 
        print('Connected to :', addr[0], ':', addr[1]) 
  
        # Start a new thread and return its identifier 
        start_new_thread(threaded, (c,)) 
    s.close() 

if __name__ == '__main__': 
    Main()