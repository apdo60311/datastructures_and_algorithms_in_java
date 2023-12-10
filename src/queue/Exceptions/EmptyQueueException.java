package queue.Exceptions;

public class EmptyQueueException extends QueueException {

    @Override
    public String getMessage() {
        return "Empty queue Exception";
    }

}
