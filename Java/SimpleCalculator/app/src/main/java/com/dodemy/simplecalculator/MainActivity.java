package com.dodemy.simplecalculator;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation
    Button btnSin, btnCos, btnTan, btnFactorial, btnPow;
    Button btnClear, btnBackSpace, btnBracketsOpen, btnBracketsClose, btnSquare;
    Button btnSeven, btnNine, btnEight, btnDiv, btnMod;
    Button btnFour, btnFive, btnSix, btnMulti, btnInverse;
    Button btnOne, btnTwo, btnThree, btnMinus, btnAdd, btnResult;
    Button btnZero, btnDot, btnPi;

    TextView screenAns, screenMath;

    StringBuilder textMath = new StringBuilder("");
    StringBuilder textAns = new StringBuilder("0");
    StringBuilder screenTextMath = new StringBuilder("");
    double num1 = 0, num2 = 0, ans = 0;
    //char mask = ' ';
    int checkSubmit = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set font
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "lato-regular.ttf", true);

        //Control
        screenAns = (TextView) findViewById(R.id.txtResult);
        screenMath = (TextView) findViewById(R.id.txtCal);

        btnZero = (Button) findViewById(R.id.btnZero);
        btnZero.setOnClickListener(this);

        btnOne = (Button) findViewById(R.id.btnOne);
        btnOne.setOnClickListener(this);

        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(this);

        btnThree = (Button) findViewById(R.id.btnThree);
        btnThree.setOnClickListener(this);

        btnFour = (Button) findViewById(R.id.btnFour);
        btnFour.setOnClickListener(this);

        btnFive = (Button) findViewById(R.id.btnFive);
        btnFive.setOnClickListener(this);


        btnSix = (Button) findViewById(R.id.btnSix);
        btnSix.setOnClickListener(this);

        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(this);

        btnEight = (Button) findViewById(R.id.btnEight);
        btnEight.setOnClickListener(this);

        btnNine = (Button) findViewById(R.id.btnNine);
        btnNine.setOnClickListener(this);

        btnSin = (Button) findViewById(R.id.btnSin);
        btnSin.setOnClickListener(this);

        btnCos = (Button) findViewById(R.id.btnCos);
        btnCos.setOnClickListener(this);


        btnTan = (Button) findViewById(R.id.btnTan);
        btnTan.setOnClickListener(this);

        btnFactorial = (Button) findViewById(R.id.btnFactorial);
        btnFactorial.setOnClickListener(this);

        btnPow = (Button) findViewById(R.id.btnPow);
        btnPow.setOnClickListener(this);


        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);


        btnBackSpace = (Button) findViewById(R.id.btnBack);
        btnBackSpace.setOnClickListener(this);

        btnBracketsClose = (Button) findViewById(R.id.btnBrackketsClose);
        btnBracketsClose.setOnClickListener(this);

        btnBracketsOpen = (Button) findViewById(R.id.btnBracketsOpen);
        btnBracketsOpen.setOnClickListener(this);

        btnSquare = (Button) findViewById(R.id.btnSquared);
        btnSquare.setOnClickListener(this);

        btnDot = (Button) findViewById(R.id.btnDot);
        btnDot.setOnClickListener(this);

        btnPi = (Button) findViewById(R.id.btnPi);
        btnPi.setOnClickListener(this);


        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(this);


        btnMod = (Button) findViewById(R.id.btnMod);
        btnMod.setOnClickListener(this);


        btnMulti = (Button) findViewById(R.id.btnMulti);
        btnMulti.setOnClickListener(this);


        btnInverse = (Button) findViewById(R.id.btnInverse);
        btnInverse.setOnClickListener(this);

        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);


        btnResult = (Button) findViewById(R.id.btnResult);
        btnResult.setOnClickListener(this);


    }


    public void error() {
        screenAns.setText("Math Error !");
        textAns = textMath = screenTextMath = new StringBuilder("");
    }

    public void submit(String[] elementMath) {
        InfixToPostfix ITP = new InfixToPostfix();
        if (textMath.length() > 0) {
            try {
                if (!ITP.check_error)
                    elementMath = ITP.processString(textMath.toString());        //	split expression to element
                if (!ITP.check_error)
                    elementMath = ITP.postfix(elementMath);        // 	format elements to postfix
                if (!ITP.check_error)
                    textAns = new StringBuilder(ITP.valueMath(elementMath));        // get result
                screenAns.setText(textAns);

                screenTextMath = new StringBuilder();
                textMath = new StringBuilder();
                checkSubmit = 1;

            } catch (Exception e) {
                error();
            }
            if (ITP.check_error) error();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String elementMath[] = null;


        if (id == R.id.btnZero) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("0");
                screenTextMath.append("0");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnOne) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("1");
                screenTextMath.append("1");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnTwo) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("2");
                screenTextMath.append("2");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnThree) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("3");
                screenTextMath.append("3");
            }

            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnFour) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("4");
                screenTextMath.append("4");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnFive) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("5");
                screenTextMath.append("5");
            }
            screenMath.setText(screenTextMath.toString());
        } else if (id == R.id.btnSix) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("6");
                screenTextMath.append("6");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnSeven) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("7");
                screenTextMath.append("7");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnEight) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("8");
                screenTextMath.append("8");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnNine) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("9");
                screenTextMath.append("9");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnDot) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append(".");
                screenTextMath.append(".");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnPi) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("π");
                screenTextMath.append("π");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnAdd) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("+");
                screenTextMath.append("+");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnMinus) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("-");
                screenTextMath.append("-");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnMulti) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("*");
                screenTextMath.append("*");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnDiv) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("/");
                screenTextMath.append("/");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnPow) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("^(");
                screenTextMath.append("^(");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnSquared) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("@");
                screenTextMath.append("√");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnSin) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("s(");
                screenTextMath.append("Sin(");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnCos) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("c(");
                screenTextMath.append("Cos(");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnTan) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("t(");
                screenTextMath.append("Tan(");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnBracketsOpen) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append("(");
                screenTextMath.append("(");
            }
            screenMath.setText(screenTextMath);
        } else if (id == R.id.btnBrackketsClose) {
            if (screenTextMath.length() < 48) {    //if length < 48
                if (checkSubmit == 1) {
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit = 0;
                }
                textMath.append(")");
                screenTextMath.append(")");
            }
            screenMath.setText(screenTextMath);
        }

        //==========================================================
        else if (id == R.id.btnMod) {
            if (screenTextMath.length() == 0) screenTextMath = new StringBuilder("0");
            screenTextMath = new StringBuilder("(" + screenTextMath + ")%");
            screenMath.setText(screenTextMath.toString());
            if (checkSubmit == 0) submit(null);
            textMath = new StringBuilder(textAns + "/100");
            submit(null);
        } else if (id == R.id.btnInverse) {
            if (screenTextMath.length() == 0) screenTextMath = new StringBuilder("0");
            screenTextMath = new StringBuilder("1/(" + screenTextMath + ")");
            screenMath.setText(screenTextMath);
            if (checkSubmit == 0) submit(null);
            textMath = new StringBuilder("1/" + textAns);
            submit(null);
        } else if (id == R.id.btnResult) {
            submit(null);
        } else if (id == R.id.btnClear) {
            textMath = new StringBuilder("");
            screenTextMath = new StringBuilder("");
            textAns = new StringBuilder("0");
            screenAns.setText(textAns);
            screenMath.setText("|");
        } else if (id == R.id.btnBack) {
            if (screenMath.length() > 0) {
                char c = textMath.charAt(textMath.length() - 1);
                if (textMath.length() > 1 && c == '(' && textMath.charAt(textMath.length() - 2) == '^') {
                    screenTextMath = new StringBuilder(screenTextMath.substring(0, screenTextMath.length() - 2));
                    textMath = new StringBuilder(textMath.substring(0, textMath.length() - 2));
                } else if (textMath.length() > 1 && c == '(' && (textMath.charAt(textMath.length() - 2) == 's' || textMath.charAt(textMath.length() - 2) == 'c' || textMath.charAt(textMath.length() - 2) == 't')) {
                    textMath = new StringBuilder(textMath.substring(0, textMath.length() - 2));
                    screenTextMath = new StringBuilder(screenTextMath.substring(0, screenTextMath.length() - 4));
                } else {
                    textMath = new StringBuilder(textMath.substring(0, textMath.length() - 1));
                    screenTextMath = new StringBuilder(screenTextMath.substring(0, screenTextMath.length() - 1));
                }
            }
            screenMath.setText(screenTextMath);
        }

    }
}
