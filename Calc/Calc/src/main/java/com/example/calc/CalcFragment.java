package com.example.calc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Cedric on 8/03/14.
 */
public class CalcFragment extends Fragment implements Observer {
    private EditText result = null;
    private Calculator calculator;
    private View.OnClickListener numberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            calculator.setInput(button.getText().toString());
        }
    };
    private View.OnClickListener operatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnAdd : calculator.setOperator("+"); break;
                case R.id.btnSub : calculator.setOperator("-"); break;
                case R.id.btnMul : calculator.setOperator("*"); break;
                case R.id.btnDiv : calculator.setOperator("/"); break;
            }
        }
    };

    public CalcFragment() {
        calculator = new Calculator();
        calculator.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        result.setText((String)o);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc,container,false);  //inflate la vue
        /*charge les widgets*/
        result = (EditText)view.findViewById(R.id.edtResult);
        final Button btn0 = (Button)view.findViewById(R.id.btn0);
        final Button btn1 = (Button)view.findViewById(R.id.btn1);
        final Button btn2 = (Button)view.findViewById(R.id.btn2);
        final Button btn3 = (Button)view.findViewById(R.id.btn3);
        final Button btn4 = (Button)view.findViewById(R.id.btn4);
        final Button btn5 = (Button)view.findViewById(R.id.btn5);
        final Button btn6 = (Button)view.findViewById(R.id.btn6);
        final Button btn7 = (Button)view.findViewById(R.id.btn7);
        final Button btn8 = (Button)view.findViewById(R.id.btn8);
        final Button btn9 = (Button)view.findViewById(R.id.btn9);
        final Button btnDot = (Button)view.findViewById(R.id.btnDot);
        final Button btnC = (Button)view.findViewById(R.id.btnC);
        final Button btnAdd = (Button)view.findViewById(R.id.btnAdd);
        final Button btnSub = (Button)view.findViewById(R.id.btnSub);
        final Button btnMul = (Button)view.findViewById(R.id.btnMul);
        final Button btnDiv = (Button)view.findViewById(R.id.btnDiv);
        final Button btnEq = (Button)view.findViewById(R.id.btnEq);
        /*attache les écouteurs nombre*/
        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);
        btnDot.setOnClickListener(numberClickListener);
        /*attache les écouteurs opérateurs*/
        btnAdd.setOnClickListener(operatorClickListener);
        btnSub.setOnClickListener(operatorClickListener);
        btnMul.setOnClickListener(operatorClickListener);
        btnDiv.setOnClickListener(operatorClickListener);
        /*écouteur anonyme btnEq*/
        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               calculator.getResult();
            }
        });
        /*écouteurs anonymes btnC*/
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.undo();
            }
        });
        btnC.setLongClickable(true);
        btnC.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                calculator.reset();
                Toast.makeText(getActivity().getApplicationContext(),R.string.reset,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return view;
    }
}
