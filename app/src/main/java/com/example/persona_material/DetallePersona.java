package com.example.persona_material;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetallePersona extends AppCompatActivity {
    private Persona p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ImageView foto;
        TextView cedula, nombre, apellido;
        Intent intent;
        Bundle bundle;
        String ced, nom, apell, id;
        int fot;
        StorageReference storageReference;

        foto = findViewById(R.id.imgFotoDetalle);
        cedula = findViewById(R.id.lblCedulaDetalle);
        nombre = findViewById(R.id.lblNombreDetalle);
        apellido = findViewById(R.id.lblApellidoDetalle);

        intent =getIntent();
        bundle = intent.getBundleExtra("datos");


        fot = bundle.getInt("foto");
        id = bundle.getString("id");
        ced = bundle.getString("cedula");
        nom = bundle.getString("nombre");
        apell = bundle.getString("apellido");


        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });

        //foto.setImageResource(fot);
        cedula.setText(ced);
        nombre.setText(nom);
        apellido.setText(apell);

        p = new Persona(ced, nom, apell, 0, id);

    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(DetallePersona.this, MainActivity.class);
        startActivity(i);
    }


    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Persona");
        builder.setMessage("Estas seguro que desea eliminar a esta persona?");
        positivo = "Si";
        negativo = "No";

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                p.eliminar();
                onBackPressed();
            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
