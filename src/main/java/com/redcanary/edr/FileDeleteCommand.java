package com.redcanary.edr;

import com.redcanary.edr.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

@CommandLine.Command(name = "delete", description = "Deletes a file")
public class FileDeleteCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(FileDeleteCommand.class);

    @CommandLine.Option(names = {"-f", "--file"}, description = "Name of file to delete", required = true)
    String file;

    private final FileService fileService;

    public FileDeleteCommand(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void run() {
        try {
            fileService.delete(file);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
