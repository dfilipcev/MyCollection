package MyCollection.exceptions.emptyCollectionOperation;

public class EmptyCollectionOperation extends RuntimeException {

    public EmptyCollectionOperation() {
        super("Collection is empty");
    }

    public EmptyCollectionOperation(String message) {
        super(message);
    }

    public EmptyCollectionOperation(Throwable cause) {
        super(cause);
    }
}
