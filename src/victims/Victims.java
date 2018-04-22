package victims;

import protocol.VictimsProtocol;

import java.io.*;
import java.util.*;

public class Victims {

    private ArrayList<String> victims;

    public Victims() {
        victims = new ArrayList<>();
    }

    public List<String> getVictims() {
        return victims;
    }

    public int getNumberOfVictims() {
        return victims.size();
    }

    public void readVictims(InputStream is) throws UnsupportedEncodingException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isEmpty()) {
                victims.add(line);
            }
        }
        victims.trimToSize();
    }

    public List<Group> formGroups(int nbGroups) throws FormingGroupsException {

        if (victims.size()/ VictimsProtocol.MIN_GROUP_SIZE < nbGroups) {
            throw new FormingGroupsException("There is not enough of victims in the list");
        }

        List<Group> groups = new ArrayList<>();
        int victimsPerGroup = victims.size()/nbGroups;
        Iterator<String> it = victims.iterator();

        for (int i = 0; i < nbGroups; ++i) {
            Group group = new Group();

            if (i == nbGroups-1) {
                victimsPerGroup += victims.size()%nbGroups;
            }

            for (int j = 0; j < victimsPerGroup; ++j) {
                group.addVictim(it.next());
            }
            Group.incrementNumberOfGroups();
            groups.add(group);
        }

        return groups;
    }
}
