package com.example.firebase_ogrenci;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebase_ogrenci.databinding.ActivityOgrenciBilgiBinding;
import com.example.firebase_ogrenci.databinding.ActivityOgrenciKayitBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class OgrenciKayitActivity extends AppCompatActivity {
    ActivityOgrenciKayitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOgrenciKayitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onClickKayitOl(View view) {

        String txtTcKimlik = binding.editTextKayitKimlikNo.getText().toString().trim();
        String txtEposta = binding.editTextKayitEPosta.getText().toString().trim();
        String txtParola = binding.editTextKayitPassword.getText().toString();
        String txtParolaTekrar = binding.editTextKayitRePassword.getText().toString();

        if(!txtEposta.isEmpty()){
            if(!txtParola.isEmpty()){
                if(!txtParolaTekrar.isEmpty()){
                    if(txtParola.equals(txtParola)){
                        // Firestore Kullanıcı kayıtları
                        FirebaseAuth.getInstance()
                                .createUserWithEmailAndPassword(txtEposta,txtParola)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        ///
                                        Toast.makeText(OgrenciKayitActivity.this, "Kayıt başarılı.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(OgrenciKayitActivity.this, "Kullanıcı OLUŞTURULAMADI.", Toast.LENGTH_LONG).show();
                                    }
                                });


                    } else Toast.makeText(this, "Parolalar uyuşmuyor.", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "Parola tekrar boş geçilemez", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(this, "Parola boş geçilemez.", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "E-Posta boş geçilemez", Toast.LENGTH_SHORT).show();

    }

    public void onClickGirisYap(View view) {
        finish();
        startActivity(new Intent(OgrenciKayitActivity.this, GirisActivity.class));
    }
}