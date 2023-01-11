package cz.cvut.fel.via.kalismic.mtm.exeptions;

public class NotFoundException extends MTMException {

    public NotFoundException() {
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public static NotFoundException create(String resourceName, int identifier) {
        return new NotFoundException(resourceName + " with id " + identifier + " was not found.");
    }
}
