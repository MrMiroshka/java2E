package homework6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public static void main(String[] args) {
        final Client client = new Client();
        client.start();
    }

    private void start(){
        try{
            openConnection();
            final Scanner scanner = new Scanner(System.in);
            while(true) {
                final String msg = scanner.nextLine();
                outputStream.writeUTF(msg);
                 if ("/end".equalsIgnoreCase(msg)){
                    break;
                }
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }

    private void closeConnection() {
        if (socket != null){
            try {
                socket.close();
            }catch (Exception exp){
                exp.printStackTrace();
            }
        }

        if (inputStream !=null){
            try {
                inputStream.close();
            }catch (Exception exp){
                exp.printStackTrace();
            }
        }

        if (outputStream !=null){
            try {
                outputStream.close();
            }catch (Exception exp){
                exp.printStackTrace();
            }
        }
    }

    private  void openConnection() throws IOException {
        socket = new Socket("localhost",8386);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        final String msg = inputStream.readUTF();
                        if ("/end".equalsIgnoreCase(msg)) {
                            break;
                        }
                        System.out.println("Сообщение от сервера: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Соединение с сервером было разорвано!");
                }finally {
                    closeConnection();
                }
            }
        });
        thread.start();
    }
}
