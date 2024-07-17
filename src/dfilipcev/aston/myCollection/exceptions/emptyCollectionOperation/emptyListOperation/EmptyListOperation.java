package dfilipcev.aston.myCollection.exceptions.emptyCollectionOperation.emptyListOperation;

import dfilipcev.aston.myCollection.exceptions.emptyCollectionOperation.EmptyCollectionOperation;

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
