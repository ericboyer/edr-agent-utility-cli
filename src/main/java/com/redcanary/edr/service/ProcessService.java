package com.redcanary.edr.service;

import com.redcanary.edr.CommandUtils;
import com.redcanary.edr.logging.ActivityLogger;
import com.redcanary.edr.logging.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Dependent
public class ProcessService {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessService.class);

    @Inject
    ActivityLogger activityLogger;

    public void run(String file, List<String> args) throws IOException, InterruptedException {
        File executable = CommandUtils.getFile(file);
        // throw exception if it's not executable
        if(!executable.canExecute()) {
            throw new RuntimeException(file + " is not executable");
        }

        // Run the process and get info
        args.add(0, executable.getAbsolutePath());
        Process process = Runtime.getRuntime().exec(args.toArray(String[]::new));
        ProcessHandle.Info processInfo = process.info();
        long pid = process.pid();
        LOG.info("info: " + processInfo);
        process.waitFor();

        // write a log entry
        LogEntry logEntry = new LogEntry(processInfo.startInstant().get(),
                processInfo.user(),
                processInfo.command(),
                processInfo.commandLine(),
                pid);

        activityLogger.log(logEntry);
    }
}
