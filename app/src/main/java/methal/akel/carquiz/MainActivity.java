package methal.akel.carquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Fragment fragment;
    TextView resetdata;
    ImageView meteinsta, metelinkedin, halilinsta, halillinkedin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,0,0);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        drawer.addDrawerListener(toggle);

        toggle.syncState();

        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);

        navigationView.setNavigationItemSelectedListener(this);

        fragment = new Start_Screen_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentTutucu,fragment).commit();





    }



    public void showAlertDialogContactUs() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);



        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.contactus_popup, null);
        builder.setView(customLayout);

        // add a button


        metelinkedin = customLayout.findViewById(R.id.metelinkedin);

        halillinkedin = customLayout.findViewById(R.id.halillinledin);

        metelinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri linkimiz=Uri.parse("https://www.linkedin.com/in/metehan-alper-elmas-153224204/");
                Intent intentimiz =new Intent(Intent.ACTION_VIEW,linkimiz);
                startActivity(intentimiz);
            }
        });


        halillinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri linkimiz=Uri.parse("https://www.linkedin.com/in/halil-akpinar/");
                Intent intentimiz =new Intent(Intent.ACTION_VIEW,linkimiz);
                startActivity(intentimiz);
            }
        });





        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();




    }
    public void showAlertDialogSettings(){
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.setting_popup, null);
        builder.setView(customLayout);

        // add a button


        resetdata = customLayout.findViewById(R.id.resetdata);
        resetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File yol = getFilesDir();

                File file = new File(yol,"scores.txt");

                file.delete();
                Toast.makeText(getApplicationContext(),"HIGH SCORE TABLE DELETED",Toast.LENGTH_SHORT).show();
            }
        });







        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contactus:
                showAlertDialogContactUs();
                break;

            case R.id.settings:
                showAlertDialogSettings();
                break;
        }


        return true;
    }
}