package victims;

/**
 * This exception is thrown when the number of groups to form is too big
 * Maximum number of groups is equal to number of victims divided by the minimum number of victims in the group
 * (3 in our case)
 */
public class FormingGroupsException extends Exception {

    public FormingGroupsException() {
        super();
    }

    public FormingGroupsException(String message) {
        super(message);
    }
}
