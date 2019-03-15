package ch.hsr.epj.ouroboros.statemachine;

import ch.hsr.epj.ouroboros.DiscoveredIPList;

class IdleState extends Discovery {

  private static final String STATE_NAME = "IDLE";

  IdleState() {
    System.out.println("change state:  ===> " + STATE_NAME);
    try {
      waitTillDiscoveryByNextPeer();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void waitTillDiscoveryByNextPeer() throws InterruptedException {

    while (!DiscoveredIPList.getInstance().hasNextPeer()) {
      Thread.sleep(10000);
    }

    state = new UpdateState();
  }
}