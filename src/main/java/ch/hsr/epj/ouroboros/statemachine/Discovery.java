package ch.hsr.epj.ouroboros.statemachine;

public class Discovery implements Runnable {

  Discovery state;
  static String[] listOfIps;

  public Discovery() {
  }

  public void addListOfIPsToScan(String[] listOfIps) {
    Discovery.listOfIps = listOfIps;
  }

  @Override
  public void run() {
    state = new SearchingState();
  }
}
