package com.redcanary.edr.logging;

import java.time.Instant;
import java.util.Optional;

public class FileLogEntry extends LogEntry {
    private String absolutePath;
    private ActivityType activityType;

    public FileLogEntry(Instant timestamp,
                        Optional<String> user,
                        Optional<String> name,
                        Optional<String> command,
                        long pid,
                        String absolutePath,
                        ActivityType activityType) {
        super(timestamp, user, name, command, pid);
        this.absolutePath = absolutePath;
        this.activityType = activityType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(absolutePath).append(COMMA);
        sb.append(activityType).append(COMMA);

        return sb.toString();
    }
}
