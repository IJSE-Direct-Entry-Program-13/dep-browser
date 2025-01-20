package lk.ijse.dep13.browser;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("google.lk", 80);
        System.out.println("Connected to: " + socket.getRemoteSocketAddress());

        new Thread(()->{
            try {
                InputStream is = socket.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                while(true){
                    byte[] buffer = new byte[1024];
                    int read = bis.read(buffer);
                    if (read == -1) break;
                    System.out.println(new String(buffer,0,read));
                }
                System.out.println("Server stopped responding");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        bos.write("Hello Machan Google \n\n".getBytes());
        bos.flush();
    }
}
