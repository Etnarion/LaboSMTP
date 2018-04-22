package message;

public class Message {
    private static int curId;
    private String content;
    private int id;

    public Message(String content) {
        this.content = content;
        id = curId++;
    }

    public String getContent() {
        return content;
    }

    public static int getCurId() {
        return curId;
    }

    public int getId() {
        return id;
    }
}
