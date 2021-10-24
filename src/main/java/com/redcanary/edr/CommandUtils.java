package com.redcanary.edr;

import java.io.File;
import java.nio.file.Paths;

public class CommandUtils {
    public static final File getFile(String filename) {
        return Paths.get(filename).toFile();
    }
}
