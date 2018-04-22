package message;

import protocol.MessageProtocol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MessageReader {
    BufferedReader bufferReader;

    public MessageReader(String fileName) throws FileNotFoundException {
        bufferReader = new BufferedReader(new FileReader(fileName));
    }

    public ArrayList<Message> readMessages() throws IOException {
        ArrayList<Message> messages = new ArrayList<>();
        String line = bufferReader.readLine();
        String message = "";
        boolean startWrite = false;
        while (line != null) {
            if (line.equals(MessageProtocol.START_MSG)) {
                startWrite = true;
            } else if (line.equals(MessageProtocol.END_MSG)) {
                startWrite = false;
                //removes last \n in the message
                message = message.substring(0, message.length()-1);
                messages.add(new Message(message));
                message = "";
            } else {
                if (startWrite) {
                    message += line + "\n";
                }
            }
            line = bufferReader.readLine();
        }
        return messages;
    }
}
