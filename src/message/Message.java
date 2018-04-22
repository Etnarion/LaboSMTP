package message;

/**
 * Class representing a message used in an email
 */
public class Message {
    private static int curId;
    private String content;
    private int id;

    /**
     * Create a message, set its id and content
     * @param content Message content
     */
    public Message(String content) {
        this.content = content;
        id = curId++;
    }

    /**
     * return message content
     * @return Message content
     */
    public String getContent() {
        return content;
    }

    /**
     * return current id
     * @return Current id
     */
    public static int getCurId() {
        return curId;
    }

    /**
     * return message id
     * @return Message id
     */
    public int getId() {
        return id;
    }
}
