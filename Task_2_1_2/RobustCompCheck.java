import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RobustCompCheck {

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

    public static void server(int port, int[] numbers) throws IOException {
        // Use at most as many parts as there are numbers, up to 100
        int partCount = Math.min(numbers.length, 100);
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server running on port " + port);

        // Divide array into parts
        int totalLength = numbers.length;
        int baseChunkSize = totalLength / partCount;
        int remainder = totalLength % partCount;
        List<int[]> parts = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < partCount; i++) {
            int end = start + baseChunkSize + (i < remainder ? 1 : 0);
            int[] part = Arrays.copyOfRange(numbers, start, end);
            parts.add(part);
            start = end;
        }

        // Task queue: indices of parts to process
        ConcurrentLinkedQueue<Integer> taskQueue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < partCount; i++) {
            taskQueue.add(i); // Each part processed once
        }

        // Flag for composite number detection
        AtomicBoolean hasComposite = new AtomicBoolean(false);

        // Wait for all parts to be processed
        CountDownLatch latch = new CountDownLatch(partCount);

        // Thread pool for clients
        ExecutorService clientExecutor = Executors.newCachedThreadPool();

        // Accept clients until all tasks are done or composite is found
        try {
            while (!hasComposite.get() && latch.getCount() > 0) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                clientExecutor.execute(() -> {
                    try (
                        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
                    ) {
                        while (!hasComposite.get() && latch.getCount() > 0) {
                            Integer taskIndex = taskQueue.poll();
                            if (taskIndex == null) {
                                System.out.println("No more tasks, sending STOP to client");
                                out.writeObject("STOP");
                                out.flush();
                                break;
                            }
                            int[] part = parts.get(taskIndex);
                            System.out.println("Sending task " + taskIndex + " to client");
                            out.writeObject(part);
                            out.flush();

                            Boolean result = (Boolean) in.readObject();
                            System.out.println("Received result for task " + taskIndex + ": " + result);
                            if (result) {
                                hasComposite.set(true);
                            }
                            latch.countDown();
                            System.out.println("Latch count decremented for task " + taskIndex + ", remaining: " + latch.getCount());
                        }
                    } catch (Exception e) {
                        System.out.println("Client processing error: " + e.getMessage());
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            // Ignore
                        }
                    }
                });
            }
        } catch (IOException e) {
            System.out.println("Server stopped accepting clients: " + e.getMessage());
        }

        // Wait for all tasks to complete or composite to be found
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting for results");
        }

        clientExecutor.shutdown();
        try {
            clientExecutor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // Ignore
        }

        // Print final result
        System.out.println("Result: " + (hasComposite.get() ? "There is composite number" : "All numbers are prime"));

        serverSocket.close();
    }

    public static void client(String host, int port) throws IOException, ClassNotFoundException {
        try (
            Socket socket = new Socket(host, port);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Клиент подключен к " + host + ":" + port);
            while (true) {
                Object obj = in.readObject();
                if (obj instanceof String && "STOP".equals(obj)) {
                    System.out.println("Client received STOP signal");
                    break;
                }
                int[] part = (int[]) obj;
                System.out.println("Client received task: " + Arrays.toString(part));
                boolean result = hasComposite(part);
                System.out.println("Client sending result: " + result);
                out.writeObject(result);
                out.flush();
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("give role: server <port> OR client <host> <port>");
            return;
        }

        String role = args[0];
        if (role.equals("server")) {
            int port = Integer.parseInt(args[1]);
            int[] numbers = {2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 4, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13, 2, 3, 5, 7, 11, 13}; // Пример массива
            server(port, numbers);
        } else if (role.equals("client")) {
            String host = args[1];
            int port = Integer.parseInt(args[2]);
            client(host, port);
        }
    }
}