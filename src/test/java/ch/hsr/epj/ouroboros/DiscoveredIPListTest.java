package ch.hsr.epj.ouroboros;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ch.hsr.epj.ouroboros.discovery.DiscoveredIPList;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;

public class DiscoveredIPListTest {

  @Before
  public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
    Field instance = DiscoveredIPList.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
  }

  @Test
  public void testInsertOneNewIPBelow() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.40");
    String[] expected = {"192.168.42.40", "192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertTwoDescNewIPBelow() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.40");
    ipList.add("192.168.42.35");
    String[] expected = {"192.168.42.35", "192.168.42.40", "192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertTwoAscNewIPBelow() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.35");
    ipList.add("192.168.42.40");
    String[] expected = {"192.168.42.35", "192.168.42.40", "192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertThreeDescNewIPBelow() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.40");
    ipList.add("192.168.42.35");
    ipList.add("192.168.42.30");
    String[] expected = {"192.168.42.30", "192.168.42.35", "192.168.42.40", "192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertThreeAscNewIPBelow() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.30");
    ipList.add("192.168.42.35");
    ipList.add("192.168.42.40");
    String[] expected = {"192.168.42.30", "192.168.42.35", "192.168.42.40", "192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertThreeMixedNewIPBelow() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.35");
    ipList.add("192.168.42.30");
    ipList.add("192.168.42.40");
    String[] expected = {"192.168.42.30", "192.168.42.35", "192.168.42.40", "192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertOneNewIPAbove() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.45");
    String[] expected = {"192.168.42.42", "192.168.42.45"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertTwoDescNewIPAbove() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.45");
    ipList.add("192.168.42.50");
    String[] expected = {"192.168.42.42", "192.168.42.45", "192.168.42.50"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertTwoAscNewIPAbove() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.50");
    ipList.add("192.168.42.45");
    String[] expected = {"192.168.42.42", "192.168.42.45", "192.168.42.50"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertThreeDescNewIPAbove() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.45");
    ipList.add("192.168.42.50");
    ipList.add("192.168.42.55");
    String[] expected = {"192.168.42.42", "192.168.42.45", "192.168.42.50", "192.168.42.55"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertThreeAscNewIPAbove() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.55");
    ipList.add("192.168.42.50");
    ipList.add("192.168.42.45");
    String[] expected = {"192.168.42.42", "192.168.42.45", "192.168.42.50", "192.168.42.55"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertThreeMixedNewIPAbove() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.50");
    ipList.add("192.168.42.55");
    ipList.add("192.168.42.45");
    String[] expected = {"192.168.42.42", "192.168.42.45", "192.168.42.50", "192.168.42.55"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testInsertDiverseIPs() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");
    ipList.add("192.168.42.5");
    ipList.add("192.168.41.50");
    ipList.add("192.168.42.250");
    ipList.add("192.168.42.55");
    ipList.add("192.168.43.45");
    String[] expected = {
        "192.168.41.50",
        "192.168.42.5",
        "192.168.42.42",
        "192.168.42.55",
        "192.168.42.250",
        "192.168.43.45"
    };
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testUpdateIPListTwoEntries() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");

    String[] updateData = {"192.168.42.40", "192.168.42.42"};

    ipList.updateCompleteIPList(updateData);
    String[] expected = {"192.168.42.40", "192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testUpdateIPListOneEntries() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");

    String[] updateData = {"192.168.42.40"};

    ipList.updateCompleteIPList(updateData);
    String[] expected = {"192.168.42.40", "192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testRangeUpdateFromSmallerIP() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");

    String[] updateData = {
        "192.168.42.30",
        "192.168.42.36",
        "192.168.42.37",
        "192.168.42.39",
        "192.168.42.40",
        "192.168.42.42",
        "192.168.42.44",
        "192.168.42.50",
        "192.168.42.67"
    };
    ipList.updateCompleteIPList(updateData);

    ipList.updateRange("192.168.42.36");

    String[] expected = {
        "192.168.42.30",
        "192.168.42.36",
        "192.168.42.42",
        "192.168.42.44",
        "192.168.42.50",
        "192.168.42.67"
    };
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testRangeUpdateFromBiggerIP() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");

    String[] updateData = {
        "192.168.42.30",
        "192.168.42.36",
        "192.168.42.37",
        "192.168.42.39",
        "192.168.42.40",
        "192.168.42.42",
        "192.168.42.44",
        "192.168.42.50",
        "192.168.42.67"
    };
    ipList.updateCompleteIPList(updateData);

    ipList.updateRange("192.168.42.50");

    String[] expected = {
        "192.168.42.42", "192.168.42.44", "192.168.42.50",
    };
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testRangeUpdateFromSameIP() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");

    String[] updateData = {
        "192.168.42.42",
    };
    ipList.updateCompleteIPList(updateData);

    ipList.updateRange("192.168.42.42");

    String[] expected = {"192.168.42.42"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testRangeUpdateFromBiggerIPInSmallSet() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.137");

    String[] updateData = {
        "192.168.100.100", "192.168.100.137",
    };
    ipList.updateCompleteIPList(updateData);

    ipList.updateRange("192.168.100.140");

    String[] expected = {"192.168.100.137", "192.168.100.140"};
    assertArrayEquals(expected, ipList.getArray());
  }

  @Test
  public void testHasNextPeerNo() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.100");

    assertFalse(ipList.hasNextPeer());
  }

  @Test
  public void testHasNextPeerSelfReference() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.100");

    ipList.add("192.168.100.100");

    assertFalse(ipList.hasNextPeer());
  }

  @Test
  public void testHasNextPeerSmaller() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.100");

    ipList.add("192.168.100.50");

    assertTrue(ipList.hasNextPeer());
  }

  @Test
  public void testHasNextPeerBigger() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.100");

    ipList.add("192.168.100.150");

    assertTrue(ipList.hasNextPeer());
  }

  @Test
  public void testGetNextPeerNo() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.100");

    assertEquals("192.168.100.100", ipList.getNextPeer());
  }

  @Test
  public void testGetNextPeerSmaller() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.100");

    String peerIP = "192.168.100.50";

    ipList.add(peerIP);

    assertEquals(peerIP, ipList.getNextPeer());
  }

  @Test
  public void testGetNextPeerBigger() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.100");

    String peerIP = "192.168.100.150";

    ipList.add(peerIP);

    assertEquals(peerIP, ipList.getNextPeer());
  }

  @Test
  public void testGetNextPeerFromListAbove() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.42");

    String[] updateData = {
        "192.168.42.30",
        "192.168.42.36",
        "192.168.42.37",
        "192.168.42.39",
        "192.168.42.40",
        "192.168.42.42",
        "192.168.42.44",
        "192.168.42.50",
        "192.168.42.67"
    };
    ipList.updateCompleteIPList(updateData);

    assertEquals("192.168.42.44", ipList.getNextPeer());
  }

  @Test
  public void testGetNextPeerFromList() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.42.67");

    String[] updateData = {
        "192.168.42.30",
        "192.168.42.36",
        "192.168.42.37",
        "192.168.42.39",
        "192.168.42.40",
        "192.168.42.42",
        "192.168.42.44",
        "192.168.42.50",
        "192.168.42.67"
    };
    ipList.updateCompleteIPList(updateData);

    assertEquals("192.168.42.30", ipList.getNextPeer());
  }

  @Test
  public void testGetNextPeerFromListMixed1() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.100");

    String[] updateData = {
        "192.168.100.100", "192.168.100.140", "192.168.100.141",
    };
    ipList.updateCompleteIPList(updateData);

    assertEquals("192.168.100.140", ipList.getNextPeer());
  }

  @Test
  public void testGetNextPeerFromListMixed2() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.140");

    String[] updateData = {
        "192.168.100.100", "192.168.100.140", "192.168.100.141",
    };
    ipList.updateCompleteIPList(updateData);

    assertEquals("192.168.100.141", ipList.getNextPeer());
  }

  @Test
  public void testGetNextPeerFromListMixed3() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.141");

    String[] updateData = {
        "192.168.100.100", "192.168.100.140", "192.168.100.141",
    };
    ipList.updateCompleteIPList(updateData);

    assertEquals("192.168.100.100", ipList.getNextPeer());
  }

  @Test
  public void testGetIdentity() {
    DiscoveredIPList ipList = DiscoveredIPList.getInstance();
    ipList.setIdentity("192.168.100.141");
    assertEquals("192.168.100.141", ipList.getIdentity());

  }
}
