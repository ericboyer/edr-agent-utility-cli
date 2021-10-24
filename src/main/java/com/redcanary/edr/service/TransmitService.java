package com.redcanary.edr.service;

import com.redcanary.edr.logging.ActivityLogger;
import com.redcanary.edr.logging.NetworkLogEntry;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.time.Instant;

@Dependent
public class TransmitService {

    @Inject
    ActivityLogger activityLogger;

    public void transmit(String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        String message = "hi";
        printWriter.println(message);
        printWriter.close();
        socket.close();

        NetworkLogEntry logEntry = new NetworkLogEntry(Instant.now(),
                ProcessHandle.current().info().user(),
                ProcessHandle.current().info().command(),
                ProcessHandle.current().info().commandLine(),
                ProcessHandle.current().pid(),
                socket.getInetAddress().toString() + ":" + port,
                InetAddress.getLocalHost().getHostAddress()+ ":" + socket.getLocalPort(),
                message.getBytes().length,
                "TCP");

        activityLogger.log(logEntry);
    }
}
