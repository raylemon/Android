package be.ifosup.vco.sugarsample.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import be.ifosup.vco.sugarsample.app.engine.City;
import be.ifosup.vco.sugarsample.app.engine.CityDAOImpl;
import be.ifosup.vco.sugarsample.app.engine.Client;
import be.ifosup.vco.sugarsample.app.engine.ClientDAOImpl;
import be.ifosup.vco.sugarsample.app.iface.CityDAO;
import be.ifosup.vco.sugarsample.app.iface.ClientDAO;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class AddClientActivity extends ActionBarActivity implements CityDialogFragment.OnFinishDialog {

    public static final String CLIENT = "CLIENT";
    @InjectView(R.id.etClientFirstName)
    EditText etClientFirstName;
    @InjectView(R.id.etClientLastName)
    EditText etClientLastName;
    @InjectView(R.id.etClientAddress)
    EditText etClientAddress;
    @InjectView(R.id.spinnerClientCity)
    Spinner spinnerClientCity;

    private ArrayAdapter<City> cityAdapter;
    private Client client;
    private boolean isUpdate;
    private CityDAO cityDAO;
    private ClientDAO clientDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        ButterKnife.inject(this);
        cityDAO = new CityDAOImpl();
        clientDAO = new ClientDAOImpl();
        cityAdapter = new ArrayAdapter<City>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cityDAO.getCities());
        spinnerClientCity.setAdapter(cityAdapter);
        Intent intent = getIntent();

        if (intent.hasExtra(CLIENT)) {
            client = intent.getParcelableExtra(CLIENT);

                etClientFirstName.setText(client.getFirstName());
                etClientLastName.setText(client.getLastName());
                etClientAddress.setText(client.getAddress());
                spinnerClientCity.setSelection(getSelByValue(client.getCity().getId()));

            isUpdate = true;
        } else {
            client = new Client();
            isUpdate = false;
        }

    }

    @OnClick(R.id.btClientAddCity)
    public void doShowDialog() {
        CityDialogFragment fragment = new CityDialogFragment();
        fragment.show(getFragmentManager(), null);
    }

    @OnClick(R.id.btnClientSave)
    public void doSave() {
        client.setFirstName(etClientFirstName.getText().toString());
        client.setLastName(etClientLastName.getText().toString());
        client.setAddress(etClientAddress.getText().toString());
        client.setCity(cityAdapter.getItem(spinnerClientCity.getSelectedItemPosition()));
        if (isUpdate) clientDAO.updateClient(client);
        else clientDAO.AddClient(client);
        doGoBack();
    }

    @OnClick(R.id.btnClientBack)
    public void doGoBack() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnClientDelete)
    public void doDelete() {
        clientDAO.deleteClient(client);
        doGoBack();
    }

    @Override
    public void getDialogResult(City city) {
        if (city != null) {
            long cityId = -1; //TODO
            if (!cityDAO.contains(city)) {
                cityId = cityDAO.addCity(city);
            }
            cityAdapter.add(city);
            spinnerClientCity.setSelection(getSelByValue(cityId));
        }
    }

    private int getSelByValue(long cityId) {
        for (int i = 0; i < cityAdapter.getCount(); i++) {
            if (cityAdapter.getItem(i).getId() == cityId) return i;
        }
        return 0;
    }
}
