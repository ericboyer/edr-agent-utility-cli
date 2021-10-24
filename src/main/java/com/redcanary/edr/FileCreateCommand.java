package com.redcanary.edr;

import picocli.CommandLine;

@CommandLine.Command(name = "file", description = "Perform file activity")
public class FileCommand implements Runnable {

    @CommandLine.Option(names = {"-a", "--action"}, description = "create|modify|delete", required = true)
    String action;

    @CommandLine.Option(names = {"-f", "--file"}, description = "Path to file", required = true)
    String file;

    private final FileService fileService;

    public FileCommand(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void run() {
        fileService.printFileCommandInputs(action, file);
    }
}
