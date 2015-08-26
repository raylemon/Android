package be.ifosup.vco.daosample.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import be.ifosup.vco.daosample.app.engine.City;
import be.ifosup.vco.daosample.app.engine.Client;
import be.ifosup.vco.daosample.app.engine.SqlFactory;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class AddClientActivity extends ActionBarActivity implements CityDialogFragment.OnFinishDialog {

    public static final String CLIENT = "CLIENT";
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
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
    private SqlFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        ButterKnife.inject(this);
        factory = new SqlFactory(getApplicationContext());
        factory.getCityDAO().getCities();

        cityAdapter = new ArrayAdapter<City>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, factory.getCityDAO().getCities());
        spinnerClientCity.setAdapter(cityAdapter);

        if (getIntent().hasExtra(CLIENT)) {
            client = getIntent().getParcelableExtra(CLIENT);
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
    public void doSave(){
        client.setFirstName(etClientFirstName.getText().toString());
        client.setLastName(etClientLastName.getText().toString());
        client.setCity((City) spinnerClientCity.getSelectedItem());
        if (isUpdate) factory.getClientDAO().updateClient(client);
        else factory.getClientDAO().AddClient(client);
        doGoBack();
    }

    @OnClick(R.id.btnClientBack)
    public void doGoBack() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnClientDelete)
    public void doDelete(){
        factory.getClientDAO().deleteClient(client);
        doGoBack();
    }

    @Override
    public void getDialogResult(City city) {
        if (city != null) {
            long cityId = -1; //TODO
            if (!factory.getCityDAO().contains(city)) {
                cityId = factory.getCityDAO().addCity(city);
            }
            cityAdapter.add(city);
            spinnerClientCity.setSelection(getSelByValue(cityId));
        }
    }

    private int getSelByValue(long cityId) {
        for (int i = 0; i < cityAdapter.getCount(); i++) {
            if(cityAdapter.getItem(i).getId() == cityId) return i;
        }
        return 0;
    }
}
