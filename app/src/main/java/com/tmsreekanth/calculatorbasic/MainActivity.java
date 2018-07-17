package com.tmsreekanth.calculatorbasic;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {


    public TextView screenText;
    public TextView answerText;
    public String TAG = "com.tmsreekanth.calculatorbasic";
    boolean bracketOpen = false;
    boolean trigBracket=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenText = (TextView)findViewById(R.id.screenText);
        answerText = (TextView)findViewById(R.id.answerText);
        Log.e(TAG,"onCreate");
        Toast.makeText(getApplicationContext(),"Created by Sreekanth T M!",Toast.LENGTH_LONG).show();
    }


    /************************************************************************
     * Number buttons onClick methods
     *
     * @param view
     */

    public void numClick(View view){

        int num;
        switch (view.getId()){
            case R.id.zeroButton:num=0;
                break;
            case R.id.oneButton:num=1;
                break;
            case R.id.twoButton:num=2;
                break;
            case R.id.threeButton:num=3;
                break;
            case R.id.fourButton:num=4;
                break;
            case R.id.fiveButton:num=5;
                break;
            case R.id.sixButton:num=6;
                break;
            case R.id.sevenButton:num=7;
                break;
            case R.id.eightButton:num=8;
                break;
            case R.id.nineButton:num=9;
                break;
           default: Toast.makeText(getApplicationContext(),"Unknown Error!",Toast.LENGTH_SHORT).show();
                    num=10;
                break;
        }
        setScreenText(num);

        //To change font size as text increases

        int ot = getResources().getConfiguration().orientation;

        if(ot== Configuration.ORIENTATION_PORTRAIT){
            if(screenText.getText().toString().length() > 14){
                screenText.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
            }
            if(screenText.getText().toString().length()>18){
                screenText.setText("0");
                answerText.setText("Math Error!");
                screenText.setTextSize(TypedValue.COMPLEX_UNIT_SP,36);

            }
        }

        if(ot == Configuration.ORIENTATION_LANDSCAPE){
            if(screenText.getText().toString().length() > 25){
                screenText.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
            }
            if(screenText.getText().toString().length()>29){
                screenText.setText("0");
                answerText.setText("Math Error!");
                screenText.setTextSize(TypedValue.COMPLEX_UNIT_SP,26);

            }
        }


        Log.e(TAG,"numClick"+num);
    }


    public void setScreenText(int num){

        if(screenText.getText().toString().equals("0")){
            screenText.setText(""+num);
        }else{
            screenText.setText(screenText.getText().toString()+num);
        }
    }




    /***********************************************************
     * Functions onClick methods
     * @param view
     */


    public void plusClick(View view){

        if(answerText.getText().toString().equals("Math Error!")){
            answerText.setText("0");
            screenText.setText("");
        }



        if(!answerText.getText().toString().equals("0")){
            screenText.setText(answerText.getText().toString()+"+");
            answerText.setText("0");
        }else {
            screenText.setText(screenText.getText().toString() + "+");
        }
    }

    public void multiplyClick(View view){
        if(answerText.getText().toString().equals("Math Error!")){
            answerText.setText("0");
            screenText.setText("");
        }
        if(!answerText.getText().toString().equals("0")){
            screenText.setText(answerText.getText().toString()+"*");
            answerText.setText("0");
        }else {
            screenText.setText(screenText.getText().toString() + "*");
        }
    }

    public void minusClick(View view){
        if(answerText.getText().toString().equals("Math Error!")){
            answerText.setText("0");
            screenText.setText("");
        }
        if(!answerText.getText().toString().equals("0")){
            screenText.setText(answerText.getText().toString()+"-");
            answerText.setText("0");
        }else {
            screenText.setText(screenText.getText().toString() + "-");
        }
    }

    public void divideClick(View view){
        if(answerText.getText().toString().equals("Math Error!")){
            answerText.setText("0");
            screenText.setText("");
        }
        if(!answerText.getText().toString().equals("0")){
            screenText.setText(answerText.getText().toString()+"/");
            answerText.setText("0");
        }else {
            screenText.setText(screenText.getText().toString() + "/");
        }


    public void bracketClick(View view){

        if(answerText.getText().toString().equals("Math Error!") || screenText.getText().toString().equals("0")){
            answerText.setText("0");
            screenText.setText("");
        }

        if(trigBracket){
            screenText.setText(screenText.getText()+"}");
            trigBracket = false;
            return;
        }

        if(bracketOpen){
            screenText.setText(screenText.getText().toString()+")");
            bracketOpen = false;
        }else{
            screenText.setText(screenText.getText().toString()+"(");
            bracketOpen = true;
        }
    }

    public void modClick(View view){
        //answerText.setText("");
        if(answerText.getText().toString().equals("Math Error!")){
            answerText.setText("0");
            screenText.setText("");
        }
        if(!answerText.getText().toString().equals("0")){
            screenText.setText(answerText.getText().toString()+"%");
            answerText.setText("0");
        }
        else {
            screenText.setText(screenText.getText().toString() + "%");
        }
    }

    public void clrClick(View view){
        answerText.setText("0");
        screenText.setText("0");
        bracketOpen = false;
        int ot = getResources().getConfiguration().orientation;

        if(ot == Configuration.ORIENTATION_PORTRAIT) {
            screenText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        }
        else if(ot==Configuration.ORIENTATION_LANDSCAPE){
            screenText.setTextSize(TypedValue.COMPLEX_UNIT_SP,26);
        }
    }

    public void delClick(View view){
        String subS = (String) screenText.getText().subSequence(0,screenText.getText().toString().length() -1);
        char deletedChar = screenText.getText().toString().charAt(screenText.getText().toString().length()-1);

        if(deletedChar=='('){
            bracketOpen = false;
        }
        if(deletedChar==')'){
            bracketOpen = true;
        }

        if(subS.length()>0){
            screenText.setText(subS);
        }
        else{
            screenText.setText("0");
        }
    }

    public void dotClick(View view){
        screenText.setText(screenText.getText().toString()+".");
    }


    /********************
     * Special triginometry functions & root & pi
     * @param view
     */


    public void piClick(View view){
        if(screenText.getText().toString().equals("0")){
            screenText.setText("π");
        }
        else{
            screenText.setText(screenText.getText().toString()+"π");
        }
    }


    public void rootClick(View view){
        if(screenText.getText().toString().equals("0")){
            screenText.setText("");
        }
        screenText.setText(screenText.getText().toString()+"√{");
        trigBracket=true;
    }


    public void trigClick(View view){
        int trig;
        switch(view.getId()){
            case R.id.sinButton:trig=1;
            break;
            case R.id.cosButton:trig=2;
            break;
            case R.id.tanButton:trig=3;
            break;
            default:trig=0;
            break;
        }

        if(screenText.getText().toString().equals("0")){
            screenText.setText("");
        }

        if(trig==1){
            screenText.setText(screenText.getText()+"Sin{");
        }
        if(trig==2){
            screenText.setText(screenText.getText()+"Cos{");
        }
        if(trig==3){
            screenText.setText(screenText.getText()+"Tan{");
        }
        trigBracket=true;


    }



    public void equalClick(View view){
        String expression = screenText.getText().toString();
        String exp2="";
        int exc=0;
        double degree,rad,ans;

        while(expression.contains("Sin{") || expression.contains("Cos{") || expression.contains("Tan{") || expression.contains("√{")){

            try {
                exp2 = expression.substring(expression.indexOf("{") + 1, expression.indexOf("}"));
                degree = Double.parseDouble(exp2);
            }catch (Exception e){
                answerText.setText("Math Error!");
                exc = 1;
                trigBracket = false;
                break;
            }

            rad = Math.toRadians(degree);
            Log.e(TAG,"Inside while loop equal: "+expression);

            if(expression.charAt(expression.indexOf("{")-1)=='√'){
                ans = Math.sqrt(degree);
                try {
                    expression = expression.replaceFirst("√\\{" + exp2 + "\\}", "" + ans);
                }catch(Exception e){
                    answerText.setText("Math Error!");
                    break;
                }
            }

            else if(expression.charAt(expression.indexOf("{")-3)=='S'){
                ans = Math.sin(rad);
                try {
                    expression = expression.replaceFirst("Sin\\{" + exp2 + "\\}", "" + ans);
                }catch(Exception e){
                    answerText.setText("Math Error!");
                    break;
                }
                Log.e(TAG,"Sin method :"+expression);
            }
            else if(expression.charAt(expression.indexOf("{")-3)=='C'){
                ans = Math.cos(rad);
                try {
                    expression = expression.replaceFirst("Cos\\{" + exp2 + "\\}", "" + ans);
                }catch (Exception e){
                    answerText.setText("Math Error!");
                    break;
                }
            }
            else if(expression.charAt(expression.indexOf("{")-3)=='T'){
                ans = Math.tan(rad);
                try {
                    expression = expression.replaceFirst("Tan\\{" + exp2 + "\\}", "" + ans);
                }catch (Exception e){
                    answerText.setText("Math Error!");
                    break;
                }
            }
            else{
                continue;
            }
            Log.e(TAG,"bottom while "+expression);
        }

        Log.e(TAG,"Outside while :"+expression);

        //Replacing π with 3.14159265359

        if(expression.contains("π")){
            expression = expression.replaceAll("π","3.14159265359");
        }


        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        String result = "";


        try {
            Scriptable scope = rhino.initSafeStandardObjects();
            result = rhino.evaluateString(scope, expression, "JavaScript", 1, null).toString();
        } catch (Exception e) {
            result = "Math Error!";
            screenText.setText("0");
        }
        if (result.length() > 17) {
            result = result.substring(0, 12);
        }

        answerText.setText(result);
    }

}
