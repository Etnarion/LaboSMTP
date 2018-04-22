import configs.ConfigFilenames;
import message.Message;
import message.MessageReader;
import message.Messages;
import protocol.VictimsProtocol;
import victims.FormingGroupsException;
import victims.Group;
import victims.Victims;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int requestNumberOfGroups() {
        System.out.println("Enter the number of groups");
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, try again");
            scanner.next();
        }

        int numberOfGroups = scanner.nextInt();

        return numberOfGroups;

    }

    public static void main(String[] args) {
        Victims victims = null;
        boolean success = false;
        List<Group> groups = null;
        // Request the number of groups and form the groups
        while (!success)
            try {
                victims = new Victims();
                victims.readVictims(new FileInputStream(ConfigFilenames.VICTIMS_FILENAME));
                int nbGroups = Main.requestNumberOfGroups();
                groups = victims.formGroups(nbGroups);
                success = true;
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (FormingGroupsException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Maximum number of groups is: "
                        + victims.getNumberOfVictims() / VictimsProtocol.MIN_GROUP_SIZE);
            }

        // Select a random group from the list of groups
        Random random = new Random();
        int groupIndex = random.nextInt(groups.size());
        Group group = groups.get(groupIndex);

        SmtpClient client = new SmtpClient();

        // Play the prank
        try {
            client.connect("localhost", 2525);
            List<String> receivers = group.getRecievers();
            MessageReader msgReader = new MessageReader("messages.txt");
            Messages messages = new Messages(msgReader.readMessages());
            Message rdmMsg = messages.getRandomMessage();
            client.sendMail(group.getSender(), receivers, "Salut les copains", rdmMsg.getContent());
            client.disconnect();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
