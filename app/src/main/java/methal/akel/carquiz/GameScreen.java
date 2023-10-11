package methal.akel.carquiz;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.N)



public class GameScreen extends AppCompatActivity {
    static TextView numberofquestion, textofquestion, score,live, finalscore;
    ImageView imageofquestion, gameoverscoreicon,winscoreicon;
    public static Button button1,button2,button3,button4,playagainbutton,highscorebutton, highscoreplayagain;

    LinearLayout market_layout;
    LinearLayout can_layout, showadslayout;
    static List<Question> questionList;
    private Animation  car_image_anim_end, gamescreen_button_define_animation_left,gamescreen_button_define_animation_right;
    List<String> brandList;
    ArrayList<String> mercedes;
    ArrayList<String> honda;
    ArrayList<String> ford;
    ArrayList<String> renault;
    ArrayList<String> hyundai;
    ArrayList<String> nissan;
    ArrayList<String> bmw;
    ArrayList<String> citroen;
    ArrayList<String> peugeot;
    ArrayList<String> vw;
    ArrayList<String> fiat;
    ArrayList<String> skoda;
    ArrayList<String> audi;
    ArrayList<String> seat;
    ArrayList<String> opel;

    private InterstitialAd mInterstitialAd;

    public static RewardedAd mRewardedAd;


     static SharedPreferences sp;
     static SharedPreferences.Editor e;


    static int  userscore =0;
    static Question askedQuestion=null;

    static int questionnumber =0;
    static int userlive = 3;

