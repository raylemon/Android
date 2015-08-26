package be.ifosup.vco.sugarsample.app.iface;


import be.ifosup.vco.sugarsample.app.engine.Client;

import java.util.List;

/**
 * Created by cedric on 21-05-15.
 * Global methods to manage any {@link Client} in database
 * Not very usefull for this example
 */
public interface ClientDAO {
    /**
     * Persist a {@link Client} somewhere defined by implementation
     *
     * @param client {@link Client} to store
     * @return the client id
     */
    long AddClient(Client client);

    /**
     * Update the {@link Client}
     *
     * @param client {@link Client} to update
     * @return the client id
     */
    long updateClient(Client client);

    /**
     * Delete the {@link Client} from storage
     *
     * @param client {@link Client} te remove
     * @return number of clients deleted;
     */
    int deleteClient(Client client);

    /**
     * List all clients
     *
     * @return a list of {@link Client}
     */
    List<Client> getClients();

    /**
     * Count clients stored
     *
     * @return number of {@link Client} stored in storage
     */
    long count();
}
