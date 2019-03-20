package ch.hsr.epj.ouroboros;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    DiscoveryIF discovery = new ch.hsr.epj.ouroboros.discovery.Discovery();

    while (true) {
      System.out.println(Arrays.toString(discovery.getIPasList()));
      Thread.sleep(7500);
    }
  }
}
