package MyCollection.exceptions.emptyCollectionOperation.emptyListOperation;

import MyCollection.exceptions.emptyCollectionOperation.EmptyCollectionOperation;

public class EmptyListOperation extends EmptyCollectionOperation {

    public EmptyListOperation() {
        super("List is empty");
    }

    public EmptyListOperation(String message) {
        super(message);
    }

    public EmptyListOperation(Throwable cause) {
        super(cause);
    }
}
