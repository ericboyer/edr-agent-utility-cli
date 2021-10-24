package com.redcanary.edr.service;

import com.redcanary.edr.logging.ActivityLogger;
import com.redcanary.edr.logging.ActivityType;
import com.redcanary.edr.logging.FileLogEntry;
import org.apache.commons.io.FileUtils;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

import static com.redcanary.edr.CommandUtils.getFile;

@Dependent
public class FileService {

    @Inject
    ActivityLogger activityLogger;

    public void create(String filename) throws IOException {
        // create file using simple touch
        File file = getFile(filename);
        FileUtils.touch(file);

        // write a log entry
        activityLogger.log(createLogEntry(file, ActivityType.FILE_CREATE));
    }

    public void modify(String filename) throws IOException {
        File file = getFile(filename);

        // create file if it doesn't already exist
        if(!file.exists()) {
            create(filename);
        }

        // open file and write current time
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(Instant.now().toString() + "\n");
        fileWriter.close();

        // write a log entry
        activityLogger.log(createLogEntry(file, ActivityType.FILE_MODIFY));
    }

    public void delete(String filename) throws IOException {
        File file = getFile(filename);

        // create file if it doesn't already exist
        if(!file.exists()) {
            create(filename);
        }

        // and delete
        file.delete();

        // write a log entry
        activityLogger.log(createLogEntry(file, ActivityType.FILE_DELETE));
    }

    private static final FileLogEntry createLogEntry(File file, ActivityType activityType) {
        return new FileLogEntry(
                Instant.now(),
                ProcessHandle.current().info().user(),
                ProcessHandle.current().info().command(),
                ProcessHandle.current().info().commandLine(),
                ProcessHandle.current().pid(),
                file.getAbsolutePath(),
                activityType);
    }


}
