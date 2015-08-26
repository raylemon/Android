package com.example.vco.masterdetailwithouttemplate.presenters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.vco.masterdetailwithouttemplate.R;
import com.example.vco.masterdetailwithouttemplate.model.Item;

/**
 * ACAPATH 11
 * Création de l'activité Détails (spécial Handset)
 * Ajout d'un Frame Layout dans le XML.
 */
public class ItemDetailActivity extends Activity {

    ItemDetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        //ACAPATH 15 Appel de l'item Parcelisé
        Item item = getIntent().getParcelableExtra("item"); //on récupère l'item passé dans l'intention
        if (savedInstanceState == null) {
            fragment = ItemDetailFragment.newInstance(item);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragment); //affichage du fragment dans le container
            //ACAPATH 16 : création des XML d'animation (voir dossier animator)
            ft.setCustomAnimations(//animation
                    R.animator.card_flip_right_in, //affichage du nouveau fragment
                    R.animator.card_flip_right_out, //dispartion de l'ancien
                    R.animator.card_flip_left_in, //affichaqe du fragment précédent si retour
                    R.animator.card_flip_left_out); //dispartion de l'ancien si retour
            ft.addToBackStack(null); //ajoute la transaction au backstack pour les retours
            ft.commit(); //application
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
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
}
