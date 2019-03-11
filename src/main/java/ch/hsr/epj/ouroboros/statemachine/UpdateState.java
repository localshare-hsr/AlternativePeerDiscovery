package ch.hsr.epj.ouroboros.statemachine;

import ch.hsr.epj.ouroboros.DiscoverdIPList;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class UpdateState extends Discovery {

  private static final String STATE_NAME = "UPDATE";
  private static final int PORT = 8640;

  UpdateState() {
    System.out.println("change state:  ===> " + STATE_NAME);
    try {
      getUpdateFromNextPeer();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  private void getUpdateFromNextPeer() throws IOException, InterruptedException {
    String nextPeer;
    while (DiscoverdIPList.getInstance().hasNextPeer()) {
      nextPeer = DiscoverdIPList.getInstance().getNextPeer();
      System.out.println("  - get updates from " + nextPeer);

      try (DatagramSocket datagramSocket = new DatagramSocket(0)) {

        datagramSocket.setSoTimeout(3000);
        byte[] buffer = "Update".getBytes();

        InetAddress targetAddress = InetAddress.getByName(nextPeer);
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, targetAddress, PORT);
        datagramSocket.send(request);


      } catch (SocketException e) {
//      DiscoverdIPList.getInstance().removePeer(nextPeer);
      }

      Thread.sleep(9000);

    }

    state = new IdelState();
  }

}
