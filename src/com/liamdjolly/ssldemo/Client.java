package com.liamdjolly.ssldemo;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public final class Client {
  public static void main(String[] args) throws IOException {
    System.setProperty("javax.net.ssl.trustStore", "testing");
    System.setProperty("javax.net.ssl.trustStorePassword", "testing");

    SSLSocket sslSocket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("localhost", 9999);

    sslSocket.startHandshake();

    SSLSession session = sslSocket.getSession();
    System.out.println(String.format("Protocol: %s", session.getProtocol()));
    System.out.println(String.format("Cipher Suite: %s", session.getCipherSuite()));

    //set up streams
    InputStream inputStream = System.in;
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    OutputStream outputStream = sslSocket.getOutputStream();
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

    String message;
    while ((message = bufferedReader.readLine()) != null) {
      bufferedWriter.write(message + '\n');
      bufferedWriter.flush();
    }
  }
}
