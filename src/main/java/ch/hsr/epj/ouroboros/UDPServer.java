package ch.hsr.epj.ouroboros;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public abstract class UDPServer implements Runnable {

  private final int bufferSize;
  private final int port;

  private UDPServer(int port, int bufferSize) {
    this.bufferSize = bufferSize;
    this.port = port;
  }

  UDPServer(int port) {
    this(port, 512);
  }

  @Override
  public void run() {
    byte[] buffer = new byte[bufferSize];
    try {
      InetAddress myIP = InetAddress.getByName(DiscoveredIPList.getInstance().getIdentity());
      try (DatagramSocket socket = new DatagramSocket(port, myIP)) {
        socket.setSoTimeout(10000);
        System.out.println(
            "Listen on " + socket.getLocalAddress() + " port " + socket.getLocalPort());
        while (true) {
          DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
          try {
            socket.receive(incoming);
            this.respond(socket, incoming);
          } catch (SocketTimeoutException ignored) {
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }
      } catch (SocketException e) {
        e.printStackTrace();
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  public abstract void respond(DatagramSocket socket, DatagramPacket request) throws IOException;
}
