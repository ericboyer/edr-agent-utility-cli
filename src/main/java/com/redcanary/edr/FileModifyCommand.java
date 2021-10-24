package com.redcanary.edr;

import com.redcanary.edr.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

@CommandLine.Command(name = "modify", description = "Modifies a file")
public class FileModifyCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(FileModifyCommand.class);

    @CommandLine.Option(names = {"-f", "--file"}, description = "Name of file to modify", required = true)
    String file;

    private final FileService fileService;

    public FileModifyCommand(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void run() {
        try {
            fileService.modify(file);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
