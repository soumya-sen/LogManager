package com.logmanager;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.logmanager.matcher.LogMatcher;
import com.logmanager.matcher.LogMatcherImpl;

public class LogManager {

	public static void main(String[] args) throws UnknownHostException, IOException {
		final String ipAddress = args[0];
		final int port = Integer.parseInt(args[1]);
		final LogMatcher logMatcher = new LogMatcherImpl(args[2]);
		
		final Socket socket = new Socket(ipAddress, port);
		final Scanner in = new Scanner(socket.getInputStream());
		while(in.hasNext()){
			final String line = cleanInput(in.nextLine());			
			if(logMatcher.match(line)){
				System.out.println(line);
			}
		}
		
		System.out.println("Server Disconnected.");
		in.close();
		socket.close();
	}

	private static String cleanInput(String nextLine) {
		return nextLine.replace("\\n", "");
	}
	
}
