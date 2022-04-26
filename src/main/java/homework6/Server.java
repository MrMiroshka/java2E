package homework6;

import javax.swing.table.TableRowSorter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        //Socket socket = null;
        try(ServerSocket serverSocket = new ServerSocket(8386)) {// try with resources
            System.out.println("Сервер запущен, ожидаем подключения...");
            Socket socket = serverSocket.accept(); //блокирующий метод (на подобие join)
            System.out.println("Клиент подключился");
            final DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while(true){
                final String msg = inputStream.readUTF();//блокирующий метод (на подобие join)
                System.out.println("Сообщение от клиента: " + msg );
                if ("/end".equalsIgnoreCase(msg)) {
                    outputStream.writeUTF("/end");
                    break;
                }
                outputStream.writeUTF("Сообщение от клиента:" + msg);
            }
        }catch ( IOException exp){
            exp.printStackTrace();
            throw new RuntimeException(exp);
        }
        System.out.println("Сервер остановлен");
    }
}
