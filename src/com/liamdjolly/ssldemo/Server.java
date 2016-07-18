package com.liamdjolly.ssldemo;

import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public final class Server {
  public static void main(String[] args) throws IOException {

    //set the keystore and logging to debug to see the good part!
    System.setProperty("javax.net.ssl.keyStore", "testing");
    System.setProperty("javax.net.ssl.keyStorePassword", "testing");
    System.setProperty("javax.net.debug", "all");

    //create socket
    Socket sslSocket = SSLServerSocketFactory.getDefault().createServerSocket(9999).accept();

    //set up streams
    InputStream inputStream = sslSocket.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    String message;
    while ((message = bufferedReader.readLine()) != null) {
      System.out.println(message);
    }
  }
}
