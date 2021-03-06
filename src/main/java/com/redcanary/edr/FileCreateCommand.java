package com.redcanary.edr;

import com.redcanary.edr.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

@CommandLine.Command(name = "create", description = "Creates a file")
public class FileCreateCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(FileCreateCommand.class);

    @CommandLine.Option(names = {"-f", "--file"}, description = "Name of file to create", required = true)
    String file;

    private final FileService fileService;

    public FileCreateCommand(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void run() {
        try {
            fileService.create(file);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
