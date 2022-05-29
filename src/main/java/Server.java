import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    public void start() throws IOException {
        ServerSocket servSocket = new ServerSocket(23445);
        while (true) {
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new
                         InputStreamReader(socket.getInputStream()))) {
                String line;
                int fibo;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) {
                        throw new NullPointerException();
                    }
                    try {
                        fibo = fibonachi(Integer.parseInt(line));
                        out.println("Echo: " + Integer.toString(fibo));
                    } catch (Exception exception){
                        out.println("Enter number");
                    }

                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public int fibonachi(int n) {
        if (n == 0){
            return 0;
        } else {
            if (n == 1){
                return 1;
            } else {
                return fibonachi(n - 1) + fibonachi(n - 2);
            }
        }
    }

    @Override
    public void run() {
        try {
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
