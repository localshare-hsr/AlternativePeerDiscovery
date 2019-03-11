package ch.hsr.epj.ouroboros;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class OuroborosUDPServer extends UDPServer {

  private static final int DEFAULT_PORT = 8640;

  OuroborosUDPServer() {
    super(DEFAULT_PORT);
  }

  @Override
  public void respond(DatagramSocket socket, DatagramPacket request) throws IOException {

    byte[] requestBody = request.getData();
    DiscoverdIPList.getInstance().add(request.getAddress().getHostAddress());

    switch (requestBody[0]) {
      case 'D':
        sendMyIPAddress(socket, request);
        break;
      case 'U':
        // DiscoverdIPList.getInstance().updateRange(request.getAddress().getHostAddress());
        sendAllIPAddresses(socket, request);
        break;
      default:
        processUpdateData(requestBody);
    }
  }

  private void sendMyIPAddress(final DatagramSocket socket, DatagramPacket request)
      throws IOException {
    String bodyString = DiscoverdIPList.getInstance().getIdentity() + ";";
    byte[] body = bodyString.getBytes();
    DatagramPacket response =
        new DatagramPacket(body, body.length, request.getAddress(), DEFAULT_PORT);

    socket.send(response);
    System.out.println("Sent my ip to peer " + request.getAddress());
  }

  private void sendAllIPAddresses(final DatagramSocket socket, final DatagramPacket request)
      throws IOException {
    byte[] body = prepareSendingBody().getBytes();
    DatagramPacket response =
        new DatagramPacket(body, body.length, request.getAddress(), DEFAULT_PORT);

    socket.send(response);
    System.out.println("Sent all ips to peer " + request.getAddress());
  }

  private void processUpdateData(final byte[] data) {
    String dataString = new String(data);
    StringTokenizer stringTokenizer = new StringTokenizer(dataString, ";");
    ArrayList<String> ipData = new ArrayList<>(stringTokenizer.countTokens());
    while (stringTokenizer.hasMoreTokens()) {
      String newToken = stringTokenizer.nextToken().trim();
      if (!newToken.isEmpty()) {
        ipData.add(newToken);
      }
    }

    String[] dataArray = ipData.toArray(new String[0]);
    DiscoverdIPList.getInstance().updateCompleteIPList(dataArray);
  }

  private String prepareSendingBody() {
    StringBuilder sb = new StringBuilder();
    for (String s : DiscoverdIPList.getInstance().getArray()) {
      sb.append(s);
      sb.append(";");
    }
    return sb.toString();
  }
}
