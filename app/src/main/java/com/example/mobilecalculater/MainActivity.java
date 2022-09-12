package com.example.mobilecalculater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int n;
    String total;
    String trig;
    char op;
    char rad=' ';
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        EditText result = findViewById(R.id.edit_txt);
        TextView print = findViewById(R.id.edit_txt2);
        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        Button btn5 = findViewById(R.id.btn_5);
        Button btn6 = findViewById(R.id.btn_6);
        Button btn7 = findViewById(R.id.btn_7);
        Button btn8 = findViewById(R.id.btn_8);
        Button btn9 = findViewById(R.id.btn_9);
        Button btn0 = findViewById(R.id.btn_0);
        Button btn_plus = findViewById(R.id.btn_plus);
        Button btn_sub = findViewById(R.id.btn_sub);
        Button btn_mul = findViewById(R.id.btn_mul);
        Button btn_div = findViewById(R.id.btn_div);
        Button btn_equal=findViewById(R.id.btn_equal);
        Button btn_point=findViewById(R.id.btn_point);
        Button btn_ac = findViewById(R.id.btn_Ac);
        Button btn_leftbraces = findViewById(R.id.btn_leftbraces);
        Button btn_rightbraces = findViewById(R.id.btn_rightbraces);
        Button btn_pow = findViewById(R.id.btn_pow);
        Button btn_mod = findViewById(R.id.btn_mod);
        Button btn_sqrt = findViewById(R.id.btn_sqrt);
        Button btn_phi = findViewById(R.id.btn_phi);
        Button btn_euler = findViewById(R.id.btn_euler);
        Button btn_fac = findViewById(R.id.btn_fact);
        Button btn_cos = findViewById(R.id.btn_cos);
        Button btn_sin = findViewById(R.id.btn_sin);
        Button btn_tan = findViewById(R.id.btn_tan);
        Button btn_clr = findViewById(R.id.btn_clear);
        Button btn_rad=findViewById(R.id.btn_rad);

             {
                 btn1.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         result.setText(result.getText().toString()+btn1.getText().toString());
                     }
                 });
             }
        {
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn2.getText().toString());
                }
            });
        }
        {
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn3.getText().toString());
                }
            });
        }
        {
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn4.getText().toString());
                }
            });
        }
        {
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn5.getText().toString());
                }
            });
        }
        {
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn6.getText().toString());
                }
            });
        }
        {
            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn7.getText().toString());
                }
            });
        }
        {
            btn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn8.getText().toString());
                }
            });
        }
        {
            btn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn9.getText().toString());
                }
            });
        }
        {
            btn0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(result.getText().toString()+btn0.getText().toString());
                }
            });
        }
        {
            btn_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //op = '+';
                    //result.setText(result.getText().toString()+op);
                    //total= result.getText().toString();
                    result.setText(result.getText().toString()+btn_plus.getText().toString());
                    //result.setText(String.valueOf(result.getText().toString())+op);
                }
            });
        }
        {
            btn_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //op = '-';
                    //result.setText(result.getText().toString()+op);
                    //total= result.getText().toString();
                    result.setText(result.getText().toString()+btn_sub.getText().toString());
                   // result.setText(String.valueOf(result.getText().toString())+op);
                }
            });
        }
        {
            btn_mul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // op = '*';
                   // result.setText(result.getText().toString()+op);
                    //total= result.getText().toString();
                    result.setText(result.getText().toString()+btn_mul.getText().toString());
                    //result.setText(String.valueOf(result.getText().toString())+op);
                }
            });
        }
       {
            btn_point.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText(String.valueOf(result.getText().toString())+btn_point.getText().toString());
                }
            });
        }
       {
            btn_div.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //op = '/';
                    //total= result.getText().toString();
                    result.setText(result.getText().toString()+btn_div.getText().toString());
                    //result.setText(String.valueOf(result.getText().toString())+op);
                }
            });
        }
        btn_leftbraces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(result.getText().toString()+btn_leftbraces.getText().toString());
            }
        });
        btn_rightbraces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(result.getText().toString()+btn_rightbraces.getText().toString());
            }
        });
        btn_pow.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (!result.getText().toString().equals("")) {
                    result.setText(result.getText().toString() + btn_pow.getText().toString());
                }
                //result.setText(result.getText().toString()+btn_pow.getText().toString());
            }
        });
        btn_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(result.getText().toString()+btn_mod.getText().toString());
            }
        });
        btn_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           op = '√';
           print.setText(btn_sqrt.getText().toString());
           result.setText("");
                //result.setText(result.getText().toString()+btn_sqrt.getText().toString());
            }
        });
        btn_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op='π';
                result.setText("3.1415926536");
                //result.setText(result.getText().toString()+btn_sqrt.getText().toString());
            }
        });
        btn_euler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op='e';
                result.setText("2.7182818285");
                //result.setText(result.getText().toString()+btn_sqrt.getText().toString());
            }
        });
        btn_fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!result.getText().toString().equals("")) {
                    op = '!';
                    total = result.getText().toString();
                    result.setText(result.getText().toString() + btn_fac.getText().toString());
                }
            }

        });
