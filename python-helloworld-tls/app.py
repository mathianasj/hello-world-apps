from socket import *
from ssl import *

def listen():
    #create socket
    server_socket=socket(AF_INET, SOCK_STREAM)

    #Bind to an unused port on the local machine
    server_socket.bind(('localhost',8080))

    #listen for connection
    server_socket.listen (1)
    tls_server = wrap_socket(server_socket, ssl_version=PROTOCOL_TLSv1, cert_reqs=CERT_NONE, server_side=True, keyfile='./my.key', certfile='./my.crt')

    print('server started')
    
    while True:
        current_connection, address = tls_server.accept()
        while True:
            data = current_connection.recv(2048)

            if data == 'quit\r\n':
                current_connection.shutdown(1)
                current_connection.close()
                break

            elif data == 'stop\r\n':
                current_connection.shutdown(1)
                current_connection.close()
                exit()

            elif data:
                current_connection.send(data)
                print(data)
                current_connection.shutdown(1)
                current_connection.close()
                break


if __name__ == "__main__":
    try:
        listen()
    except KeyboardInterrupt:
        pass
