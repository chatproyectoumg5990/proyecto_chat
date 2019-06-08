package com.chat.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.google.firebase.auth.FirebaseAuth;

import com.chat.app.Persistencia.UsuarioDAO;
import com.chat.app.R;

public class MenuActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button btnChatPublic;
    ImageButton floatButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        //codigo de toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //codigo de boton flotante
        floatButton =  findViewById(R.id.imageButton);

        //codigo de Boton invocador de activity
        btnChatPublic = findViewById(R.id.btnVerUsuarios);
        btnChatPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MensajeriaActivity_ind.class);
                startActivity(intent);
            }
        });
        //codigo de boton flotante
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,VerUsuariosActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Nueva Conversacion", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void returnLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(UsuarioDAO.getInstancia().isUsuarioLogeado()){
            //el usuario esta logeado y decimos que haga:
        }else{
            returnLogin();
        }
    }
    //codigo de toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    //codigo de toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.item1) {
            Toast.makeText(this, "Sesion Cerrada Correctamente", Toast.LENGTH_SHORT).show();
            //estas 2 lineas cierran la sesion
            FirebaseAuth.getInstance().signOut();
            returnLogin();
        }
        return super.onOptionsItemSelected(item);
    }

}
