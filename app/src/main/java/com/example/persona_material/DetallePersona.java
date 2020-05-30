package com.example.persona_material;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePersona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);
        ImageView foto;
        TextView cedula, nombre, apellido;
        Intent intent;
        Bundle bundle;

        foto = findViewById(R.id.imgFotoDetalle);
        cedula = findViewById(R.id.lblCedulaDetalle);
        nombre = findViewById(R.id.lblNombreDetalle);
        apellido = findViewById(R.id.lblApellidoDetalle);
        intent =getIntent();
        bundle = intent.getBundleExtra("datos");

        foto.setImageResource(bundle.getInt("foto"));
        cedula.setText(bundle.getString("cedula"));
        nombre.setText(bundle.getString("nombre"));
        apellido.setText(bundle.getString("apellido"));
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(DetallePersona.this, MainActivity.class);
        startActivity(i);
    }
}
