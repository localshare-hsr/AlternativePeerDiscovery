package ch.hsr.epj.ouroboros.discovery;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class DiscoveredIPList {

  private static DiscoveredIPList instance;
  private SortedSet<String> setOfDiscoveredIPs;
  private String myIPAddress;

  private DiscoveredIPList() {
    setOfDiscoveredIPs = new TreeSet<>(Comparator.comparing(this::toNumeric));
  }

  public static synchronized DiscoveredIPList getInstance() {
    if (instance == null) {
      instance = new DiscoveredIPList();
    }
    return instance;
  }

  public synchronized void setIdentity(final String myIPAddress) {
    this.myIPAddress = myIPAddress;
    setOfDiscoveredIPs.add(myIPAddress);
  }

  public synchronized String getIdentity() {
    return this.myIPAddress;
  }

  /**
   * Update list of discovered ip addresses.
   *
   * <p>All previous known ip addresses will be removed. List of known ip addresses will be
   * repopulated with newIPlist entries. This method will be called during the periodic update
   * process.
   *
   * @param newIPList List of known ip addresses
   */
  public synchronized void updateCompleteIPList(final String[] newIPList) {
    /*    setOfDiscoveredIPs = new TreeSet<>(Comparator.comparing(this::toNumeric));
    setOfDiscoveredIPs.add(myIPAddress);
    setOfDiscoveredIPs.addAll(Arrays.asList(newIPList));*/

    Collections.addAll(setOfDiscoveredIPs, newIPList);
  }

  public synchronized void updateRange(final String newIPAddress) {
    if (newIPAddress.equals(myIPAddress)) {
      return;
    }
    setOfDiscoveredIPs.add(newIPAddress);
    Iterator<String> positionOfIP = findPositionOfIP(newIPAddress);
    removeIPRangeFrom(positionOfIP);
  }

  private synchronized int size() {
    return setOfDiscoveredIPs.size();
  }

  public synchronized boolean hasNextPeer() {
    boolean hasNextPeer;
    hasNextPeer = (size() != 1) || !setOfDiscoveredIPs.contains(myIPAddress);

    return hasNextPeer;
  }

  public synchronized String getNextPeer() {
    String currentIP = myIPAddress;
    if (hasNextPeer()) {

      Iterator<String> it = setOfDiscoveredIPs.iterator();
      while (it.hasNext()) {
        currentIP = it.next();

        if (currentIP.equals(myIPAddress)) {
          break;
        }
      }

      if (it.hasNext()) {
        return it.next();
      } else {
        return setOfDiscoveredIPs.iterator().next();
      }
    }

    return currentIP;
  }

  public synchronized void add(final String newIPAddress) {
    setOfDiscoveredIPs.add(newIPAddress);
  }

  public synchronized String[] getArray() {
    return setOfDiscoveredIPs.toArray(new String[0]);
  }

  private Long toNumeric(final String ip) {
    Scanner sc = new Scanner(ip).useDelimiter("\\.");
    return (sc.nextLong() << 24) + (sc.nextLong() << 16) + (sc.nextLong() << 8) + (sc.nextLong());
  }

  private Iterator<String> findPositionOfIP(final String IPToFind) {
    Iterator<String> it = setOfDiscoveredIPs.iterator();
    String currentIP;

    while (it.hasNext()) {
      currentIP = it.next();

      if (currentIP.equals(IPToFind)) {
        break;
      }
    }

    return it;
  }

  private void removeIPRangeFrom(Iterator<String> fromIPPosition) {
    Iterator<String> it = fromIPPosition;
    String currentIP;
    if (it.hasNext()) {
      currentIP = it.next();
    } else {
      it = setOfDiscoveredIPs.iterator();
      currentIP = it.next();
    }
    while (!currentIP.equals(myIPAddress)) {

      it.remove();

      if (!it.hasNext()) {
        it = setOfDiscoveredIPs.iterator();
      }

      currentIP = it.next();
    }
  }
}
