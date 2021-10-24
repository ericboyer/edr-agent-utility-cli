package com.redcanary.edr;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

@TopCommand
@CommandLine.Command(mixinStandardHelpOptions = true,
    subcommands = {
        FileCreateCommand.class,
        FileModifyCommand.class,
        FileDeleteCommand.class,
        ProcessCommand.class,
        TransmitCommand.class
    }
)
public class EntryCommand {
}