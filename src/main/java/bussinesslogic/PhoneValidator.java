package bussinesslogic;

import model.Client;
/**
 * This is the PhoneValidator class, responsible for validating the phone number of a client.
 * It implements the Validator interface and overrides the validate method.
 *
 *
 */
public class PhoneValidator implements Validator<Client> {
    /**
     * This is the PhoneValidator class, responsible for validating the phone number of a client.
     * It implements the Validator interface and overrides the validate method.
     *
     *
     */
    @Override
    public void validate(Client client) {
        String phonePattern = "^[0-9]{10}$";
        if (!client.getPhone().matches(phonePattern)) {
            throw new IllegalArgumentException("Invalid phone number format for client: " + client.getPhone());
        }
    }
}
