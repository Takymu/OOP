import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class DistributedCompositeCheck {

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean hasComposite(int[] numbers) {
        for (int num : numbers) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }

    public static void server(int port, int[] numbers, int clientCount) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server running on port " + port);

        int chunkSize = numbers.length / clientCount;
        Socket[] clientSockets = new Socket[clientCount];
        ObjectOutputStream[] outs = new ObjectOutputStream[clientCount];
        ObjectInputStream[] ins = new ObjectInputStream[clientCount];

        for (int i = 0; i < clientCount; i++) {
            clientSockets[i] = serverSocket.accept();
            outs[i] = new ObjectOutputStream(clientSockets[i].getOutputStream());
            ins[i] = new ObjectInputStream(clientSockets[i].getInputStream());
            System.out.println("Client " + i + " connected");
        }

        for (int i = 0; i < clientCount; i++) {
            int start = i * chunkSize;
            int end = (i == clientCount - 1) ? numbers.length : start + chunkSize;
            int[] part = new int[end - start];
            System.arraycopy(numbers, start, part, 0, end - start);
            outs[i].writeObject(part);
            outs[i].flush();
        }

        ExecutorService executor = Executors.newFixedThreadPool(clientCount);
        boolean hasComposite = false;
        CompletionService<Boolean> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < clientCount; i++) {
            final int index = i;
            completionService.submit(() -> (Boolean) ins[index].readObject());
        }

        for (int i = 0; i < clientCount; i++) {
            try {
                Future<Boolean> future = completionService.poll(5, TimeUnit.SECONDS);
                if (future != null && future.get()) {
                    hasComposite = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error getting result from client " + i);
            }
        }

        executor.shutdown();
        System.out.println("Result: " + (hasComposite ? "There is composit number" : "All numbers are prime"));

        for (int i = 0; i < clientCount; i++) {
            outs[i].close();
            ins[i].close();
            clientSockets[i].close();
        }
        serverSocket.close();
    }

    // client
    public static void client(String host, int port) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(host, port);
        System.out.println("Клиент подключен к " + host + ":" + port);

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        int[] part = (int[]) in.readObject();
        boolean result = hasComposite(part);

        out.writeObject(result);
        out.flush();

        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("give role: server <port> <clientCount> OR client <host> <port>");
            return;
        }

        String role = args[0];
        if (role.equals("server")) {
            int port = Integer.parseInt(args[1]);
            int clientCount = Integer.parseInt(args[2]);
            // example array
            int[] numbers = {2, 3, 5, 7, 9, 11, 13};
            server(port, numbers, clientCount);
        } else if (role.equals("client")) {
            String host = args[1];
            int port = Integer.parseInt(args[2]);
            client(host, port);
        }
    }
}