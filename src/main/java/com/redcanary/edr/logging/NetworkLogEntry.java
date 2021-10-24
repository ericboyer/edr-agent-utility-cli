package com.redcanary.edr.logging;

import java.time.Instant;
import java.util.Optional;

public class NetworkLogEntry extends LogEntry {
    private String destinationAddressPort;
    private String sourceAddressPort;
    private int dataSentInBytes;
    private String protocol;

    public NetworkLogEntry(Instant timestamp,
                           Optional<String> user,
                           Optional<String> name,
                           Optional<String> command,
                           long pid,
                           String destinationAddressPort,
                           String sourceAddressPort,
                           int dataSentInBytes,
                           String protocol) {
        super(timestamp, user, name, command, pid);
        this.destinationAddressPort = destinationAddressPort;
        this.sourceAddressPort = sourceAddressPort;
        this.dataSentInBytes = dataSentInBytes;
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(destinationAddressPort).append(COMMA);
        sb.append(sourceAddressPort).append(COMMA);
        sb.append(dataSentInBytes + "B").append(COMMA);
        sb.append(protocol).append(COMMA);

        return sb.toString();
    }
}
