package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result;
    TextView expression;
    TextView B0, B1, B2, B3, B4, B5, B6, B7, B8, B9;
    TextView BAdd, BSub, BMulti, BDiv, BEqual;
    TextView BClear, BDot, BOpenclose, BPercent;
    ImageView BBack;
    Boolean checkBracket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = findViewById(R.id.Exp);
        result = findViewById(R.id.Res);
        B0 = findViewById(R.id.BuZero);
        B0.setOnClickListener(this);
        B1 = findViewById(R.id.BuOne);
        B1.setOnClickListener(this);
        B2 = findViewById(R.id.BuTwo);
        B2.setOnClickListener(this);
        B3 = findViewById(R.id.BuThree);
        B3.setOnClickListener(this);
        B4 = findViewById(R.id.BuFour);
        B4.setOnClickListener(this);
        B5 = findViewById(R.id.BuFive);
        B5.setOnClickListener(this);
        B6 = findViewById(R.id.BuSix);
        B6.setOnClickListener(this);
        B7 = findViewById(R.id.BuSeven);
        B7.setOnClickListener(this);
        B8 = findViewById(R.id.BuEight);
        B8.setOnClickListener(this);
        B9 = findViewById(R.id.BuNine);
        B9.setOnClickListener(this);
        BAdd = findViewById(R.id.BuAdd);
        BAdd.setOnClickListener(this);
        BSub = findViewById(R.id.BuSub);
        BSub.setOnClickListener(this);
        BMulti = findViewById(R.id.BuMulti);
        BMulti.setOnClickListener(this);
        BDiv = findViewById(R.id.BuDiv);
        BDiv.setOnClickListener(this);
        BEqual = findViewById(R.id.BuEqual);
        BEqual.setOnClickListener(this);
        BOpenclose = findViewById(R.id.BuOpenclose);
        BOpenclose.setOnClickListener(this);
        BClear = findViewById(R.id.BuClear);
        BClear.setOnClickListener(this);
        BPercent = findViewById(R.id.BuPercent);
        BPercent.setOnClickListener(this);
        BDot = findViewById(R.id.BuDot);
        BDot.setOnClickListener(this);
        BBack = findViewById(R.id.BuBack);
        BBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String exp = expression.getText().toString();

        if (R.id.BuEqual == id) {
            calculate();
        } else if (R.id.BuZero == id) {
            generateExpression("0");
        } else if (R.id.BuOne == id) {
            generateExpression("1");
        } else if (R.id.BuTwo == id) {
            generateExpression("2");
        } else if (R.id.BuThree == id) {
            generateExpression("3");
        } else if (R.id.BuFour == id) {
            generateExpression("4");
        } else if (R.id.BuFive == id) {
            generateExpression("5");
        } else if (R.id.BuSix == id) {
            generateExpression("6");
        } else if (R.id.BuSeven == id) {
            generateExpression("7");
        } else if (R.id.BuEight == id) {
            generateExpression("8");
        } else if (R.id.BuNine == id) {
            generateExpression("9");
        } else if (R.id.BuAdd == id) {
            generateExpression("+");
        } else if (R.id.BuSub == id) {
            generateExpression("-");
        } else if (R.id.BuMulti == id) {
            generateExpression("*");
        } else if (R.id.BuDiv == id) {
            generateExpression("/");
        } else if (R.id.BuClear == id) {
            expression.setText("");
            result.setText("");
        } else if (R.id.BuBack == id) {
            int expLength = exp.length();
            if (!(expLength < 1)) {
                if (expLength == 1) {
                    expression.setText("");
                } else {
                    expression.setText(exp.substring(0, expLength - 1));
                }
            }
        } else if (R.id.BuPercent == id) {
            if (exp.equals("") || isSpecialChar("" + exp.charAt(exp.length() - 1))) {
                return;
            }
            Expression expression1 = new Expression(exp + "/100");
            String result1 = String.valueOf(expression1.calculate());
            generateExpression("%");
            result.setText(result1);
        } else if (R.id.BuDot == id) {
            if (!Double.isNaN(new Expression(exp + ".0").calculate())) generateExpression(".");
        } else if (R.id.BuOpenclose==id)
        {
            if (checkBracket)
            {
                expression.setText(exp + ")");
                checkBracket = false;
            }
            else{
                expression.setText(exp + "(");
                checkBracket = true;
            }
        }
    }

    private void calculate()
    {
        String exp = expression.getText().toString();
        String result1;
        Expression expression = new Expression(exp);
        result1 = String.valueOf(expression.calculate());
        result.setText(result1);
        if(result1.endsWith(".0")) {
            result1 = result1.replace(".0", "");
        }
        result.setText(result1);
    }
    public void generateExpression(String val) {
        String exp = expression.getText().toString();
        if (isSpecialChar(val)) {
            if (exp.equals("") || isSpecialChar("" + exp.charAt(exp.length() - 1))) {
                return;
            }
        }
        expression.setText(String.format("%s%s", expression.getText().toString(), val));
    }

    public boolean isSpecialChar(String str) {
        return Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]").matcher(str).find();
    }
}

