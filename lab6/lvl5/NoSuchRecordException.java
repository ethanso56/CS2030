import java.util.NoSuchElementException;

public class NoSuchRecordException extends NoSuchElementException {
    public NoSuchRecordException(String msg) {
        super(msg);
    }
}
