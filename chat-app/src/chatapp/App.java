package chatapp;

import connection.TCPConnection;

public class App {

	public static void main(String[] args) {
		if(args.length>0) {
			if(args[0].equalsIgnoreCase("server")) {
				startServer();
			}else if(args[0].equalsIgnoreCase("client")) {
				startCliente();
			}
		}
	}

	private static void startCliente() {
		// TODO Auto-generated method stub
		TCPConnection clientConnection = TCPConnection.getInstance();
		clientConnection.initAsClient("127.0.0.1", 5000);
		clientConnection.start();
				
	}

	private static void startServer() {
		// TODO Auto-generated method stub
		TCPConnection serverConnection =TCPConnection.getInstance();
		serverConnection.initAsServer(5000);
		serverConnection.start();
	}
}
