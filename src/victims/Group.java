package victims;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Group {
    private static int nbGroups;
    private int id;
    List<String> victims;
    private int senderIndex;

    public int getSenderIndex() {
        return senderIndex;
    }

    public Group() {
        id = nbGroups;
        victims = new ArrayList<>();
    }

    public void addVictim(String email) {
        victims.add(email);
    }

    public static void incrementNumberOfGroups() {
        nbGroups++;
    }

    public int selectSender() {
        Random r = new Random();
        senderIndex = r.nextInt(victims.size());
        return senderIndex;
    }

    public String toString() {
        String s = new String();
        s += "ID: " + id + "\n";
        s += "Victims:\n";
        for (String victim : victims) {
            s += victim + "\n";
        }
        return s;
    }
}
