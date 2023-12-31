package com.example;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;

/* public class Main {
    public static void main(String[] args) {
        try {
            // Creazione del ServerSocket in ascolto sulla porta 8080
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server Andrea in ascolto sulla porta 8080...");

            while (true) {
                // Accettazione di una connessione dal client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata.");

                // Creazione dei buffer di input e output per la comunicazione con il client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Lettura delle righe inviate dal client
                String line;
                StringBuilder requestBuilder = new StringBuilder();
                while ((line = in.readLine()) != null && !line.isEmpty()) {
                    System.out.println("Ricevuto: " + line);
                    requestBuilder.append(line).append("\r\n");
                }

                // Analisi del contenuto di stringa2 (path del file)
                String request[] = requestBuilder.toString().split(" ");
                String filePath = request[1];

                // Ricerca del file sul disco
                File file = new File(filePath);

                if (file.exists()) {
                    // Il file esiste, invio della risposta al client
                    out.println("HTTP/1.1 200 OK");
                    out.println("Date: " + new Date());
                    out.println("Content-Length: " + file.length());
                    out.println("Server: meucci-server");
                    out.println("Content-Type: text/plain; charset=UTF-8");
                    out.println();

                    // Lettura e invio del contenuto del file al client
                    BufferedReader fileReader = new BufferedReader(new FileReader(file));
                    String fileLine;
                    while ((fileLine = fileReader.readLine()) != null) {
                        out.println(fileLine);
                    }
                    fileReader.close();
                    if (getContentType(file).startsWith("image/")
                            || getContentType(file).equals("application/octet-stream")) {
                        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
                        sendbinaryfile(dataOut, file);
                    }
                } else {
                    // Il file non esiste, invio della risposta al client
                    out.println("HTTP/1.1 404 Not Found");
                    out.println("Date: " + new Date());
                    out.println("Server: meucci-server");
                    out.println("Content-Type: text/plain; charset=UTF-8");
                    out.println("Content-Length: 26");
                    out.println();
                    out.println("The resource was not found");
                }

                // Chiusura del socket del client
                clientSocket.close();
                System.out.println("Connessione chiusa.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendbinaryfile(DataOutputStream output, File file) throws IOException {
        output.writeBytes("HTTP/1.1 200 Ok");
        output.writeBytes("Content-Length: " + file.length() + "\n");
        output.writeBytes("Content-Type: " + getContentType(file) + "\n");
        output.writeBytes("\n");
        InputStream is = new FileInputStream(file);
        byte[] buf = new byte[8192];
        int n;
        while ((n = is.read(buf)) != -1) {
            output.write(buf, 0, n);
        }
        is.close();
    }

    private static String getContentType(File f) {
        String[] s = f.getName().split("\\.");
        String ext = s[s.length - 1];
        switch (ext) {
            case "html":
            case "htm":
                return "text/html";
            case "png":
                return "image/jpeg";
            case "css":
                return "text/css";
        }
        return ext;
    }
}
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Creazione del ServerSocket in ascolto sulla porta 8080
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server Andrea in ascolto sulla porta 8080...");

            while (true) {
                // Accettazione di una connessione dal client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata.");

                // Creazione dei buffer di input e output per la comunicazione con il client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Lettura delle righe inviate dal client
                String line;
                StringBuilder requestBuilder = new StringBuilder();
                while ((line = in.readLine()) != null && !line.isEmpty()) {
                    System.out.println("Ricevuto: " + line);
                    requestBuilder.append(line).append("\r\n");
                }

                // Analisi del contenuto di stringa2 (path del file)
                String request[] = requestBuilder.toString().split(" ");
                String filePath = request[1];

                // Ricerca del file sul disco
                File file = new File(filePath);

                if (file.exists()) {
                    // Il file esiste, invio della risposta al client
                    out.println("HTTP/1.1 200 OK");
                    out.println("Date: " + new Date());
                    out.println("Content-Length: " + file.length());
                    out.println("Server: meucci-server");
                    out.println("Content-Type: text/plain; charset=UTF-8");
                    out.println();

                    // Lettura e invio del contenuto del file al client
                    BufferedReader fileReader = new BufferedReader(new FileReader(file));
                    String fileLine;
                    while ((fileLine = fileReader.readLine()) != null) {
                        out.println(fileLine);
                    }
                    fileReader.close();
                    if (getContentType(file).startsWith("image/") || getContentType(file).equals("application/octet-stream")) {
                        DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
                        sendbinaryfile(dataOut, file);
                    } else if (getContentType(file).equals("text/html")) {
                        Desktop.getDesktop().browse(file.toURI());
                    }
                } else {
                    // Il file non esiste, invio della risposta al client
                    out.println("HTTP/1.1 404 Not Found");
                    out.println("Date: " + new Date());
                    out.println("Server: meucci-server");
                    out.println("Content-Type: text/plain; charset=UTF-8");
                    out.println("Content-Length: 26");
                    out.println();
                    out.println("The resource was not found");
                }

                // Chiusura del socket del client
                clientSocket.close();
                System.out.println("Connessione chiusa.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendbinaryfile(DataOutputStream output, File file) throws IOException {
        output.writeBytes("HTTP/1.1 200 Ok");
        output.writeBytes("Content-Length: " + file.length() + "\n");
        output.writeBytes("Content-Type: " + getContentType(file) + "\n");
        output.writeBytes("\n");
        InputStream is = new FileInputStream(file);
        byte[] buf = new byte[8192];
        int n;
        while ((n = is.read(buf)) != -1) {
            output.write(buf, 0, n);
        }
        is.close();
    }

    private static String getContentType(File f) {
        String[] s = f.getName().split("\\.");
        String ext = s[s.length - 1];
        switch (ext) {
            case "html":
            case "htm":
                return "text/html";
            case "jpg":
                return "image/jpg";
            case "css":
                return "text/css";
        }
        return ext;
    }
}