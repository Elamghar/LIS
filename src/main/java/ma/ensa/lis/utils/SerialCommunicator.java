package ma.ensa.lis.utils;

import com.fazecast.jSerialComm.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.*;
import java.nio.charset.StandardCharsets;

public class SerialCommunicator {
    private static final Logger LOGGER =Logger.getLogger(SerialCommunicator.class.getName());
    private SerialPort serialPort;
    private final BlockingQueue<String> messageQueue =new LinkedBlockingQueue<>();
    private volatile boolean isRunning =false;
    public SerialCommunicator(String portName, int baudRate) {
        setupLogger();
        initializePort(portName, baudRate);
    }

    private void setupLogger() {
        ConsoleHandler handler =new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(handler);
        LOGGER.setLevel(Level.ALL);
    }

    private void initializePort(String portName, int baudRate) {
        serialPort =SerialPort.getCommPort(portName);
        if (serialPort == null) {
            throw new IllegalArgumentException("Port not found: " + portName);
        }
        serialPort.setBaudRate(baudRate);
        serialPort.setNumDataBits(8);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 1000, 0);
    }

    public boolean start() {
        if (serialPort == null || !serialPort.openPort()) {
            LOGGER.severe("port won't work: " + (serialPort !=null ? serialPort.getSystemPortName() : "Unknown"));
            return false;
        }
        isRunning =true;
        startReadThread();
        startWriteThread();
        LOGGER.info("workin Serial communication " + serialPort.getSystemPortName());
        return true;
    }
    private void startReadThread() {
        Thread readThread =new Thread(() -> {
            byte[] buffer =new byte[1024];
            try {
                while (isRunning) {
                    int numRead =serialPort.readBytes(buffer, buffer.length);
                    if (numRead > 0) {
                        String received =new String(buffer, 0, numRead, StandardCharsets.UTF_8).trim();
                        LOGGER.info("Received: " + received);
                        processMessage(received);
                    }
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error reading from port", e);
            } finally {
                LOGGER.info("Read thread terminated");
            }
        });
        readThread.setDaemon(true);
        readThread.start();
    }
    private void startWriteThread() {
        Thread writeThread =new Thread(() -> {
            try {
                while (isRunning) {
                    String message =messageQueue.take();
                    byte[] data =message.getBytes(StandardCharsets.UTF_8);
                    int written =serialPort.writeBytes(data, data.length);
                    if (written !=data.length) {
                        LOGGER.warning("message won't be comp");
                    } else {
                        LOGGER.info("Message sent: " + message);
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Write thread interrupted", e);
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error writing to port", e);
            } finally {
                LOGGER.info("Write thread terminated");
            }
        });
        writeThread.setDaemon(true);
        writeThread.start();
    }
    public void sendMessage(String message) {
        try {
            if (isRunning) {
                messageQueue.put(message);
            } else {
                LOGGER.warning("Cannot send message; communicator is not running");
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error queuing message", e);
            Thread.currentThread().interrupt();
        }
    }
    private void processMessage(String message) {
        LOGGER.info("Processing message: "+message);
    }
    public void stop() {
        isRunning =false;
        if (serialPort !=null && serialPort.isOpen()) {
            serialPort.closePort();
            LOGGER.info("closed");
        }
        LOGGER.info(" stop Serial communication");
    }
    public static void listAvailablePorts() {
        SerialPort[] ports=SerialPort.getCommPorts();
        System.out.println("ports:");
        for (SerialPort port : ports) {
            System.out.printf("- %s (%s)\n",
                    port.getSystemPortName(),
                    port.getDescriptivePortName());
        }
    }
    public static void main(String[] args) {
        listAvailablePorts();
        SerialCommunicator communicator=new SerialCommunicator("COM3",115200);
        if (communicator.start()){
            communicator.sendMessage("Helloooooooooo!");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            communicator.stop();//w
        }
    }
}
