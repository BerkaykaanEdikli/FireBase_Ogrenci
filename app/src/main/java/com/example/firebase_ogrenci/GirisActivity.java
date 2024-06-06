package com.example.firebase_ogrenci;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebase_ogrenci.databinding.ActivityGirisBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GirisActivity extends AppCompatActivity {
    ActivityGirisBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGirisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onClickGirisYap(View view) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String textEposta = binding.editTextGirisEPosta.getText().toString().trim();
        String textParola = binding.editTextGirisPassword.getText().toString();
        if (!textEposta.isEmpty()) {
            if (!textParola.isEmpty()) {

                firebaseAuth
                        .signInWithEmailAndPassword(textEposta, textParola)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                Toast.makeText(GirisActivity.this, "Başarı ile giriş yaptınız.", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(GirisActivity.this, MainActivity.class));

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                binding.textViewGirisTxtDurum.setTextColor(Color.parseColor("#CC0000"));
                                binding.textViewGirisTxtDurum.setText("Giriş BAŞARISIZ\n" + e.getLocalizedMessage());

                            }
                        });


            } else binding.textViewGirisTxtDurum.setText("Şifre boş olamaz");
        } else binding.textViewGirisTxtDurum.setText("E-posta boş olamaz");
    }

    public void onClickSifremiUnuttum(View view) {
        String eposta = binding.editTextGirisEPosta.getText().toString().trim();
        if (eposta.isEmpty()) {
            binding.textViewGirisTxtDurum.setTextColor(Color.parseColor("#CC0000"));
            binding.textViewGirisTxtDurum.setText("E-posta boş olamaz");

        }
        else
        {
            FirebaseAuth.getInstance()
                    .sendPasswordResetEmail(eposta)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            binding.textViewGirisTxtDurum.setTextColor(Color.parseColor("#2e7d32"));
                            binding.textViewGirisTxtDurum.setText("Şifre Sıfırlama E-Postası gönderildi");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            binding.textViewGirisTxtDurum.setTextColor(Color.parseColor("#1E88E5"));
                            binding.textViewGirisTxtDurum.setText("Hatırlatma e-postası gönderilemedi\n" + e.getLocalizedMessage());

                        }
                    });
        }
    }

    public void onClickKayitOl(View view) {
        finish();
        startActivity(new Intent(GirisActivity.this, OgrenciKayitActivity.class));
    }
}