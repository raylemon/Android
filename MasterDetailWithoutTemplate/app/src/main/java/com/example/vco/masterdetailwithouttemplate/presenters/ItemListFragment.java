package com.example.vco.masterdetailwithouttemplate.presenters;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vco.masterdetailwithouttemplate.R;
import com.example.vco.masterdetailwithouttemplate.model.EvenOddAdapter;
import com.example.vco.masterdetailwithouttemplate.model.Item;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ACAPATH 2
 * création du fragment contenant une liste
 * Pas de changement de code au départ
 */
public class ItemListFragment extends Fragment {
    /* ACAPATH 8
  * Population de la liste
  * */
    @InjectView(R.id.lvItems)
    ListView mLvItems;
    private EvenOddAdapter adapter;

    //ACAPATH 17 : création d'une interface d'écoute
    private OnListItemSelectedListener listener;

    public ItemListFragment() {
        // Required empty public constructor
    }

    /* ACAPATH 9
    * Gonflement de la vue et injection
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.inject(this, view);
        mLvItems.setAdapter(adapter); //attachement de l'adapter
        //ACAPATH 20 : attachement de l'écouteur à la liste
        mLvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position); //on récupère l'item cliqué
                listener.onItemSelected(item); //on envoie le résultat à l'activité qui doit gérer l'affichage de l'item.
            }
        });
        return view;
    }

    /* ACAPATH 10
    * override et création de la liste
    * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Item> items = Item.getItems(); //récupération de la liste.
        adapter = new EvenOddAdapter(getActivity().getApplicationContext(), items); //création de l'adaptateur
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /* ACAPATH 19
        Attachement des écouteurs
        Un fragment est une portion d'activité, il ne peut fonctionner seul. L'utilisateur va utiliser
        l'activité chargée pour interagir sur les fragments.
        C'est pour cela qu'il faut que l'on crée un CALLBACK qui lie l'action sur l'activité avec les
        données du fragment.
     */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnListItemSelectedListener) {
            listener = (OnListItemSelectedListener) activity;
        } else throw new ClassCastException(activity.toString() +
                "must be implement OnItemListSelectedListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null; //on détache l'écouteur pour éviter les problèmes
    }

    //ACAPATH 26 : affichage d'un état "sélectionné" pour tablettes
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        mLvItems.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
    }
}
