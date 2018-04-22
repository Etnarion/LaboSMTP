import protocol.SMTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SmtpClient {
    private final String newline = "\r\n";
    Socket socket;
    protected BufferedReader reader;
    protected PrintWriter printWriter;

    public void connect(String server, int port) throws IOException {
        socket = new Socket(server, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.print(SMTP.SMTP_EHLO + " test\r\n");
        printWriter.flush();
        String line = reader.readLine();
        while (!line.equals(SMTP.SMTP_OK)) {
            line = reader.readLine();
        }
    }

    public void disconnect() throws IOException {
        printWriter.print(SMTP.SMTP_QUIT + newline);
        printWriter.flush();
        reader.close();
        printWriter.close();
        socket.close();
    }

    public void sendMail(String from, ArrayList<String> receivers, String subject, String content) throws IOException {
        printWriter.print(SMTP.SMTP_MAIL + from + newline);
        printWriter.flush();
        for (String to : receivers) {
            printWriter.print(SMTP.SMTP_RCPT + to + newline);
            printWriter.flush();
        }
        printWriter.print(SMTP.SMTP_DATA + newline);
        printWriter.flush();
        reader.readLine();
        printWriter.print(SMTP.SMTP_DATA_FROM + from + newline);
        printWriter.flush();
        String line = "";
        for (String to : receivers) {
            line += to + ", ";
        }
        line = line.substring(0, line.length() - 2);
        printWriter.print(SMTP.SMTP_DATA_TO + line + newline);
        printWriter.print(SMTP.SMTP_DATA_FROM + from + newline);
        printWriter.print(SMTP.SMTP_DATA_SUBJECT + subject + newline);
        printWriter.print("\n" + content + newline);
        printWriter.print(SMTP.SMTP_DATA_END + newline);
        printWriter.flush();
        reader.readLine();
    }

    public static void main(final String[] args) throws IOException {
        SmtpClient client = new SmtpClient();
        client.connect("localhost", 2525);
        ArrayList<String> receivers = new ArrayList<>();
        receivers.add("alain@vevey.ch");
        receivers.add("benoit@gmail.com");
        receivers.add("lucas@heig-vd.ch");
        client.sendMail("samuel.mayor@heig-vd.ch", receivers, "Salut les copains", "Chers amis, je me vois dans l'obligation de finir ce mail brutalement.\n Cordialement.");
        client.disconnect();
    }
}