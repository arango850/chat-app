package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPConnection extends Thread {

	private static TCPConnection instance;
	private Socket socket;
	
	private TCPConnection() {
		
	}
	
	public static TCPConnection getInstance() {
		if(instance==null) {
			instance = new TCPConnection();
		}
		return instance;
	}
	
	public void initAsServer(int port) {
		try {
			ServerSocket serversocket = new ServerSocket(port);
			System.out.println("Servidor TCP escuchando en el puerto "+port);
			this.socket = serversocket.accept();
			System.out.println("Ciente conectado: "+ socket.getInetAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initAsClient(String remoteIp, int remotePort) {
		try {
			this.socket = new Socket(remoteIp, remotePort);
			System.out.println("Conectado al servidor TCP en "+remoteIp+ ":"+remotePort);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			
			String line = reader.readLine();
			
			while(line != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		new Thread(()->{
			try {
				OutputStream os= socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter writer = new BufferedWriter(osw);
				
				writer.write(message +"\n");
				writer.flush();
				System.out.println("Mensaje enviado");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
	}
}
