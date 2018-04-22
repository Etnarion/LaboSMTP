package message;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class used to store messages and return one at random
 */
public class Messages {
    private ArrayList<Message> messages;

    /**
     * load messages in the ArrayList
     * @param messages messages to load
     */
    public Messages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    /**
     * add a new message to the list
     * @param message message to add
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * return a random message
     * @return Random message
     */
    public Message getRandomMessage() {
        final Random random = new Random();
        int chosenId = random.nextInt(Message.getCurId());
        for (Message m : messages) {
            if (m.getId() == chosenId)
                return m;
        }
        return null;
    }
}
