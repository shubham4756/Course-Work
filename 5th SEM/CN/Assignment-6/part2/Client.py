import socket 

def Main(): 
    # local host IP '127.0.0.1' 
    host = '127.0.0.1'
    # Define the port on which you want to connect 
    port = 12345
    s = socket.socket(socket.AF_INET,socket.SOCK_STREAM) 
    # connect to server on local computer 
    s.connect((host,port))
    str1 = input("Enter string 1 :")
    str2 = input("Enter string 2 :")
    s.send(str1.encode())
    s.send(str2.encode())
   
    while True:
        print ("\nl. Length\nr. Reverse of given string\nc. Capitalize\nlo. Lower Case\nu. Upper Case:\nconcat. for concatenation")
        option=input('Choose one option:\n')
        # message sent to server 
        s.send(option.encode())
        # messaga received from server 
        data = s.recv(1024).decode()
        # print the received message 
        print('Received from the server :',(data)) 
        ans = input('\nDo you want to continue(y/n) :') 
        if ans == 'y': 
            continue
        else: 
            break
    # close the connection 
    s.close() 
  
if __name__ == '__main__': 
    Main()