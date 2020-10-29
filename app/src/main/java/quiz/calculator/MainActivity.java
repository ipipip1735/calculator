package quiz.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    EditText editTextOp;
    MutableLiveData<String> liveData;

    List<String> opt = new ArrayList<>();
    String input = "";
    String arg1 = "";
    String arg2 = "";
    boolean first = true;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);

        liveData = new MutableLiveData<>();
        liveData.setValue("");
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("s is " + s);

                if (s.matches("[+,\\-,*,/]")) {
                    opt.add(s);
                    editTextOp.setText(s);

                    if (arg1.isEmpty()) {
                        arg1 = input;
                        System.out.println("first|arg1 is " + arg1);
                        n++;
                    } else {
                        if (!input.isEmpty()) {
                            arg2 = input;
                            System.out.println("secend|arg2 is " + arg2);

                            editText.setText(input);
                            compute(arg1, arg2, opt.get(opt.size() - 2));
                        }
                    }
                    input = "";
                }

//                String reg2 = "^m.*";
//                if (s.matches(reg2)) {
//                    System.out.println("xxxxxxxxxx");
//
//                    input = s;
//
//                    opt = input;
//                    editTextOp.setText(opt);
//
//                }


                if (s.matches("\\d*")) {
                    System.out.println("dddddddddddd");
                    input += s;
                    editText.setText(input);

                }


                if (s.equals("=")) {
                    System.out.println("===========");

                    if(input.isEmpty() || opt.isEmpty()) return;

                    arg2 = input;
                    System.out.println("secend|arg2 is " + arg2);

                    compute(arg1, arg2, opt.get(opt.size() - 1));

                    editTextOp.setText("");
                    input = "";
                    n = 0;

                }


                if (s.equals("c")) {
                    System.out.println("ccccccc");
                    editText.setText("");
                    editTextOp.setText("");
                    input = arg1 = arg2 = "";
                    opt = new ArrayList<>();
                    n = 0;
                }

                System.out.println(arg1 + " " + opt + " " + arg2);

            }
        });


        ViewGroup parent = findViewById(R.id.cl);

        for (int i = 0; i < parent.getChildCount(); i++) {

            View view = parent.getChildAt(i);
            if (view instanceof Button) {
                AppCompatButton button = (AppCompatButton) view;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppCompatButton button = (AppCompatButton) v;
                        System.out.println(button.getText());
                        liveData.setValue(button.getText().toString());
                    }
                });


            }

        }


        editText = findViewById(R.id.et);
        editTextOp = findViewById(R.id.etop);


    }

    private void compute(String arg1, String arg2, String opt) {

        int a = Integer.valueOf(arg1);
        int b = Integer.valueOf(arg2);
        int r = 0;

        switch (opt) {
            case "+":
                System.out.println("++++++");
                System.out.println(a + " " + opt + " " + b);
                r = a + b;
                break;

            case "-":
                System.out.println("------");
                System.out.println(a + " " + opt + " " + b);
                r = a - b;
                break;
            case "*":
                System.out.println("*****");
                System.out.println(a + " " + opt + " " + b);
                r = a * b;
                break;
            case "/":
                System.out.println("///////");
                System.out.println(a + " " + opt + " " + b);
                r = a / b;
                break;
            default:
                System.out.println("default!");
        }


        this.arg1 = Integer.toString(r);
        this.arg2 = "";
        editText.setText(this.arg1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }


}
