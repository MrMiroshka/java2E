package homework6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public static void main(String[] args) {
        final Server server = new Server();
        server.start();

    }

    private void start() {
        try {// try with resources

            openConnection();

            final Scanner scanner = new Scanner(System.in);
            while (true) {
                final String msg = scanner.nextLine();
                outputStream.writeUTF(msg);
                if ("/end".equalsIgnoreCase(msg)) {
                    closeConnection();
                    break;
                }
            }
        } catch (IOException exp) {
            exp.printStackTrace();
            throw new RuntimeException(exp);
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        System.out.println("Сервер остановлен");


    }




    private void openConnection() throws IOException {
        serverSocket = new ServerSocket(8386);
        System.out.println("Сервер запущен, ожидаем подключения...");
        socket = serverSocket.accept(); //блокирующий метод (на подобие join)
        System.out.println("Клиент подключился");
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        final String msg = inputStream.readUTF();//блокирующий метод (на подобие join)
                        System.out.println("Сообщение от клиента: " + msg);
                        if ("/end".equalsIgnoreCase(msg)) {
                            outputStream.writeUTF("/end");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Соединение с сервером было разорвано!");
                } finally {
                    closeConnection();
                }
            }
        });
        thread.start();
    }

    private void closeConnection() {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }

        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }

        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
    }
}
