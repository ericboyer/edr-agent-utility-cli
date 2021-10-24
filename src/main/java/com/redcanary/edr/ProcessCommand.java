package com.redcanary.edr;

import com.redcanary.edr.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "run", description = "Runs an executable")
public class ProcessCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessCommand.class);

    @CommandLine.Option(names = {"-e", "--executable"}, description = "Name of the executable to run", required = true)
    String file;

    @CommandLine.Parameters(description = "List of optional arguments")
    List<String> args;

    private final ProcessService processService;

    public ProcessCommand(ProcessService processService) {
        this.processService = processService;
    }

    @Override
    public void run() {
        try {
            processService.run(file, args);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
