package be.ifosup.vco.daosample.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import be.ifosup.vco.daosample.app.engine.Client;
import be.ifosup.vco.daosample.app.engine.SqlFactory;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.edtSearch)
    EditText edtSearch;
    @InjectView(R.id.listViewClients)
    ListView listViewClients;
    @InjectView(R.id.empty)
    TextView empty;

    private ArrayAdapter<Client> clientAdapter;
    private SqlFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        factory = new SqlFactory(getApplicationContext());
        clientAdapter = new ArrayAdapter<Client>(getApplicationContext(),android.R.layout.simple_list_item_2, factory.getClientDAO().getClients());

        listViewClients.setEmptyView(empty);
        listViewClients.setAdapter(clientAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_AddClient) {
            Intent intent = new Intent(getApplicationContext(),AddClientActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clientAdapter.clear();
        clientAdapter.addAll(factory.getClientDAO().getClients());
    }

    @OnTextChanged(R.id.edtSearch)
    public void textChanged() {
        clientAdapter.getFilter().filter(edtSearch.getText());
    }

    @OnItemSelected(R.id.listViewClients)
    public void selClient(int position) {
        Client client = clientAdapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(),AddClientActivity.class);
        intent.putExtra(AddClientActivity.CLIENT, client);
        startActivity(intent);
    }

}
