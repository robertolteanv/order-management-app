package bussinesslogic;

import model.Client;
/**
 * This is the EmailValidator class, responsible for validating the email of a client.
 * It implements the Validator interface and overrides the validate method.
 *
 *
 */
public class EmailValidator implements Validator<Client> {
    /**
     * This method is used to validate the email of a client.
     * It checks if the email matches the email pattern.
     * If the email does not match the pattern, it throws an IllegalArgumentException.
     *
     * @param client This is the client whose email is to be validated.
     * @throws IllegalArgumentException If the email does not match the pattern.
     */
    @Override
    public void validate(Client client) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!client.getEmail().matches(emailPattern)) {
            throw new IllegalArgumentException("Invalid email format for client: " + client.getEmail());
        }
    }
}
