package com.example.persona_material;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class AgregarPersonas extends AppCompatActivity {
    private ArrayList<Integer> fotos;
    private EditText cedula, nombre, apellido;
    private StorageReference storageReference;
    private Uri uri;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_personas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cedula = findViewById(R.id.txtCedula);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        foto = findViewById(R.id.imgFotoSeleccionada);
        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);

        storageReference = FirebaseStorage.getInstance().getReference();


    }

    public void guardar(View v){
        String ced, nom, apell, id;
        int foto;
        Persona persona;
        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);//para qu el teclado se baje
        ced = cedula.getText().toString();
        nom = nombre.getText().toString();
        apell = apellido.getText().toString();
        foto = foto_aleatoria();
        id = Datos.getId();
        persona = new Persona(ced, nom, apell, foto, id);
        persona.guardar();
        subir_fotos(id);
        limpiar();
        imp.hideSoftInputFromWindow(cedula.getWindowToken(), 0);//para qu el teclado se baje
        Snackbar.make(v, getString(R.string.mensaje_guardado_correcto), Snackbar.LENGTH_LONG).show();
    }

    public void subir_fotos(String id){
        StorageReference child = storageReference.child(id);
        //Uri uri = Uri.parse("android.resourse://"+R.class.getPackage().getName()+"/"+foto);
        UploadTask uploadTask = child.putFile(uri);
    }



    public void limpiar(View V){
        limpiar();
    }

    public int foto_aleatoria(){
        int foto_seleccionada;
        Random r = new Random();
        foto_seleccionada = r. nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }

    public void limpiar(){
        cedula.setText("");
        nombre.setText("");
        apellido.setText("");
        cedula.requestFocus();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarPersonas.this, MainActivity.class);
        startActivity(i);
    }

    public void seleccionar_foto(View v){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Selecciona Foto"),1);
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data){
        super.onActivityResult(requesCode, resultCode, data);
        if (requesCode == 1){
            uri = data.getData();
            if (uri != null){
                foto.setImageURI(uri);
            }
        }
    }
}
