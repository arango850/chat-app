package connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread {

	private DatagramSocket socket;
	private static UDPConnection instance;
	
	private UDPConnection() {
		
	}
	
	public static UDPConnection getInstance() {
		if (instance == null) {
			instance = new UDPConnection();
		}
		return instance;
	}
	
	public void setPort(int port) {
		try {
			this.socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void sendDatagram(String msg, String ip, int portDest) {
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress ipAddress = InetAddress.getByName(ip);
			DatagramPacket packet = new DatagramPacket(msg.getBytes(),msg.length(),ipAddress, portDest);
			socket.send(packet);
			System.out.println("Datagrama enviado a " + ip + ":" + portDest + " - Mensaje: " + msg);
			socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		byte[]buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		
		System.out.println("Esperando datagramas...");
		while(true) {
		
				socket.receive(packet);
			
			String msg= new String(packet.getData(),0,packet.getLength()).trim();
			System.out.println("Datagrama recibido de "+ packet.getAddress()+":"+packet.getPort()+" Mensaje "+msg);
			
		}} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

