package methal.akel.carquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Start_Screen_Fragment extends Fragment {


    private Button mainstartbuttton,mainhst;
    private Animation start_button_animation;
    static EditText player_name_edittext;
    SharedPreferences sp;
    SharedPreferences.Editor e;

    private AdView banner;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.start_screen_fragment,container,false);


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        MobileAds.initialize(getView().getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        banner = getView().findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);





        define();
        click();




    }



    @SuppressLint("MissingPermission")
    public void define(){
        mainstartbuttton = (Button)getView().findViewById(R.id.mainstartbutton);
        start_button_animation = AnimationUtils.loadAnimation(getContext(),R.anim.main_start_button_animation);
        player_name_edittext = getView().findViewById(R.id.player_name_edittext);



        sp = getActivity().getSharedPreferences("TABLO", Context.MODE_PRIVATE);

        e = sp.edit();



        player_name_edittext.setHint(sp.getString("playername","PLAYER NAME"));










        mainstartbuttton.setAnimation(start_button_animation);

    }

    public void click(){

        mainstartbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                if(player_name_edittext.getText().toString().equals("")){
                    e.putString("playername",player_name_edittext.getHint().toString());
                }
                else{
                    e.putString("playername",player_name_edittext.getText().toString());
                }


                e.commit();

                startGame();

                getActivity().finish();


            }
        });



    }





    public void startGame() {
        Intent intent = new Intent(getActivity(), GameScreen.class);
        startActivity(intent);
    }

}
