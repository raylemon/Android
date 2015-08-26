package be.ifosup.vco.daosample.app.iface;

import be.ifosup.vco.daosample.app.engine.Client;

import java.util.List;

/**
 * Created by cedric on 21-05-15.
 */
public interface ClientDAO {
    long AddClient(Client client);

    int updateClient(Client client);

    int deleteClient(Client client);

    List<Client> getClients();

    int count();
}
