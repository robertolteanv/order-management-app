package model;
/**
 * This is the Client class, representing a client in the system.
 *
 *
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
    /**
     * This is the constructor of the Client class.
     *
     * @param id This is the ID of the client.
     * @param name This is the name of the client.
     * @param address This is the address of the client.
     * @param email This is the email of the client.
     * @param phone This is the phone number of the client.
     */
    public Client(int id, String name, String address, String email, String phone) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * This method is used to get a string representation of the client.
     *
     * @return String This returns a string representation of the client.
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phone=" + phone + "]";
    }

}
