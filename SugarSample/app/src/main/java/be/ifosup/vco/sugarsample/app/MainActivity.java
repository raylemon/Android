package be.ifosup.vco.sugarsample.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import be.ifosup.vco.sugarsample.app.engine.City;
import be.ifosup.vco.sugarsample.app.engine.CityDAOImpl;
import be.ifosup.vco.sugarsample.app.engine.Client;
import be.ifosup.vco.sugarsample.app.engine.ClientDAOImpl;
import be.ifosup.vco.sugarsample.app.iface.CityDAO;
import be.ifosup.vco.sugarsample.app.iface.ClientDAO;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.edtSearch)
    EditText edtSearch;
    @InjectView(R.id.listViewClients)
    ListView listViewClients;
    @InjectView(R.id.empty)
    TextView empty;

    private ArrayAdapter<Client> clientAdapter;
    private ClientDAO clientDAO;
    private CityDAO cityDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        clientDAO = new ClientDAOImpl();
        cityDAO = new CityDAOImpl();
        clientAdapter = new ArrayAdapter<Client>(getApplicationContext(), android.R.layout.simple_list_item_1, clientDAO.getClients());
        listViewClients.setEmptyView(empty);
        listViewClients.setAdapter(clientAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (BuildConfig.DEBUG) getMenuInflater().inflate(R.menu.menu_debug, menu); //activate special menu on debug only
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_AddClient:
                Intent intent = new Intent(getApplicationContext(), AddClientActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_ClearDB:
                Client.deleteAll(Client.class);
                City.deleteAll(City.class);
                clientAdapter.clear();
                listViewClients.setAdapter(clientAdapter);
                Toast.makeText(this, "Database cleared", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clientAdapter.clear();
        clientAdapter.addAll(clientDAO.getClients());
    }

    /**
     * filter list
     */
    @OnTextChanged(R.id.edtSearch)
    public void textChanged() {
        clientAdapter.getFilter().filter(edtSearch.getText()); //Filter list
    }

    /**
     * Passes selected {@link Client} to {@link AddClientActivity}
     * @param position index of client selected
     */
    @OnItemClick(R.id.listViewClients)
    public void selClient(int position) {
        Client client = clientAdapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), AddClientActivity.class);
        intent.putExtra(AddClientActivity.CLIENT, client);
        startActivity(intent);
    }

}
