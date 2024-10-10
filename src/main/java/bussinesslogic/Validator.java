package bussinesslogic;

/**
 * This is the Validator interface, responsible for validating objects of type T.
 * It declares the validate method which needs to be implemented by any class that implements this interface.
 *
 *
 */
public interface Validator<T> {

    /**
     * This method is used to validate an object of type T.
     * It needs to be implemented by any class that implements this interface.
     *
     * @param t This is the object to be validated.
     */
    void validate(T t);
}