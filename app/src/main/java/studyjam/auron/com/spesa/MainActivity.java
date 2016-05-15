package studyjam.auron.com.spesa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import studyjam.auron.com.spesa.fragment.Fragment_CardList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, Fragment.instantiate(MainActivity.this, Fragment_CardList.class.getName()))
                .addToBackStack("Fragment_CardList")
                .commit();

    }


}
