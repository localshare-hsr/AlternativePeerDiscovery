package ch.hsr.epj.ouroboros;

import ch.hsr.epj.ouroboros.statemachine.Discovery;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Hello Ouroboros");

    String path = args[0];
    System.out.println("Read file " + path);
    String[] listOfIPsToProbe = readIPfile(path);
    DiscoverdIPList.getInstance().setIdentity(findMyIPAddress().getHostAddress());

    Thread serverThread = new Thread(new OuroborosUDPServer());
    serverThread.setDaemon(true);
    serverThread.start();
    Discovery discovery = new Discovery();
    discovery.addListOfIPsToScan(listOfIPsToProbe);
    Thread discoveryThread = new Thread(discovery);
    discoveryThread.setDaemon(true);
    discoveryThread.start();

    // block main thread
    serverThread.join();
  }

  private static InetAddress findMyIPAddress() {
    InetAddress myIP = null;
    Socket socket = new Socket();
    try {
      socket.connect(new InetSocketAddress("example.com", 80));
      myIP = InetAddress.getByName(socket.getLocalAddress().getHostAddress());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return myIP;
  }

  private static String[] readIPfile(String filename) throws IOException {
    FileReader fileReader = new FileReader(filename);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    List<String> lines = new ArrayList<>();
    String line;
    while ((line = bufferedReader.readLine()) != null) {
      lines.add(line);
    }
    bufferedReader.close();
    return lines.toArray(new String[0]);
  }
}
