# Ouroboros Prototype
Ouroboros is a network peer discovery protocol prototype implementation.


## Goal
In a non broad-/multicast network Ouroboros aime is to find other peers which run a certain application on specific port.

## Limitations
The current version only supports finding and growing functionality but no capabilities to shrink by identifying missing peers.

This limitation comes from the usage of UDP. Possible mitigation's can be implemented with timers or by switching to TCP for the periodic update process. An easier and straight forward approach would be to restart the search process after a certain amount of time.


## How the protocol works
The protocol knows three stats: SEARCHING, IDLE and UPDATE. Once the protocol is started SEARCHING will be set active. UDP datagrams to port 8640/udp will be send one by one to next higher ip address (from its own host ip address). If the last messages's peer ip address is equal to its own host's ip address the searching process stops and the state changes to IDEL. This means know other peer is online. If an other peer is found the state changes to UPDATE. From know on the host updates its list of known ip addresses from this found peer.
While in IDLE the state changes to UPDATE as soon as a discovery message from an other (new) peer in the same network is received. Once in IDLE or UPDATE there is no way back to SEARCHING.


```
                            +-----------------+
                            |                 |
             +--------------+    SEARCHING    +------------+
             |              |                 |            |
             |              +-----------------+            |
No Other     |                                             |    Other Peer
Peer Found   |                                             |    Found
             |                                             |
             |                 Other Peer                  |
             |                 Found Me                    |
       +-----v------+                               +------v-------+
       |            +------------------------------->              |
       |    IDLE    |                               |    UPDATE    |
       |            <-------------------------------+              |
       +------------+                               +--------------+
                               All Other
                               Peer Lost
```

The state change from UPDATE to IDLE is not yet implemented.


### Messages

All messages are transferred in byte format.

- SEARCHING: Client sends `Discovery` messages to incrementing ip addresses. If a peer receives such a message it respond with a its ip address followed by a semicolon `192.168.42.42;`
- UPDATE: Client sends `Update` messages to the next peer and receives a list of new ip addresses from its peer `192.168.42.50;192.198.42.23;`


## How to use it

1. Clone the repository
2. At it to your favorite IDE
3. Start maven lifecycle 'package'
4. From within the 'target' folder start the application with a list of ip addresses in your local network: `java -jar Ouroboros-1.0-SNAPSHOT.jar ip.list`

You can build your own ip adress list with `nmap -sL 192.168.1.0/24`