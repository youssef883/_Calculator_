package com.kmy.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    TextView solution_tv ,result_tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        solution_tv = findViewById(R.id.solution_tv);
        result_tv = findViewById(R.id.result_tv);
        assignId(R.id.btn_clear);
        assignId(R.id.btn_clear_all);
        assignId(R.id.btn_open_brackets);
        assignId(R.id.btn_close_brackets);
        assignId(R.id.btn_dot);
        assignId(R.id.btn_division);
        assignId(R.id.btn_multiple);
        assignId(R.id.btn_addition);
        assignId(R.id.btn_subtraction);
        assignId(R.id.btn_equals);
        assignId(R.id.btn_0);
        assignId(R.id.btn_1);
        assignId(R.id.btn_2);
        assignId(R.id.btn_3);
        assignId(R.id.btn_4);
        assignId(R.id.btn_5);
        assignId(R.id.btn_6);
        assignId(R.id.btn_7);
        assignId(R.id.btn_8);
        assignId(R.id.btn_9);
    }

    public void assignId(int id)
    {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button  = (MaterialButton) view;

        String btn_name = button.getText().toString();

        String equation = solution_tv.getText().toString();

        if (btn_name.equals("AC"))
        {
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if (btn_name.equals("="))
        {
            solution_tv.setText(result_tv.getText());
            return;
        }
        if (btn_name.equals("C"))
        {
            if ( equation.length() > 0){
                equation = equation.substring(0,equation.length()-1);
            }
            else{
                solution_tv.setText("");
                result_tv.setText("0");
                return;
            }
        }
        else
        {
            equation = equation+ btn_name ;
        }
        solution_tv.setText(equation);

        String result = getResult(equation);

        if (! result.equals("error"))
        {
            result_tv.setText(result);
        }

    }

    public String getResult(String data)
    {
        try {

            Context context = Context.enter();

            context.setOptimizationLevel(-1);

            Scriptable scriptable = context.initStandardObjects();

            String result =context.evaluateString(scriptable ,data ,"Javascript" ,1,null).toString();

            if (result.endsWith(".0"))
            {
                result =result.replace(".0","");
            }

            return result;

        }catch (Exception e)
        {
            return "error";
        }
    }

}