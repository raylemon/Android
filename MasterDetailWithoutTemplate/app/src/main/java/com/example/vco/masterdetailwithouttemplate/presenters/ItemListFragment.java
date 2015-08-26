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
 * ACAPATH�2
 * cr�ation du fragment contenant une liste
 * Pas de changement de code au d�part
 */
public class ItemListFragment extends Fragment {
    /* ACAPATH�8
  * Population de la liste
  * */
    @InjectView(R.id.lvItems)
    ListView mLvItems;
    private EvenOddAdapter adapter;

    //ACAPATH�17 : cr�ation d'une interface d'�coute
    private OnListItemSelectedListener listener;

    public ItemListFragment() {
        // Required empty public constructor
    }

    /* ACAPATH�9
    * Gonflement de la vue et injection
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.inject(this, view);
        mLvItems.setAdapter(adapter); //attachement de l'adapter
        //ACAPATH�20 : attachement de l'�couteur � la liste
        mLvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position); //on r�cup�re l'item cliqu�
                listener.onItemSelected(item); //on envoie le r�sultat � l'activit� qui doit g�rer l'affichage de l'item.
            }
        });
        return view;
    }

    /* ACAPATH�10
    * override et cr�ation de la liste
    * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Item> items = Item.getItems(); //r�cup�ration de la liste.
        adapter = new EvenOddAdapter(getActivity().getApplicationContext(), items); //cr�ation de l'adaptateur
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /* ACAPATH�19
        Attachement des �couteurs
        Un fragment est une portion d'activit�, il ne peut fonctionner seul. L'utilisateur va utiliser
        l'activit� charg�e pour interagir sur les fragments.
        C'est pour cela qu'il faut que l'on cr�e un CALLBACK qui lie l'action sur l'activit� avec les
        donn�es du fragment.
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
        listener = null; //on d�tache l'�couteur pour �viter les probl�mes
    }

    //ACAPATH 26 : affichage d'un �tat "s�lectionn�" pour tablettes
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        mLvItems.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
    }
}
