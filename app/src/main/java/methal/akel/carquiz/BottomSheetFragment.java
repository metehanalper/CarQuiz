package methal.akel.carquiz;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.io.File;
import java.util.Random;

public class BottomSheetFragment extends BottomSheetDialogFragment {








    public static BottomSheetFragment newInstance() {
        BottomSheetFragment fragment = new BottomSheetFragment();
        return fragment;
    }







    /*public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_fragment, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.bottom_sheet_fragment,container,false);

        LinearLayout fiftypercent_layout = (LinearLayout)v.findViewById(R.id.fiftypercent_layout);
        LinearLayout askaudience_layout = (LinearLayout)v.findViewById(R.id.askaudience_layout);





        fiftypercent_layout.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                int counter=0;

                if(GameScreen.button1.getAlpha()==1){
                    counter++;
                }
                if(GameScreen.button2.getAlpha()==1){
                    counter++;
                }
                if(GameScreen.button3.getAlpha()==1){
                    counter++;
                }
                if(GameScreen.button4.getAlpha()==1){
                    counter++;
                }

                if(GameScreen.userscore>= 5 && counter>=3){

                    String answer="";

                    GameScreen.userscore=GameScreen.userscore-5;
                    GameScreen.e.putInt("userscore",GameScreen.userscore);
                    GameScreen.e.commit();
                    GameScreen.score.setText(String.valueOf(GameScreen.userscore));

                    if (GameScreen.questionnumber>=GameScreen.questionList.size()){
                        answer = GameScreen.askedQuestion.model;
                    }
                    else{
                        answer = GameScreen.askedQuestion.buttonAnswerText;
                    }



                    Random rnd = new Random();

                    int randomNumber = rnd.nextInt(4)+1;

                    for(int i =0;i<2;i++){

                        while (true){

                            if(randomNumber==1 ){
                                if(GameScreen.questionnumber<GameScreen.questionList.size()*2){

                                    if(GameScreen.button1.getText().equals(answer) || GameScreen.button1.getAlpha()==0){

                                        randomNumber = rnd.nextInt(4)+1;

                                    }
                                    else {
                                        GameScreen.button1.setAlpha(0);
                                        break;
                                    }

                                }


                            }
                            else if(randomNumber==2 ){
                                if(GameScreen.questionnumber<GameScreen.questionList.size()*2){

                                    if(GameScreen.button2.getText().equals(answer) || GameScreen.button2.getAlpha()==0){

                                        randomNumber = rnd.nextInt(4)+1;

                                    }
                                    else {
                                        GameScreen.button2.setAlpha(0);
                                        break;
                                    }

                                }


                            }
                            else if(randomNumber==3){
                                if(GameScreen.questionnumber<GameScreen.questionList.size()*2){

                                    if(GameScreen.button3.getText().equals(answer) || GameScreen.button3.getAlpha()==0){

                                        randomNumber = rnd.nextInt(4)+1;

                                    }
                                    else {
                                        GameScreen.button3.setAlpha(0);
                                        break;
                                    }

                                }
                            }
                            else if(randomNumber==4 ){
                                if(GameScreen.questionnumber<GameScreen.questionList.size()*2){

                                    if(GameScreen.button4.getText().equals(answer) || GameScreen.button4.getAlpha()==0){

                                        randomNumber = rnd.nextInt(4)+1;

                                    }
                                    else {
                                        GameScreen.button4.setAlpha(0);
                                        break;
                                    }

                                }

                            }
                        }

                    }
                    Toast.makeText(getContext(),"JOKER USED",Toast.LENGTH_SHORT).show();

                    dismiss();

                }
                else{

                    Toast.makeText(getContext(),"YOU DON'T HAVE ENOUGH MONEY",Toast.LENGTH_SHORT).show();

                }


            }
        });

        askaudience_layout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                if(GameScreen.userscore>= 10){

                    GameScreen.userscore=GameScreen.userscore-10;
                    GameScreen.e.putInt("userscore",GameScreen.userscore);
                    GameScreen.e.commit();


                    (GameScreen.score).setText(String.valueOf(GameScreen.userscore));

                    showAlertDialogAskAudience();

                    dismiss();




                    Toast.makeText(getContext(),"ASKED AUDIENCE",Toast.LENGTH_SHORT).show();

                }
                else{

                    Toast.makeText(getContext(),"YOU DON'T HAVE ENOUGH MONEY",Toast.LENGTH_SHORT).show();

                }










            }
        });



        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showAlertDialogAskAudience(){
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.askaudience_popup, null);
        builder.setView(customLayout);

        // add a button

        TextView percentage1,percentage2,percentage3,percentage4,questionname1,questionname2,questionname3,questionname4;
        ProgressBar progressBar1,progressBar2,progressBar3,progressBar4;

        percentage1 = customLayout.findViewById(R.id.percentage1);
        percentage2 = customLayout.findViewById(R.id.percentage2);
        percentage3 = customLayout.findViewById(R.id.percentage3);
        percentage4 = customLayout.findViewById(R.id.percentage4);

        questionname1 = customLayout.findViewById(R.id.questionname1);
        questionname2 = customLayout.findViewById(R.id.questionname2);
        questionname3 = customLayout.findViewById(R.id.questionname3);
        questionname4 = customLayout.findViewById(R.id.questionname4);

        progressBar1 = customLayout.findViewById(R.id.progressBar1);
        progressBar2 = customLayout.findViewById(R.id.progressBar2);
        progressBar3 = customLayout.findViewById(R.id.progressBar3);
        progressBar4 = customLayout.findViewById(R.id.progressBar4);


        float visiblebuttoncount=0;

        visiblebuttoncount = GameScreen.button1.getAlpha()+GameScreen.button2.getAlpha()+GameScreen.button3.getAlpha()+
                GameScreen.button4.getAlpha();

        int baserandomnumber = (int)(100/visiblebuttoncount)+1;

        Random rnd = new Random();

        String answer="";

        if(GameScreen.questionnumber>=GameScreen.questionList.size()){
            answer = GameScreen.askedQuestion.model;
        }
        else {
            answer = GameScreen.askedQuestion.buttonAnswerText;
        }


        if (visiblebuttoncount==4){



            boolean flag = true;
            int percentageofanswer=0;
            int percentageofwrong1=0;
            int percentageofwrong2=0;
            int percentageofwrong3=0;



            while(flag){


                percentageofanswer = rnd.nextInt(101-baserandomnumber)+baserandomnumber;

                int wholenumber=100;

                wholenumber = wholenumber - percentageofanswer;

                percentageofwrong1 = rnd.nextInt(wholenumber+1);

                while (percentageofwrong1>percentageofanswer){

                    percentageofwrong1 = rnd.nextInt(wholenumber+1);

                }

                wholenumber = wholenumber - percentageofwrong1;


                percentageofwrong2 = rnd.nextInt(wholenumber+1);

                while (percentageofwrong2>percentageofanswer){

                    percentageofwrong2 = rnd.nextInt(wholenumber+1);

                }

                wholenumber = wholenumber - percentageofwrong2;

                percentageofwrong3=wholenumber;

                if((percentageofwrong1+percentageofwrong2+percentageofwrong3+percentageofanswer)==100 && percentageofwrong3<percentageofanswer){
                    flag=false;
                }



            }



            if(GameScreen.button1.getText().equals(answer)){


                percentage1.setText(String.valueOf(percentageofanswer));
                progressBar1.setProgress(percentageofanswer);
                questionname1.setText(GameScreen.button1.getText());

                percentage2.setText(String.valueOf(percentageofwrong1));
                progressBar2.setProgress(percentageofwrong1);
                questionname2.setText(GameScreen.button2.getText());

                percentage3.setText(String.valueOf(percentageofwrong2));
                progressBar3.setProgress(percentageofwrong2);
                questionname3.setText(GameScreen.button3.getText());

                percentage4.setText(String.valueOf(percentageofwrong3));
                progressBar4.setProgress(percentageofwrong3);
                questionname4.setText(GameScreen.button4.getText());



            }
            else if (GameScreen.button2.getText().equals(answer)){

                percentage1.setText(String.valueOf(percentageofwrong1));
                progressBar1.setProgress(percentageofwrong1);
                questionname1.setText(GameScreen.button1.getText());

                percentage2.setText(String.valueOf(percentageofanswer));
                progressBar2.setProgress(percentageofanswer);
                questionname2.setText(GameScreen.button2.getText());

                percentage3.setText(String.valueOf(percentageofwrong2));
                progressBar3.setProgress(percentageofwrong2);
                questionname3.setText(GameScreen.button3.getText());

                percentage4.setText(String.valueOf(percentageofwrong3));
                progressBar4.setProgress(percentageofwrong3);
                questionname4.setText(GameScreen.button4.getText());

            }
            else if (GameScreen.button3.getText().equals(answer)){

                percentage1.setText(String.valueOf(percentageofwrong1));
                progressBar1.setProgress(percentageofwrong1);
                questionname1.setText(GameScreen.button1.getText());

                percentage2.setText(String.valueOf(percentageofwrong2));
                progressBar2.setProgress(percentageofwrong2);
                questionname2.setText(GameScreen.button2.getText());

                percentage3.setText(String.valueOf(percentageofanswer));
                progressBar3.setProgress(percentageofanswer);
                questionname3.setText(GameScreen.button3.getText());

                percentage4.setText(String.valueOf(percentageofwrong3));
                progressBar4.setProgress(percentageofwrong3);
                questionname4.setText(GameScreen.button4.getText());

            }
            else if (GameScreen.button4.getText().equals(answer)){

                percentage1.setText(String.valueOf(percentageofwrong1));
                progressBar1.setProgress(percentageofwrong1);
                questionname1.setText(GameScreen.button1.getText());

                percentage2.setText(String.valueOf(percentageofwrong2));
                progressBar2.setProgress(percentageofwrong2);
                questionname2.setText(GameScreen.button2.getText());

                percentage3.setText(String.valueOf(percentageofwrong3));
                progressBar3.setProgress(percentageofwrong3);
                questionname3.setText(GameScreen.button3.getText());

                percentage4.setText(String.valueOf(percentageofanswer));
                progressBar4.setProgress(percentageofanswer);
                questionname4.setText(GameScreen.button4.getText());

            }







        }
        else if (visiblebuttoncount==3){

            boolean flag = true;
            int percentageofanswer=0;
            int percentageofwrong1=0;
            int percentageofwrong2=0;




            while(flag){


                percentageofanswer = rnd.nextInt(101-baserandomnumber)+baserandomnumber;

                int wholenumber=100;

                wholenumber = wholenumber - percentageofanswer;

                percentageofwrong1 = rnd.nextInt(wholenumber+1);

                while (percentageofwrong1>percentageofanswer){

                    percentageofwrong1 = rnd.nextInt(wholenumber+1);

                }

                wholenumber = wholenumber - percentageofwrong1;



                percentageofwrong2=wholenumber;

                if((percentageofwrong1+percentageofwrong2+percentageofanswer)==100 && percentageofwrong2<percentageofanswer){
                    flag=false;
                }

            }


            if(GameScreen.button1.getText().equals(answer)){


                if(GameScreen.button2.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofanswer));
                    progressBar1.setProgress(percentageofanswer);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button3.getText());

                    percentage3.setText(String.valueOf(percentageofwrong2));
                    progressBar3.setProgress(percentageofwrong2);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);



                }
                else if(GameScreen.button3.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofanswer));
                    progressBar1.setProgress(percentageofanswer);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofwrong2));
                    progressBar3.setProgress(percentageofwrong2);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }
                else if(GameScreen.button4.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofanswer));
                    progressBar1.setProgress(percentageofanswer);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofwrong2));
                    progressBar3.setProgress(percentageofwrong2);
                    questionname3.setText(GameScreen.button3.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }






            }
            else if (GameScreen.button2.getText().equals(GameScreen.askedQuestion.getButtonAnswerText())){

                if(GameScreen.button1.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofanswer));
                    progressBar1.setProgress(percentageofanswer);
                    questionname1.setText(GameScreen.button2.getText());

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button3.getText());

                    percentage3.setText(String.valueOf(percentageofwrong2));
                    progressBar3.setProgress(percentageofwrong2);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);



                }
                else if(GameScreen.button3.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofwrong1));
                    progressBar1.setProgress(percentageofwrong1);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofwrong2));
                    progressBar3.setProgress(percentageofwrong2);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }
                else if(GameScreen.button4.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofwrong1));
                    progressBar1.setProgress(percentageofwrong1);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofwrong2));
                    progressBar3.setProgress(percentageofwrong2);
                    questionname3.setText(GameScreen.button3.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }


            }
            else if (GameScreen.button3.getText().equals(answer)){

                if(GameScreen.button1.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofwrong1));
                    progressBar1.setProgress(percentageofwrong1);
                    questionname1.setText(GameScreen.button2.getText());

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button3.getText());

                    percentage3.setText(String.valueOf(percentageofwrong2));
                    progressBar3.setProgress(percentageofwrong2);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);



                }
                else if(GameScreen.button2.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofwrong1));
                    progressBar1.setProgress(percentageofwrong1);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button3.getText());

                    percentage3.setText(String.valueOf(percentageofwrong2));
                    progressBar3.setProgress(percentageofwrong2);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }
                else if(GameScreen.button4.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofwrong1));
                    progressBar1.setProgress(percentageofwrong1);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofwrong2));
                    progressBar2.setProgress(percentageofwrong2);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button3.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }


            }
            else if (GameScreen.button4.getText().equals(answer)){

                if(GameScreen.button1.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofwrong1));
                    progressBar1.setProgress(percentageofwrong1);
                    questionname1.setText(GameScreen.button2.getText());

                    percentage2.setText(String.valueOf(percentageofwrong2));
                    progressBar2.setProgress(percentageofwrong2);
                    questionname2.setText(GameScreen.button3.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);



                }
                else if(GameScreen.button2.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofwrong1));
                    progressBar1.setProgress(percentageofwrong1);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofwrong2));
                    progressBar2.setProgress(percentageofwrong2);
                    questionname2.setText(GameScreen.button3.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }
                else if(GameScreen.button3.getAlpha()==0){

                    percentage1.setText(String.valueOf(percentageofwrong1));
                    progressBar1.setProgress(percentageofwrong1);
                    questionname1.setText(GameScreen.button1.getText());

                    percentage2.setText(String.valueOf(percentageofwrong2));
                    progressBar2.setProgress(percentageofwrong2);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }

            }




        }
        else if (visiblebuttoncount==2){


            int percentageofanswer= rnd.nextInt(50)+51;
            int percentageofwrong1=100-percentageofanswer;


            if(GameScreen.button1.getText().equals(answer)){


                if(GameScreen.button2.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button1.getText());

                    percentage3.setText(String.valueOf(percentageofwrong1));
                    progressBar3.setProgress(percentageofwrong1);
                    questionname3.setText(GameScreen.button2.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);



                }
                else if(GameScreen.button3.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button1.getText());

                    percentage3.setText(String.valueOf(percentageofwrong1));
                    progressBar3.setProgress(percentageofwrong1);
                    questionname3.setText(GameScreen.button3.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }
                else if(GameScreen.button4.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button1.getText());

                    percentage3.setText(String.valueOf(percentageofwrong1));
                    progressBar3.setProgress(percentageofwrong1);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }






            }
            else if(GameScreen.button2.getText().equals(answer)){


                if(GameScreen.button1.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button1.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button2.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);



                }
                else if(GameScreen.button3.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofwrong1));
                    progressBar3.setProgress(percentageofwrong1);
                    questionname3.setText(GameScreen.button3.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }
                else if(GameScreen.button4.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofwrong1));
                    progressBar3.setProgress(percentageofwrong1);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }






            }

            else if(GameScreen.button3.getText().equals(answer)){


                if(GameScreen.button1.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button1.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button3.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);



                }
                else if(GameScreen.button2.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button3.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }
                else if(GameScreen.button4.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofanswer));
                    progressBar2.setProgress(percentageofanswer);
                    questionname2.setText(GameScreen.button3.getText());

                    percentage3.setText(String.valueOf(percentageofwrong1));
                    progressBar3.setProgress(percentageofwrong1);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }


            }

            else if(GameScreen.button4.getText().equals(answer)){


                if(GameScreen.button1.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button1.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);



                }
                else if(GameScreen.button2.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button2.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }
                else if(GameScreen.button3.getAlpha()!=0){

                    percentage1.setAlpha(0);
                    progressBar1.setAlpha(0);
                    questionname1.setAlpha(0);

                    percentage2.setText(String.valueOf(percentageofwrong1));
                    progressBar2.setProgress(percentageofwrong1);
                    questionname2.setText(GameScreen.button3.getText());

                    percentage3.setText(String.valueOf(percentageofanswer));
                    progressBar3.setProgress(percentageofanswer);
                    questionname3.setText(GameScreen.button4.getText());

                    percentage4.setAlpha(0);
                    progressBar4.setAlpha(0);
                    questionname4.setAlpha(0);

                }


            }






        }
        else if (visiblebuttoncount==1){

            int percentageofanswer =100;


            percentage1.setAlpha(0);
            progressBar1.setAlpha(0);
            questionname1.setAlpha(0);

            percentage2.setText(String.valueOf(percentageofanswer));
            progressBar2.setProgress(percentageofanswer);
            questionname2.setText(answer);

            percentage3.setAlpha(0);
            progressBar3.setAlpha(0);
            questionname3.setAlpha(0);

            percentage4.setAlpha(0);
            progressBar4.setAlpha(0);
            questionname4.setAlpha(0);


        }


        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
