package com.example.vco.masterdetailwithouttemplate.presenters;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.example.vco.masterdetailwithouttemplate.R;
import com.example.vco.masterdetailwithouttemplate.model.Item;


/**
 * ACAPATH�6
 * Cr�ation du fragment d�tail
 */
public class ItemDetailFragment extends Fragment {

    @InjectView(R.id.etDetTitle)
    EditText mEtDetTitle;
    @InjectView(R.id.etDetBody)
    EditText mEtDetBody;
    @InjectView(R.id.detRatingBar)
    RatingBar mDetRatingBar;

    private Item item; //pour aca 14

    public ItemDetailFragment() {
        // Required empty public constructor
        /* ACAPATH�13
         On n'utilise pratiquement pas les constructeurs param�tr�s pour cr�er des fragments.
         �a consomme trop de ressources.
         � la place, pour cr�er un nouveau fragment, on utilisera le pattern newInstance,
         avec une simulation de passage d'une intention.
         */
    }

    public static ItemDetailFragment newInstance(Item item) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        fragment.setArguments(args); //on attache l'argument
        return fragment;
    }

    /* ACAPATH�14
        Lors de la cr�ation du fragment, on r�cup�re le Bundle et l'argument � l'int�rieur
        La m�thode onCreate ne s'appelle qu'une seule fois lors de la cr�ation du fragment et de son affichage.
        le constructeur s'occupe de la cr�ation de l'objet en m�moire. Le pattern newInstance permet de
        cr�er un fragment et d'associer un item. Lorsqu'il sera appel� pour affichage, la m�thode onCreate sera appel�e.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //le Bundle sera ici
        item = getArguments().getParcelable("item");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        ButterKnife.inject(this, view);
        //ACAPATH 14 suite
        this.mEtDetTitle.setText(item.getTitle());
        this.mEtDetBody.setText(item.getBody());
        this.mDetRatingBar.setRating(item.getRating());
        return view;
    }

    @OnClick(R.id.btnDelete)
    public void doDeleteClick() {
        //TODO
    }

    @OnClick(R.id.btnUpdate)
    public void doUpdateClick() {
        //TODO
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
