package ch.hsr.epj.ouroboros.statemachine;

import ch.hsr.epj.ouroboros.DiscoverdIPList;

class IdelState extends Discovery {

  private static final String STATE_NAME = "IDEL";

  IdelState() {
    System.out.println("change state:  ===> " + STATE_NAME);
    try {
      waitTillDiscoveryByNextPeer();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void waitTillDiscoveryByNextPeer() throws InterruptedException {

    while (!DiscoverdIPList.getInstance().hasNextPeer()) {
      Thread.sleep(10000);
    }

    state = new UpdateState();
  }
}
