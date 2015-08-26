package com.example.vco.masterdetailwithouttemplate.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vco.masterdetailwithouttemplate.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ACAPATH 4
 * Création d'un adaptateur
 */
public class EvenOddAdapter extends ArrayAdapter<Item> {
    private Context context;

    public EvenOddAdapter(Context context, List<Item> objects) {
        super(context, 0, objects); //0 car on doit utiliser plusieurs layouts
        this.context = context; //utile pour gonfler les layouts
    }

    /*
     * Méthodes à surcharger
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int res = 0;    //layout à afficher, déterminé par le viewType
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                res = R.layout.even_item;
                break;
            case 1:
                res = R.layout.odd_item;
                break;
        }
        ItemViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ItemViewHolder) convertView.getTag(); //récupération de la vue en mémoire
            //coute moins cher en ressources que la recréation de la vue à chaque affichage
        } else {
            convertView = LayoutInflater.from(context).inflate(res, parent, false); //gonflement de la vue
            viewHolder = new ItemViewHolder(convertView, this.getItem(position));
            convertView.setTag(viewHolder);
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        //return super.getViewTypeCount();
        return 2; // renvoie le nombre total de vue. Ici bloqué à deux volontairement
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return position % 2; //renvoie une valeur entre 0 et getViewTypeCount -1 pour afficher le layout correspondant
    }

    /**
     * ACAPATH 5
     * Création du ViewHolder
     */
    public static class ItemViewHolder {
        @InjectView(R.id.tvDropCap)
        TextView tvDropCap;
        @InjectView(R.id.tvTitle)
        TextView tvTitle;
        @InjectView(R.id.tvBody)
        TextView tvBody;

        public ItemViewHolder(View view, Item item) {
            ButterKnife.inject(this, view); //l'injection doit se faire le plus tôt possible
            this.tvDropCap.setText(item.getDropCap());
            this.tvTitle.setText(item.getTitle());
            this.tvTitle.setText(item.getBody());
        }
    }
}

