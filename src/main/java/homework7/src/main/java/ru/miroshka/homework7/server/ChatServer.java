package ru.miroshka.homework7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {

    private final Map<String, ClientHandler> clients;

    public ChatServer() {
        this.clients = new HashMap<>();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8386);
             AuthService authService = new InMemoryAuthService()) {
            while (true) {
                System.out.println("Wait client connection...");
                final Socket socket = serverSocket.accept();
                new ClientHandler(socket, this, authService);
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToClient( String nickwhom, String message, ClientHandler sender) {
        final ClientHandler recipient = clients.get(nickwhom);
        if (recipient != null) {
            recipient.sendMessage("от " + sender.getNick() + " для " + recipient.getNick()+ ": " + message);
            sender.sendMessage("для " + nickwhom + ": " + message);
        } else {
            sender.sendMessage( nickwhom + " - такой килент не авторизован!");
        }
    }

    public boolean isNickBusy(String nick) {
        return clients.containsKey(nick);
    }

    public void subscribe(ClientHandler client) {
        clients.put(client.getNick(), client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client.getNick());
    }

    public void broadcast(String msg) {
        clients.values().forEach(client -> client.sendMessage(msg));
    }
}
