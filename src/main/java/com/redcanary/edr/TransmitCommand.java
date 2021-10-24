package com.redcanary.edr;

import com.redcanary.edr.service.TransmitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

@CommandLine.Command(name = "transmit", description = "Creates a network connection and transmits data")
public class TransmitCommand implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(TransmitCommand.class);

    @CommandLine.Option(names = {"-h", "--host"}, description = "Destination host IP", required = true)
    String ip;

    @CommandLine.Option(names = {"-p", "--port"}, description = "Destination port", required = true)
    int port;

    private final TransmitService transmitService;

    public TransmitCommand(TransmitService transmitService) {
        this.transmitService = transmitService;
    }

    @Override
    public void run() {
        try {
            transmitService.transmit(ip, port);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
