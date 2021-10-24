package com.redcanary.edr.logging;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@ApplicationScoped
public class ActivityLogger {
    private BufferedWriter bufferedWriter;

    public ActivityLogger(@ConfigProperty(name = "logfile") String logfile) throws IOException {
        this.bufferedWriter = Files.newBufferedWriter(
                Paths.get(logfile),
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);
    }

    public void log(LogEntry log) throws IOException {
        if(log != null) {
            bufferedWriter.write(String.valueOf(log));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

    public void log(FileLogEntry log) throws IOException {
        if(log != null) {
            bufferedWriter.write(String.valueOf(log));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

    public void log(NetworkLogEntry log) throws IOException {
        if(log != null) {
            bufferedWriter.write(String.valueOf(log));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
    public void log(String message) throws IOException {
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
