package com.example.buttercalc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnLongClick;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Cedric on 8/03/14.
 */
public class ButterCalcFragment extends Fragment implements Observer {
    Calculator calculator = new Calculator();
    @InjectView(R.id.edtResult)  EditText result;
    @InjectView(R.id.btn0) Button btn0;
    @InjectView(R.id.btn1) Button btn1;
    @InjectView(R.id.btn2) Button btn2;
    @InjectView(R.id.btn3) Button btn3;
    @InjectView(R.id.btn4) Button btn4;
    @InjectView(R.id.btn5) Button btn5;
    @InjectView(R.id.btn6) Button btn6;
    @InjectView(R.id.btn7) Button btn7;
    @InjectView(R.id.btn8) Button btn8;
    @InjectView(R.id.btn9) Button btn9;
    @InjectView(R.id.btnDot) Button btnDot;
    @InjectView(R.id.btnAdd) Button btnAdd;
    @InjectView(R.id.btnSub) Button btnSub;
    @InjectView(R.id.btnMul) Button btnBul;
    @InjectView(R.id.btnDiv) Button btnDiv;
    @InjectView(R.id.btnC) Button btnC;

    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot})
    public void numberClick(Button button){
        calculator.setInput(button.getText().toString());
    }

    @OnClick({R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv})
    public void operatorClick(View view){
        switch (view.getId()){
            case R.id.btnAdd : calculator.setOperator("+"); break;
            case R.id.btnSub : calculator.setOperator("-"); break;
            case R.id.btnMul : calculator.setOperator("*"); break;
            case R.id.btnDiv : calculator.setOperator("/"); break;
        }
    }

    @OnClick(R.id.btnEq)
    public void equalClick(){
        calculator.getResult();
    }

    @OnClick(R.id.btnC)
    public void cClick(){
        calculator.undo();
    }

    @OnLongClick(R.id.btnC)
    public boolean cLongClick(){
        calculator.reset();
        Toast.makeText(getActivity().getApplicationContext(),R.string.reset,Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void update(Observable observable, Object o) {
        result.setText((String) o);
    }

    public ButterCalcFragment() {
        calculator.addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc,container,false);
        ButterKnife.inject(this,view);
        return view;
    }
}
