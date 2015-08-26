package be.ifosup.vco.sugarsample.app.engine;

import be.ifosup.vco.sugarsample.app.iface.ClientDAO;

import java.util.List;

/**
 * Created by Cedric on 27/05/2015.
 * Implementation of {@link ClientDAO} interface
 */
public class ClientDAOImpl implements ClientDAO {
    @Override
    public long AddClient(Client client) {

        return client.save();
    }

    @Override
    public long updateClient(Client client) {

        return client.save(); //method can be void
    }

    @Override
    public int deleteClient(Client client) {
        client.delete();
        return 1; //method can be void
    }

    @Override
    public List<Client> getClients() {
        return Client.listAll(Client.class);
    }

    @Override
    public long count() {
        return Client.count(Client.class, null, null);
    }
}
