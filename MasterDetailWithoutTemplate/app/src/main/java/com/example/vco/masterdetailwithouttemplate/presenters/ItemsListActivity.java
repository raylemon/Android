package com.example.vco.masterdetailwithouttemplate.presenters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import com.example.vco.masterdetailwithouttemplate.R;
import com.example.vco.masterdetailwithouttemplate.model.Item;

/**
 * ce controller agira à la fois pour le handset et la tablette
 * ne pas tenir compte de l'impléments avant l'aca 21
 */
public class ItemsListActivity extends Activity implements OnListItemSelectedListener {

    //ACAPATH 23 : gestion handset ET tablette
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        //ACAPATH 24 : choix du layout
        determineLayout();
    }

    private void determineLayout() {
        //On cherche si le frameLayout existe dans cette activité
        if (ButterKnife.findById(this, R.id.flDetailContainer) != null) {
            isTwoPane = true;
            //ACAPATH 27
            ItemListFragment listFragment = (ItemListFragment) getFragmentManager().findFragmentById(R.id.fragmentItemList);
            listFragment.setActivateOnItemClick(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* ACAPATH 21
        création du callback. doit passer l'item au fragment de détail. Seule une activité peut le faire
     */
    @Override
    public void onItemSelected(Item item) {
        //ACAPATH 25 : adapatation du code
        if (isTwoPane) {
            //copier/coller du code d'affichage du fragment de la seconde activité
            ItemDetailFragment fragment = ItemDetailFragment.newInstance(item);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragment); //affichage du fragment dans le container

            ft.setCustomAnimations(//animation
                    R.animator.card_flip_right_in, //affichage du nouveau fragment
                    R.animator.card_flip_right_out, //dispartion de l'ancien
                    R.animator.card_flip_left_in, //affichaqe du fragment précédent si retour
                    R.animator.card_flip_left_out); //dispartion de l'ancien si retour
            ft.addToBackStack(null); //ajoute la transaction au backstack pour les retours
            ft.commit(); //application
        } else {
            //Intention pour les handset ACA 21
            Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class); //intention explicite
            intent.putExtra("item", item); //on passe l'item en extra
            startActivity(intent);//on démarre l'activité
            //À ce stade, l'application fonctionne en handset
        }

    }
}
