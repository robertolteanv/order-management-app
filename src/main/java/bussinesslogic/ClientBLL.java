package bussinesslogic;

import dataaccess.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * This is the ClientBLL class, responsible for business logic related to clients.
 * It uses the ClientDAO class for database operations related to the Client table.
 * It also uses a list of validators to validate a client before inserting it into the database.
 *
 *
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * This is the constructor of the ClientBLL class.
     * It initializes the validators list and the ClientDAO object.
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new PhoneValidator());

        clientDAO = new ClientDAO();
    }
    /**
     * This method is used to find a client by its ID.
     * It calls the findById method of the ClientDAO object.
     *
     * @param id This is the ID of the client to be found.
     * @return Client This returns the client with the given ID.
     * @throws NoSuchElementException If the client with the given ID is not found.
     */
    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id " + id + " was not found!");
        }
        return client;
    }


    /**
     * This method is used to insert a client into the database.
     * It validates the client before inserting it.
     * It calls the insertClient method of the ClientDAO object.
     *
     * @param client This is the client to be inserted.
     */
    public void insertClient(Client client) {
        // Validate the client before inserting
        for (Validator<Client> validator : validators) {
            validator.validate(client);
        }
        clientDAO.insertClient(client);
    }
    /**
     * This method is used to get all clients from the database.
     * It calls the selectAllClients method of the ClientDAO object.
     *
     * @return List<Client> This returns a list of all clients.
     */
    public List<Client> findAllClients() {
        return clientDAO.selectAllClients();
    }
    /**
     * This method is used to delete a client from the database by its ID.
     * It calls the deleteClient method of the ClientDAO object.
     *
     * @param clientId This is the ID of the client to be deleted.
     */
    public void deleteClient(int clientId) {
        clientDAO.deleteClient(clientId);
    }
    /**
     * This method is used to update a client in the database.
     * It calls the updateClient method of the ClientDAO object.
     *
     * @param client This is the client to be updated.
     */
    public void updateClient(Client client) {
       clientDAO.updateClient(client);
    }
}
