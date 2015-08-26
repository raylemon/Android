package be.ifosup.vco.daosample.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import be.ifosup.vco.daosample.app.engine.City;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cedric on 21-05-15.
 */
public class CityDialogFragment extends DialogFragment {

    @InjectView(R.id.etDialogCity)
    EditText etCity;
    @InjectView(R.id.etDialogZipCode)
    EditText etZIpCode;
    private OnFinishDialog mlistener;

    public CityDialogFragment(){}

    public interface OnFinishDialog {
        void getDialogResult(City city);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.city_dialog_fragment,null);
        ButterKnife.inject(this, view);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Add City", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        City city = City.newInstance(etCity.getText().toString(), etZIpCode.getText().toString());
                        mlistener.getDialogResult(city);
                        getDialog().dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mlistener.getDialogResult(null);
                        getDialog().dismiss();
                    }
                })
                .create();
        return dialog;
    }

//    @OnClick(R.id.btDialogCancel)
//    public void doCancelClick(){
//        mlistener.getDialogResult(null);
//        this.dismiss();
//    }
//
//    @OnClick(R.id.btDialogAdd)
//    public void doAddClick(){
//        City city = City.newInstance(etCity.getText().toString(),etZIpCode.getText().toString());
//        mlistener.getDialogResult(city);
//        this.dismiss();
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mlistener =  (OnFinishDialog)activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException( activity.getLocalClassName() + "must implements OnFinishDialog");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mlistener = null;
    }
}
