# Ouroboros Prototyp
Ouroboros is a network peer discovery protocol prototype implementation.


## Goal
In a non broad-/multicast network Ouroboros aime is to find other peers which run a certain application on specific port.

## Limitations
The current version only supports finding and growing functionality but no capabilities to shrink by identifying missing peers.

## How the protocol works
The protocol knows three stats: SEARCHING, IDLE and UPDATE. Once the protocol is started SEARCHING will be set active. UDP datagrams to port 8640 will be send one by one to next higher ip address (from its own host ip address). If the last messages's peer ip address is equal to its own host's ip address the searching process stops and the state changes to IDEL. This means know other peer is online. If an other peer is found the state changes to UPDATE. From know on the host updates its list of known ip addresses from this found peer.
While in IDLE the state changes to UPDATE as soon as a discovery message from an other (new) peer in the same network is received. Once in IDLE or UPDATE there is no way back to SEARCHING.

### Messages

All messages are transferred in byte format.

- SEARCHING: Client sends `Discovery` messages to incrementing ip addresses. If a peer receives such a message it respond with a its ip address followed by a semicolon `192.168.42.42;`
- UPDATE: Client sends `Update` messages to the next peer and receives a list of new ip addresses from its peer `192.168.42.50;192.198.42.23;`



