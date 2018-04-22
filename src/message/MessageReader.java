package message;

import protocol.MessageProtocol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to read messages from a file and store them
 */
public class MessageReader {
    BufferedReader bufferReader;

    /**
     * Load given file to read
     * @param fileName name of the file
     * @throws FileNotFoundException
     */
    public MessageReader(String fileName) throws FileNotFoundException {
        bufferReader = new BufferedReader(new FileReader(fileName));
    }

    /**
     * Read file and return an ArrayList of them
     * @return ArrayList of messages
     * @throws IOException
     */
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
