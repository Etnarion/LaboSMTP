package message;

import java.util.ArrayList;
import java.util.Random;

public class Messages {
    private ArrayList<Message> messages;

    public Messages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

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
