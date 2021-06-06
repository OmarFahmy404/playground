package com.example.playground;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getSupportFragmentManager().beginTransaction().replace(R.id.themainx,new recyclerviwefragment()).commit();
getSupportFragmentManager().beginTransaction().replace(R.id.themainx,new recyclerviwefragment()).commit();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(this,ADD_GROUND.class);
                startActivity(intent);


                break;
            case R.id.search:



        }

        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

/*        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        MenuItem searchfliter =menu.findItem(R.id.search);
        SearchView searchView= (SearchView) searchfliter.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



*/




        return super.onCreateOptionsMenu(menu);

    }
}
