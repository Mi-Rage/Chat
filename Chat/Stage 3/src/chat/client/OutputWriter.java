package chat.client;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;

public class OutputWriter implements Runnable {
    private final Socket socket;
    private final Queue<String> outputMessages = new ArrayDeque<>();
    private DataOutputStream dataOutputStream;

    public OutputWriter(final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        createOutputStream();
        if (!isHasStream()) {
            return;
        }
        sent();
    }

    public void addOutputMessage(final String message) {
        if (message == null)
            return;
        outputMessages.add(message);
    }

    private void sent() {
        while (!socket.isClosed() && socket.isConnected()) {
            sleep500();
            if (hasMessage())
                sentMessage();
        }
    }

    private boolean hasMessage() {
        return outputMessages.peek() != null;
    }

    private void sleep500() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("sent method has been interrupted");
            e.printStackTrace();
        }
    }

    private boolean isHasStream() {
        if (dataOutputStream == null) {
            System.out.println("input reader is aborted");
            return false;
        }
        return true;
    }

    private void sentMessage() {
        try {
            dataOutputStream.writeUTF(outputMessages.poll());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("can't write the message");
        }
    }

    private void createOutputStream() {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("outputWriter can't create a output stream");
        }

    }

}
