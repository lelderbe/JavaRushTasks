package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String username = null;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    username = message.getData();
                    if (username != null && !username.isEmpty() && !connectionMap.containsKey(username)) {
                        // OK
                        connectionMap.put(username, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    } else {
                        // Not OK
                    }
                }
            }

            return username;
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error");
            }
        }
    }

    public static void main(String[] args) {

        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("We are ON!");
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
            new Handler(socket).start();
        }

    }
}
