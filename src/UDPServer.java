import java.net.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

class UDPServer {

	private static Map<String,String> data = new HashMap<String,String>();

	public static void main(String args[]) throws Exception {
		System.out.println(args[0]);
		int Initialport = Interger.parseInt(args[0]);	//9876 Initialport
		DatagramSocket serverSocket = new DatagramSocket(Initialport);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}

	//REGISTER <plate number> <owner name>
	private static int regista (String plateNumber, String ownerName) {

		if(data.get(plateNumber) != null)
		//already exists
			return -1;
		else
		data.put(plateNumber, ownerName);
			return 0;
	}

	//LOOKUP <plate number>
	private static int verifica (String plateNumber) {
		int result = data.size();
		if(data.get(plateNumber) != null)
			return -1;
		return result;
	}
}