    String clickedbutton ="";
    static Question basicQuestion;
    Random rnd = new Random();
    Vibrator vibrator;
    ArrayList<Score> scores = new ArrayList<>();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        define();
    }
    public void define(){

        showadslayout = findViewById(R.id.show_ads_layout);
        showadslayout.setAlpha(0);
        numberofquestion = findViewById(R.id.questionnumber);
        textofquestion = findViewById(R.id.questionText);
        score = findViewById(R.id.skor);
        live = findViewById(R.id.life);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        market_layout = findViewById(R.id.market_layout);
        questionnumber=0;
        can_layout =findViewById(R.id.can_layout);

        loadRew();

        sp = getSharedPreferences("TABLO", Context.MODE_PRIVATE);

        e = sp.edit();

        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });




        car_image_anim_end = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.car_image_anim_end);
        gamescreen_button_define_animation_left =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.gamescreen_button_define_animation_left);
        gamescreen_button_define_animation_right =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.gamescreen_button_define_animation_right);



        imageofquestion = findViewById(R.id.questionImage);
        button1 = findViewById(R.id.tus1);
        button2 = findViewById(R.id.tus2);
        button3 = findViewById(R.id.tus3);
        button4 = findViewById(R.id.tus4);






        fillList();
        changeQuestion();
        click();


    }
    public void fillList(){


        loadRew();

        brandList = new ArrayList<>();

        mercedes = new ArrayList<>();
        honda = new ArrayList<>();
        ford = new ArrayList<>();
        renault = new ArrayList<>();
        nissan = new ArrayList<>();
        hyundai = new ArrayList<>();
        bmw = new ArrayList<>();
        citroen = new ArrayList<>();
        peugeot = new ArrayList<>();
        vw = new ArrayList<>();
        fiat = new ArrayList<>();
        skoda = new ArrayList<>();
        seat = new ArrayList<>();
        opel = new ArrayList<>();
        audi = new ArrayList<>();


        mercedes.add("A SERIES");mercedes.add("B SERIES");mercedes.add("C SERIES");mercedes.add("E SERIES");mercedes.add("S SERIES");mercedes.add("G SERIES");mercedes.add("SLS");

        honda.add("CRV");honda.add("JAZZ");honda.add("CIVIC");honda.add("S2000");

        ford.add("MONDEO");ford.add("FOCUS");ford.add("FIESTA");ford.add("COURIER");ford.add("TRANSIT");ford.add("CONNECT");ford.add("CMAX");
        ford.add("MUSTANG");ford.add("KA");ford.add("FMAX");ford.add("FUSION");ford.add("ESCORT");

        renault.add("MEGANE");renault.add("CLIO");renault.add("CAPTUR");renault.add("MAGNUM");renault.add("FLUENCE");renault.add("SYMBOL");

        hyundai.add("STAREX");hyundai.add("i10");hyundai.add("i20");hyundai.add("i30");hyundai.add("GETZ");hyundai.add("ELANTRA");hyundai.add("ACCENT");

        nissan.add("JUKE");nissan.add("MICRA");nissan.add("GTR");nissan.add("QASHQAI");nissan.add("NAVARA");

        bmw.add("1 SERIES");bmw.add("3 SERIES");bmw.add("4 SERIES");bmw.add("5 SERIES");bmw.add("7 SERIES");
        bmw.add("8 SERIES");bmw.add("X SERIES");

        citroen.add("C1");citroen.add("C2");citroen.add("C3");citroen.add("C4");citroen.add("C5");citroen.add("BERLINGO");

        peugeot.add("106");peugeot.add("207");peugeot.add("308");peugeot.add("407");peugeot.add("508");peugeot.add("5008");peugeot.add("3008");

        vw.add("GOLF");vw.add("POLO");vw.add("TRANSPORTER");vw.add("CC");vw.add("SCIROCCO");vw.add("BEETLE");vw.add("PASSAT");vw.add("CADDY");

        fiat.add("DOBLO");fiat.add("EGEA");fiat.add("TIPO");fiat.add("FIORINO");fiat.add("500");fiat.add("LINEA");

        skoda.add("FABIA");skoda.add("RAPID");skoda.add("SUPERB");skoda.add("YETI");skoda.add("SCALA");skoda.add("KAMIQ");

        audi.add("A1");audi.add("A3");audi.add("A4");audi.add("A5");audi.add("A8");audi.add("Q8");audi.add("RS6");

        seat.add("LEON");seat.add("IBIZA");seat.add("CORDOBA");seat.add("ATECA");

        opel.add("ASTRA");opel.add("CORSA");opel.add("MOKKA");opel.add("VECTRA");opel.add("INSIGNIA");
        brandList.add("HONDA"); brandList.add("MERCEDES"); brandList.add("FORD");brandList.add("RENAULT");brandList.add("HYUNDAI");brandList.add("NISSAN");
        brandList.add("BMW");brandList.add("TOYOTA");brandList.add("VOLKSWAGEN");brandList.add("CITROEN");brandList.add("PEUGEOT");brandList.add("FIAT");
        brandList.add("SKODA");brandList.add("AUDI");brandList.add("SEAT");brandList.add("OPEL");



        if (sp.getBoolean("continue", false)==false){

            userlive=3;
            userscore=0;
            questionnumber=0;







            questionList = new ArrayList<>();

            Question q1 = new Question("MERCEDES",R.drawable.a, "A SERIES", mercedes);
            Question q2= new Question("MERCEDES",R.drawable.slsamg,"SLS", mercedes);
            Question q3 = new Question("MERCEDES",R.drawable.c,"C SERIES", mercedes);
            Question q4 = new Question("MERCEDES",R.drawable.g, "G SERIES", mercedes);

            Question q5 = new Question("HONDA",R.drawable.civic, "CIVIC",honda);
            Question q6 = new Question("HONDA",R.drawable.crv, "CRV",honda);
            Question q7 = new Question("HONDA",R.drawable.jazz,"JAZZ",honda);
            Question q8= new Question("HONDA",R.drawable.s2000, "S2000",honda);

            Question q9 = new Question("HYUNDAI",R.drawable.elantra, "ELANTRA",hyundai);
            Question q10 = new Question("HYUNDAI",R.drawable.accent, "ACCENT",hyundai);
            Question q11 = new Question("HYUNDAI",R.drawable.getz, "GETZ",hyundai);
            Question q12= new Question("HYUNDAI",R.drawable.starex,"STAREX",hyundai);
            Question q13 = new Question("HYUNDAI",R.drawable.i10,"i10",hyundai);
            Question q14 = new Question("HYUNDAI",R.drawable.i20,"i20",hyundai);
            Question q15 = new Question("HYUNDAI",R.drawable.i30,"i30",hyundai);

            Question q16 = new Question("NISSAN",R.drawable.micra,"MICRA",nissan);
            Question q17= new Question("NISSAN",R.drawable.gtr,"GTR",nissan);

            Question q18= new Question("RENAULT",R.drawable.megane2,"MEGANE",renault);
            Question q19= new Question("RENAULT",R.drawable.captur,"CAPTUR",renault);
            Question q20= new Question("RENAULT",R.drawable.magnum,"MAGNUM",renault);
            Question q21= new Question("RENAULT",R.drawable.fluence,"FLUENCE",renault);
            Question q22= new Question("RENAULT",R.drawable.clio,"CLIO",renault);
            Question q23= new Question("RENAULT",R.drawable.symbol,"SYMBOL",renault);

            Question q24= new Question("FORD",R.drawable.curier,"COURIER",ford);
            Question q25= new Question("FORD",R.drawable.cmax,"CMAX",ford);
            Question q26= new Question("FORD",R.drawable.fiesta,"FIESTA",ford);
            Question q27= new Question("FORD",R.drawable.focus,"FOCUS",ford);
            Question q28= new Question("FORD",R.drawable.mondeo,"MONDEO",ford);
            Question q29= new Question("FORD",R.drawable.mustang,"MUSTANG",ford);
            Question q30= new Question("FORD",R.drawable.fmax,"FMAX",ford);
            Question q31= new Question("FORD",R.drawable.ka,"KA",ford);

           Question q32= new Question("BMW",R.drawable.bmw1,"1 SERIES",bmw);
           Question q33= new Question("BMW",R.drawable.bmw3,"3 SERIES",bmw);
           Question q34= new Question("BMW",R.drawable.bmw4,"4 SERIES",bmw);
           Question q35= new Question("BMW",R.drawable.bmw5,"5 SERIES",bmw);
           Question q36= new Question("BMW",R.drawable.bmw7,"7 SERIES",bmw);
           Question q37= new Question("BMW",R.drawable.bmw8,"8 SERIES",bmw);
           Question q38 = new Question("BMW",R.drawable.bmwx5,"X SERIES",bmw);

           Question q39 = new Question("CITROEN",R.drawable.c1,"C1",citroen);
           Question q40 = new Question("CITROEN",R.drawable.c2,"C2",citroen);
           Question q41 = new Question("CITROEN",R.drawable.c3,"C3",citroen);
           Question q42 = new Question("CITROEN",R.drawable.c4,"C4",citroen);
           Question q43 = new Question("CITROEN",R.drawable.berlingo,"BERLINGO",citroen);

           Question q44 = new Question("PEUGEOT",R.drawable.p106,"106",peugeot);
           Question q45 = new Question("PEUGEOT",R.drawable.p207,"207",peugeot);
           Question q46 = new Question("PEUGEOT",R.drawable.p308,"308",peugeot);
           Question q47 = new Question("PEUGEOT",R.drawable.p407,"407",peugeot);
           Question q48 = new Question("PEUGEOT",R.drawable.p508,"508",peugeot);
           Question q49 = new Question("PEUGEOT",R.drawable.p3008,"3008",peugeot);
           Question q50 = new Question("PEUGEOT",R.drawable.p5008,"5008",peugeot);


           Question q51 = new Question("VOLKSWAGEN",R.drawable.polo,"POLO",vw);
           Question q52 = new Question("VOLKSWAGEN",R.drawable.golf,"GOLF",vw);
           Question q53 = new Question("VOLKSWAGEN",R.drawable.passat,"PASSAT",vw);
           Question q54 = new Question("VOLKSWAGEN",R.drawable.cc,"CC",vw);
           Question q55 = new Question("VOLKSWAGEN",R.drawable.caddy,"CADDY",vw);
           Question q56 = new Question("VOLKSWAGEN",R.drawable.transporter,"TRANSPORTER",vw);
           Question q57 = new Question("VOLKSWAGEN",R.drawable.beetle,"BEETLE",vw);
           Question q58 = new Question("VOLKSWAGEN",R.drawable.scirocco,"SCIROCCO",vw);

           Question q59 = new Question("FIAT",R.drawable.f500,"500",fiat);
           Question q60 = new Question("FIAT",R.drawable.linea,"LINEA",fiat);
           Question q61 = new Question("FIAT",R.drawable.egea,"EGEA",fiat);
           Question q62 = new Question("FIAT",R.drawable.doblo,"DOBLO",fiat);
           Question q63 = new Question("FIAT",R.drawable.fiorino,"FIORINO",fiat);
           Question q64 = new Question("FIAT",R.drawable.tipo,"TIPO",fiat);

           Question q65 = new Question("SKODA",R.drawable.fabia,"FABIA",skoda);
           Question q66 = new Question("SKODA",R.drawable.kamiq,"KAMIQ",skoda);
           Question q67 = new Question("SKODA",R.drawable.superb,"SUPERB",skoda);
           Question q68 = new Question("SKODA",R.drawable.scala,"SCALA",skoda);

           Question q69 = new Question("AUDI",R.drawable.a3,"A3",audi);
           Question q70 = new Question("AUDI",R.drawable.a5,"A5",audi);
           Question q71 = new Question("AUDI",R.drawable.a8,"A8",audi);
           Question q72 = new Question("AUDI",R.drawable.a4,"A4",audi);
           Question q73 = new Question("AUDI",R.drawable.rs6,"RS6",audi);
           Question q74 = new Question("AUDI",R.drawable.q8,"Q8",audi);

           Question q75 = new Question("SEAT",R.drawable.leon,"LEON",seat);
           Question q76 = new Question("SEAT",R.drawable.ibiza,"IBIZA",seat);
           Question q77 = new Question("SEAT",R.drawable.ateca,"ATECA",seat);
           Question q78 = new Question("SEAT",R.drawable.cordoba,"CORDOBA",seat);

           Question q79 = new Question("OPEL",R.drawable.corsa,"CORSA",opel);
           Question q80 = new Question("OPEL",R.drawable.astra,"ASTRA",opel);
           Question q81 = new Question("OPEL",R.drawable.vectra,"VECTRA",opel);
           Question q82 = new Question("OPEL",R.drawable.insignia,"INSIGNIA",opel);
           Question q83 = new Question("OPEL",R.drawable.mokka,"MOKKA",opel);




            questionList.add(q1);questionList.add(q2);questionList.add(q3);questionList.add(q4);questionList.add(q5);questionList.add(q6);questionList.add(q7);
            questionList.add(q8);questionList.add(q9);questionList.add(q10);questionList.add(q11);questionList.add(q12);questionList.add(q13);questionList.add(q14);
            questionList.add(q15);questionList.add(q16);questionList.add(q17);questionList.add(q18);questionList.add(q19);questionList.add(q20);questionList.add(q21);
            questionList.add(q22);questionList.add(q23);questionList.add(q24);questionList.add(q25);questionList.add(q26);questionList.add(q27);questionList.add(q28);
            questionList.add(q29);questionList.add(q30);questionList.add(q31);questionList.add(q32);questionList.add(q33);questionList.add(q34);questionList.add(q35);
            questionList.add(q36);questionList.add(q37);questionList.add(q38);questionList.add(q39);questionList.add(q40);questionList.add(q41);questionList.add(q42);
            questionList.add(q43);questionList.add(q44);questionList.add(q45);questionList.add(q46);questionList.add(q47);questionList.add(q48);questionList.add(q49);
            questionList.add(q50);questionList.add(q51);questionList.add(q52);questionList.add(q53);questionList.add(q54);questionList.add(q55);questionList.add(q56);
            questionList.add(q57);questionList.add(q58);questionList.add(q59);questionList.add(q60);questionList.add(q61);questionList.add(q62);questionList.add(q63);
            questionList.add(q64);questionList.add(q65);questionList.add(q66);questionList.add(q67);questionList.add(q68);questionList.add(q69);questionList.add(q70);
            questionList.add(q71);questionList.add(q72);questionList.add(q73);questionList.add(q74);questionList.add(q75);questionList.add(q76);questionList.add(q77);
            questionList.add(q78);questionList.add(q79);questionList.add(q80);questionList.add(q81);questionList.add(q82);questionList.add(q83);

            e.putBoolean("continue", true);
            e.putInt("userscore",userscore);
            e.putInt("userlive",userlive);
            e.putInt("questionnumber",questionnumber);

            Gson gson = new Gson();
            String json = gson.toJson(questionList);
            e.putString("list", json);

            e.commit();

        }
        else {
            userlive = sp.getInt("userlive", 0);
            userscore = sp.getInt("userscore", 0);

            Gson gson = new Gson();
            String json = sp.getString("list", "boş");
            Type type = new TypeToken<ArrayList<Question>>() {}.getType();
            questionList = gson.fromJson(json,type);
            questionnumber = sp.getInt("questionnumber", 0);



        }


    }
    public void changeQuestion(){
        if (mRewardedAd!=null){
            showadslayout.setAlpha(1);
        }




        if (questionnumber==questionList.size()){


            runOnUiThread(new Runnable() {
                @Override public void run() {


                    AdRequest adRequest = new AdRequest.Builder().build();


                    InterstitialAd.load(getApplicationContext(),"ca-app-pub-2267634847444711/1563701176", adRequest, new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;
                            Log.e("reklam", "onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            Log.e("reklam", loadAdError.getMessage());
                            mInterstitialAd = null;
                        }
                    });

                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(GameScreen.this);
                    } else {
                        Log.e("TAG", "The interstitial ad wasn't ready yet.");
                    }
                }
            });










            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View customLayout = getLayoutInflater().inflate(R.layout.part2_popup, null);
            builder.setView(customLayout);
            builder.setMessage("CONGRATULATIONS. You passed part 1. Be ready for part 2!!");
            builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {






                }
            });



            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(questionnumber==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View customLayout = getLayoutInflater().inflate(R.layout.part1_popup, null);
            builder.setView(customLayout);
            builder.setMessage("WELCOME. Be ready for part 1!!");
            builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });



            AlertDialog dialog = builder.create();
            dialog.show();

        }


        button1.setAlpha(1);
        button2.setAlpha(1);
        button3.setAlpha(1);
        button4.setAlpha(1);

        button1.startAnimation(gamescreen_button_define_animation_left);
        button3.startAnimation(gamescreen_button_define_animation_left);
        button2.startAnimation(gamescreen_button_define_animation_right);
        button4.startAnimation(gamescreen_button_define_animation_right);

        textofquestion.startAnimation(gamescreen_button_define_animation_left);



        int randomQuestion = rnd.nextInt(questionList.size());;


        boolean section;
        if (questionnumber>=questionList.size()){
            section = true;

        }
        else{
            section = false;

        }
        while (true){

            askedQuestion =questionList.get(randomQuestion);

            if(questionList.get(randomQuestion).getIsAsked()==section){

                askedQuestion=questionList.get(randomQuestion);
                break;
            }
            else{
                randomQuestion=rnd.nextInt(questionList.size());
            }

        }




        String que = String.valueOf(questionnumber+1)+ "/" + String.valueOf(questionList.size()*2);
        numberofquestion.setText(que);
        if (!section){
            textofquestion.setText("Which Brand is This Car?");
        }
        else{
            textofquestion.setText("Which Model is This Car?");
        }
        live.setText(String.valueOf(userlive));
        score.setText(String.valueOf(userscore));






        imageofquestion.setImageResource(askedQuestion.getQuestionimage());

        imageofquestion.startAnimation(car_image_anim_end);



        int randomButton = rnd.nextInt(4)+1;

        ArrayList<Integer> addedButton = new ArrayList<>();
        addedButton.add(randomButton);

        if (randomButton==1){


            if (!section){
                button1.setText(askedQuestion.getButtonAnswerText());
            }
            else{
                button1.setText(askedQuestion.getModel());
            }

        }
        else if(randomButton==2){
            if (!section){
                button2.setText(askedQuestion.getButtonAnswerText());
            }
            else{
                button2.setText(askedQuestion.getModel());
            }
        }
        else if (randomButton==3){
            if (!section){
                button3.setText(askedQuestion.getButtonAnswerText());
            }
            else{
                button3.setText(askedQuestion.getModel());
            }
        }
        else if(randomButton==4) {
            if (!section){
                button4.setText(askedQuestion.getButtonAnswerText());
            }
            else{
                button4.setText(askedQuestion.getModel());
            }
        }

        ArrayList<String> addedBrand = new ArrayList<>();
        ArrayList<String> addedModel = new ArrayList<>();
        addedBrand.add(askedQuestion.getButtonAnswerText());
        addedModel.add(askedQuestion.getModel());




        for(int i=0;i<3;i++){

            Boolean flag = false;

            int randomBrand = rnd.nextInt(brandList.size());
            int randomModel = rnd.nextInt(askedQuestion.getModels().size());
            while (flag==false){

                randomButton = rnd.nextInt(4)+1;

                if(!addedButton.contains(randomButton)){

                    if (randomButton==1){

                        while (true){
                            if (!section){
                                if(addedBrand.contains(brandList.get(randomBrand))){
                                    randomBrand = rnd.nextInt(brandList.size());
                                }
                                else{
                                    button1.setText(brandList.get(randomBrand));
                                    addedBrand.add(brandList.get(randomBrand));
                                    addedButton.add(randomButton);
                                    flag=true;
                                    break;
                                }
                            }
                            else{
                                if (addedModel.contains(askedQuestion.getModels().get(randomModel))) {
                                    randomModel = rnd.nextInt(askedQuestion.getModels().size());
                                }
                                else{
                                    button1.setText(askedQuestion.getModels().get(randomModel));
                                    addedModel.add(askedQuestion.getModels().get(randomModel));
                                    addedButton.add(randomButton);
                                    flag=true;
                                    break;
                                }
                            }


                        }

                    }
                    else if(randomButton==2){
                        while (true){
                            if (!section){
                                if(addedBrand.contains(brandList.get(randomBrand))){
                                    randomBrand = rnd.nextInt(brandList.size());
                                }
                                else{
                                    button2.setText(brandList.get(randomBrand));
                                    addedBrand.add(brandList.get(randomBrand));
                                    addedButton.add(randomButton);
                                    flag=true;
                                    break;
                                }
                            }
                            else{
                                if (addedModel.contains(askedQuestion.getModels().get(randomModel))) {
                                    randomModel = rnd.nextInt(askedQuestion.getModels().size());
                                }
                                else{
                                    button2.setText(askedQuestion.getModels().get(randomModel));
                                    addedModel.add(askedQuestion.getModels().get(randomModel));
                                    addedButton.add(randomButton);
                                    flag=true;
                                    break;
                                }
                            }


                        }
                    }
                    else if (randomButton==3){
                        while (true){
                            if (!section) {
                                if(addedBrand.contains(brandList.get(randomBrand))){
                                    randomBrand = rnd.nextInt(brandList.size());
                                }
                                else{
                                    button3.setText(brandList.get(randomBrand));
                                    addedBrand.add(brandList.get(randomBrand));
                                    addedButton.add(randomButton);
                                    flag=true;
                                    break;
                                }
                            }
                            else{
                                if (addedModel.contains(askedQuestion.getModels().get(randomModel))) {
                                    randomModel = rnd.nextInt(askedQuestion.getModels().size());
                                }
                                else{
                                    button3.setText(askedQuestion.getModels().get(randomModel));
                                    addedModel.add(askedQuestion.getModels().get(randomModel));
                                    addedButton.add(randomButton);
                                    flag=true;
                                    break;
                                }
                            }


                        }
                    }
                    else if(randomButton==4) {
                        while (true){
                            if (!section){
                                if(addedBrand.contains(brandList.get(randomBrand))){
                                    randomBrand = rnd.nextInt(brandList.size());
                                }
                                else{
                                    button4.setText(brandList.get(randomBrand));
                                    addedBrand.add(brandList.get(randomBrand));
                                    addedButton.add(randomButton);
                                    flag=true;
                                    break;
                                }
                            }
                            else{
                                if (addedModel.contains(askedQuestion.getModels().get(randomModel))) {
                                    randomModel = rnd.nextInt(askedQuestion.getModels().size());
                                }
                                else{
                                    button4.setText(askedQuestion.getModels().get(randomModel));
                                    addedModel.add(askedQuestion.getModels().get(randomModel));
                                    addedButton.add(randomButton);
                                    flag=true;
                                    break;
                                }
                            }


                        }
                    }


                }




            }



        }


        basicQuestion=askedQuestion;

        e.putInt("questionnumber", questionnumber);
        e.commit();
        Gson gson = new Gson();
        String json = gson.toJson(questionList);
        e.putString("list", json);

        e.commit();


    }

    public void loadRew(){

        AdRequest adRequest2 = new AdRequest.Builder().build();
        RewardedAd.load(getApplicationContext(), "ca-app-pub-2267634847444711/1679251240", adRequest2
                , new RewardedAdLoadCallback(){
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        System.out.println("reklam yüklendi");
                        mRewardedAd = rewardedAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        System.out.println(loadAdError.getMessage());
                        mRewardedAd = null;
                    }
                });
    }

    public void click(){




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(button1.getAlpha()!=0){
                    clickedbutton =String.valueOf(button1.getText());
                    change(clickedbutton,1);
                }


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(button2.getAlpha()!=0){
                    clickedbutton =String.valueOf(button2.getText());
                    change(clickedbutton,2);
                }


            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(button3.getAlpha()!=0){
                    clickedbutton =String.valueOf(button3.getText());
                    change(clickedbutton,3);
                }


            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(button4.getAlpha()!=0){
                    clickedbutton =String.valueOf(button4.getText());
                    change(clickedbutton,4);
                }

            }
        });

        market_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragment bottomSheetDialog = BottomSheetFragment.newInstance();
                bottomSheetDialog.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });

        showadslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (mRewardedAd!=null){
                            mRewardedAd.show( GameScreen.this, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                                    userscore = userscore+5;

                                    e.putInt("YENİ",userscore);
                                    e.commit();

                                    score.setText(String.valueOf(userscore));

                                    loadRew();
                                    showadslayout.setAlpha(0);

                                }

                            });
                            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when ad is shown.
                                    Log.d("asas", "Ad was shown.");
                                    mRewardedAd = null;

                                    loadRew();
                                }


                            });
                        }


                    }
                });
            }
        });

        can_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameScreen.userscore>= 10){

                    GameScreen.userscore=GameScreen.userscore-10;
                    GameScreen.e.putInt("userscore",GameScreen.userscore);
                    GameScreen.e.commit();
                    GameScreen.userlive++;
                    GameScreen.e.putInt("userlive",GameScreen.userlive);
                    GameScreen.e.commit();
                    (GameScreen.score).setText(String.valueOf(GameScreen.userscore));
                    GameScreen.live.setText(String.valueOf(GameScreen.userlive));

                    Toast.makeText(getApplicationContext(),"HEALTH BUYED",Toast.LENGTH_SHORT).show();




                }
                else{

                    Toast.makeText(getApplicationContext(),"YOU DON'T HAVE ENOUGH MONEY",Toast.LENGTH_SHORT).show();

                }
            }
        });

        e.putInt("userscore",userscore);

        e.apply();
        e.commit();






    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(GameScreen.this,MainActivity.class));
        finish();

    }

    public void change(String clickedbut, int buttonnumber) {

        if (!clickedbut.equals("X")){
            if (clickedbut.equals(basicQuestion.getButtonAnswerText())||clickedbut.equals(basicQuestion.getModel())){
                if (questionnumber+1!=questionList.size()*2){
                    questionnumber++;
                    userscore++;
                    e.putInt("userscore", userscore);

                    e.commit();
                    int index = questionList.indexOf(basicQuestion);
                    questionList.get(index).setIsAsked(!questionList.get(index).getIsAsked());
                    changeQuestion();


                }
                else{
                    userscore++;
                    e.putInt("userscore", userscore);
                    e.commit();
                    winGame();
                }

                clickedbutton="";

            }
            else{
                if (userlive>0){

                    userlive--;
                    e.putInt("userlive", userlive);
                    e.commit();

                    live.setText(String.valueOf(userlive));


                    vibrator.vibrate(200);
                    if (buttonnumber==1){

                        button1.setAlpha(0);

                    }
                    else if (buttonnumber==2){

                        button2.setAlpha(0);
                    }
                    else if (buttonnumber==3){

                        button3.setAlpha(0);
                    }
                    else if (buttonnumber==4){

                        button4.setAlpha(0);
                    }

                }
                else{

                    gameOver();
                }

            }
        }
    }

    public void winGame(){
        e.putBoolean("continue", false);

        e.commit();

        dahiliOku();
        Score sc = new Score(sp.getString("playername",""),userscore);
        scores.add(sc);

        dahiliYaz();
        setContentView(R.layout.winscreen);
        winscoreicon= findViewById(R.id.winscoreicon);
        winscoreicon.startAnimation(gamescreen_button_define_animation_left);
        Button restart;
        TextView finalscore;
        restart = findViewById(R.id.restartbutton);
        finalscore = findViewById(R.id.finalscorewin);
        finalscore.setText(String.valueOf(userscore));

        finalscore.startAnimation(gamescreen_button_define_animation_right);

        Animation button_animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.main_start_button_animation);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3= new Intent(GameScreen.this,MainActivity.class);
                startActivity(intent3);
            }
        });

        highscorebutton = findViewById(R.id.highscorebutton);
        highscorebutton.setAnimation(button_animation);
        restart.setAnimation(button_animation);

        highscorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                highScoreFunction();
            }
        });


    }
    public void gameOver(){
        e.putBoolean("continue", false);
        e.commit();
        dahiliOku();
        Score sc = new Score(sp.getString("playername",""),userscore);
        scores.add(sc);
        dahiliYaz();
        setContentView(R.layout.gameover);
        playagainbutton = findViewById(R.id.playagainbutton);
        finalscore = findViewById(R.id.finalscore);
        finalscore.setText(String.valueOf(userscore));


        gameoverscoreicon = findViewById(R.id.gameoverscoreicon);



        gameoverscoreicon.startAnimation(gamescreen_button_define_animation_left);
        finalscore.startAnimation(gamescreen_button_define_animation_right);


        Animation button_animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.main_start_button_animation);

        playagainbutton.startAnimation(button_animation);

        playagainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Intent intent2= new Intent(GameScreen.this,MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });
        highscorebutton = findViewById(R.id.highsScoreGameOverButton);
        highscorebutton.startAnimation(button_animation);

        highscorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                highScoreFunction();
            }
        });


    }

    public void dahiliYaz(){

        try {
            FileOutputStream fos = openFileOutput("scores.txt",MODE_PRIVATE);
            OutputStreamWriter yazici = new OutputStreamWriter(fos);
            BufferedWriter bfw = new BufferedWriter(yazici);

            StringBuilder sb = new StringBuilder();




            for (int i =0;i< scores.size();i++){

                sb.append(scores.get(i).getName()+" "+scores.get(i).getPuan()+"\n");

            }

            bfw.write(sb.toString());

            bfw.flush();
            bfw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dahiliOku(){

        try {
            FileInputStream fis = openFileInput("scores.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String satir ="";

            while ((satir = buffer.readLine()) !=null){

                String [] linearray = satir.split(" ");

                Score sc = new Score(linearray[0],Integer.parseInt(linearray[1]));
                scores.add(sc);


            }

            buffer.close();




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void highScoreFunction(){

        setContentView(R.layout.highscore_layout);
        highscoreplayagain = findViewById(R.id.highscoreplayagainbutton);
        Animation buttonanimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.main_start_button_animation);
        highscoreplayagain.startAnimation(buttonanimation);
        TextView highScoreText = findViewById(R.id.highScoreText);

        StringBuilder sb = new StringBuilder();

        Collections.sort(scores, Score.ScoreComp);


        int counter=0;
        while (!scores.isEmpty() && counter<5 && counter<scores.size()){



            sb.append(scores.get(counter).getName()+" "+String.valueOf(scores.get(counter).getPuan()));
            counter++;
            if(counter<5 && counter<scores.size()){
                sb.append("\n");
            }

        }

        highScoreText.setText(sb.toString());
        highscoreplayagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3= new Intent(GameScreen.this,MainActivity.class);
                startActivity(intent3);
                finish();

            }
        });
    }








}
