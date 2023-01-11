package cz.cvut.fel.via.kalismic.mtm.exeptions;

public class ForbiddenException extends MTMException {

    public ForbiddenException() {
    }

    public ForbiddenException(String msg) {
        super(msg);
    }
}
