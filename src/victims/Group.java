package victims;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a group
 */
public class Group {
    private static int nbGroups;
    private int id;
    List<String> victims;
    private boolean senderDefined;
    private int senderIndex;

    /**
     * Selects the sender if he is not selected yet and returns his email
     * @return the email of a sender of the group
     */
    public String getSender() {
        if (!senderDefined) {
            selectSender();
            senderDefined = true;
        }
        return victims.get(senderIndex);
    }

    /**
     * Selects the sender if it is not done yet and returns all the recievers
     * @return the list containing all the recievers of the group
     */
    public List<String> getRecievers() {
        if (!senderDefined) {
            selectSender();
            senderDefined = true;
        }
        List<String> recievers = new ArrayList<>();
        for (int i = 0; i < victims.size(); i++) {
            if(i != senderIndex) {
                recievers.add(victims.get(i));
            }
        }
        return recievers;
    }

    /**
     * Getter
     * @return the index of the sender in the victims list
     */
    public int getSenderIndex() {
        return senderIndex;
    }

    /**
     * Constructor
     */
    public Group() {
        id = nbGroups;
        victims = new ArrayList<>();
        senderDefined = false;
    }

    /**
     * Adds a victim to the group's victim list
     * @param email email of the victim to add
     */
    public void addVictim(String email) {
        victims.add(email);
    }

    /**
     * Increment the number of groups to update the following group id
     */
    public static void incrementNumberOfGroups() {
        nbGroups++;
    }

    /**
     * Randomly selects a sender
     * @return the index of the sender in the victims list
     */
    private int selectSender() {
        Random r = new Random();
        senderIndex = r.nextInt(victims.size());
        return senderIndex;
    }

    /**
     * Converts the group to String
     * @return the converted group
     */
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
