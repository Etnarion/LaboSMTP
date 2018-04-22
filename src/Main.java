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
import java.util.concurrent.TimeUnit;

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

        SmtpClient client = new SmtpClient();

        // Play the prank
        try {
            client.connect("localhost", 2525);
            MessageReader msgReader = new MessageReader("messages.txt");
            Messages messages = new Messages(msgReader.readMessages());
            for (Group group : groups) {
                List<String> receivers = group.getRecievers();
                Message rdmMsg = messages.getRandomMessage();
                client.sendMail(group.getSender(), receivers, "Salut les copains", rdmMsg.getContent());
                //wait 100 miliseconds to let the server respond
                TimeUnit.MILLISECONDS.sleep(100);
            }
            client.disconnect();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
