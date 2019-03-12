package ch.hsr.epj.ouroboros.statemachine;

import ch.hsr.epj.ouroboros.DiscoveredIPList;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class SearchingState extends Discovery {

  private static final String STATE_NAME = "SEARCHING";
  private static final int PORT = 8640;

  SearchingState() {
    System.out.println("change state:  ===> " + STATE_NAME);
    try {
      searchNetwork();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void searchNetwork() throws InterruptedException {
    try {
      startNetworkScan();
    } catch (IOException e) {
      e.printStackTrace();
    }

    boolean foundOtherPeer = DiscoveredIPList.getInstance().hasNextPeer();

    if (foundOtherPeer) {
      state = new UpdateState();
    } else {
      state = new IdelState();
    }
  }

  private void startNetworkScan() throws IOException, InterruptedException {

    Thread.sleep(200); // small delay to start up the listening server first
    DatagramSocket datagramSocket = new DatagramSocket(0);
    byte[] buffer = "Discovery".getBytes();

    for (String s : startIP()) {

      InetAddress targetAddress = InetAddress.getByName(s);
      DatagramPacket request = new DatagramPacket(buffer, buffer.length, targetAddress, PORT);
      datagramSocket.send(request);
      Thread.sleep(100);

      if (DiscoveredIPList.getInstance().hasNextPeer()) {
        return;
      }
    }
  }

  private String[] startIP() {

    String[] newIPList = new String[listOfIps.length];
    int positionOfMyAddress = 0;

    for (int i = 0; i < listOfIps.length; i++) {
      if (listOfIps[i].equals(DiscoveredIPList.getInstance().getIdentity())) {
        positionOfMyAddress = i;
        break;
      }
    }

    int firstObject = positionOfMyAddress + 1;
    int length = listOfIps.length - firstObject;

    System.arraycopy(listOfIps, firstObject, newIPList, 0, length);
    System.arraycopy(listOfIps, 0, newIPList, length, newIPList.length - length);

    return newIPList;
  }
}
