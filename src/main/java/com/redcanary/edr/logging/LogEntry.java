package com.redcanary.edr.logging;

import java.time.Instant;
import java.util.Optional;

public class LogEntry {

    private Instant timestamp;
    private String user;
    private String name;
    private String command;
    private long pid;

    public static String COMMA = ", ";

    public LogEntry(Instant timestamp,
                    Optional<String> user,
                    Optional<String> name,
                    Optional<String> command,
                    long pid) {
        this.timestamp = timestamp;
        this.user = user.orElse("UNKNOWN");
        this.name = name.orElse("UNKNOWN");
        this.command = command.orElse("UNKNOWN");
        this.pid = pid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(timestamp).append(COMMA);
        sb.append(user).append(COMMA);
        sb.append(name).append(COMMA);
        sb.append(pid).append(COMMA);
        sb.append(command);

        return sb.toString();
    }
}
