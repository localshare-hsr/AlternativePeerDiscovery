package ch.hsr.epj.ouroboros.statemachine;

public class NetworkDiscovery implements Runnable {

  NetworkDiscovery state;
  static String[] listOfIps;

  public NetworkDiscovery() {
  }

  public void addListOfIPsToScan(String[] listOfIps) {
    NetworkDiscovery.listOfIps = listOfIps;
  }

  @Override
  public void run() {
    state = new SearchingState();
  }
}