btn_rad.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(rad==' '){
        rad='R';
        print.setText("R: ");}
        else if(rad!=' '){
            rad=' ';
        print.setText("");}
    }
});
        btn_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trig="Cos";
                print.setText(print.getText().toString()+btn_cos.getText().toString());
                result.setText(null);
            }
        });
        btn_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trig="Sin";
                print.setText(print.getText().toString()+btn_sin.getText().toString());
                result.setText(null);
            }
        });
        btn_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trig="tan";
                print.setText(print.getText().toString()+btn_tan.getText().toString());
                result.setText(null);
            }
        });
        {
            btn_equal.setOnClickListener(new View.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.R)
                public void onClick(View v) {
                    if (!result.getText().toString().equals("")) {
                        String temp;
                        if (op == '√') {

                            temp = result.getText().toString();
                            temp = Math.sqrt(Double.parseDouble(temp)) + "";
                            result.setText(temp);
                            print.setText("");
                        } else if (op == 'π') {
                            result.setText("3.1415926536");
                        } else if (op == 'e') {
                            result.setText("2.7182818285");
                        } else if (op == '!') {
                            double fac = Double.parseDouble(total);
                            double f = 1;
                            for (int i = 1; i <= fac; i++) {
                                f = f * i;
                            }
                            result.setText(f + "");

                        } else if (trig == "Cos") {
                            print.setText("");
                            if (rad != 'R') {
                                temp = Math.toRadians(Double.parseDouble(result.getText().toString())) + "";
                                temp = Math.cos(Double.parseDouble(temp)) + "";
                                result.setText("cos(" + result.getText().toString() + ") = " + temp);
                            } else {
                                print.setText("R: ");
                                temp = Math.cos(Double.parseDouble(result.getText().toString())) + "";
                                result.setText("cos(" + result.getText().toString() + ") = " + temp);
                            }
                        }
                        else if (trig == "Sin") {
                            print.setText("");
                            if(rad!='R'){
                                temp=Math.toRadians(Double.parseDouble(result.getText().toString()))+"";
                                temp = Math.sin(Double.parseDouble(temp)) + "";
                                result.setText("sin(" + result.getText().toString() + ") = " + temp);}
                            else
                            {
                                print.setText("R: ");
                                temp = Math.sin(Double.parseDouble(result.getText().toString())) + "";
                                result.setText("sin(" + result.getText().toString() + ") = " + temp);}
                        }
                        else if (trig == "tan") {
                            print.setText("");
                            if(rad!='R'){
                            temp=Math.toRadians(Double.parseDouble(result.getText().toString()))+"";
                                temp = Math.tan(Double.parseDouble(temp)) + "";
                                result.setText("tan(" + result.getText().toString() + ") = " + temp);}
                            else
                            {
                                print.setText("R: ");
                                temp = Math.tan(Double.parseDouble(result.getText().toString())) + "";
                                result.setText("tan(" + result.getText().toString() + ") = " + temp);}



                        } else {
                            Environment env = new Environment();
                            Lexer lexer;
                            Parser parser;
                            Evaluator solver;

                            //System.out.print("> ");
                            String line = result.getText().toString();
                            if (line.isEmpty()) {
                                getCurrentFocus();
                            } else {
//                System.out.println(line);
                                lexer = new Lexer(line);
                                ArrayList<Token> tokens = lexer.scanTokens();

//                System.out.println(tokens);
                                parser = new Parser(tokens, env);
                                Expression expression = null;
                                try {
                                    expression = parser.parse();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
//                System.out.println(new AstPrinter().print(expression));
                                solver = new Evaluator(env);
                                result.setText(String.valueOf(solver.solve(expression)));


                            }
                        }

                    }
                }





            });
        }

            btn_ac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    result.setText("");
                    trig="";
                    op=' ';
                    total="";
                    print.setText("");
                    rad=' ';
                }


            });
        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = result.length();
                if(n==0)
                {
                    result.setText("");
                }
                else {

                    total = result.getText().replace(n - 1, n, "") + "";
                    result.setText(total);
                }
                }


        });
        }
        }
