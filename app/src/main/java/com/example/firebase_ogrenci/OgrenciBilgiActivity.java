package com.example.firebase_ogrenci;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebase_ogrenci.databinding.ActivityOgrenciBilgiBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OgrenciBilgiActivity extends AppCompatActivity {
    ActivityOgrenciBilgiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOgrenciBilgiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onClickKaydet(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String textprojeAdi = binding.editTextBilgiProjeAdi.getText().toString().trim();
        String textprojeAlani = binding.editTextBilgiProjeAlani.getText().toString();
        String textsonTeslimTarihi = binding.editBilgiTeslimTarihi.getText().toString();

        if (!textprojeAdi.isEmpty() && !textprojeAlani.isEmpty() && !textsonTeslimTarihi.isEmpty()) {

            String stringAlan = textprojeAlani;
            String stringTeslim = textsonTeslimTarihi;

            Map<String, Object> bilgi = new HashMap<>();
            bilgi.put("projeAdi", Objects.requireNonNull(textprojeAdi));
            bilgi.put("projeAlani", Objects.requireNonNull(textprojeAlani));
            bilgi.put("sonTeslimTarihi", Objects.requireNonNull(stringTeslim));

            db.collection("projeler")
                    .add(bilgi).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Toast.makeText(OgrenciBilgiActivity.this, "Ürün Başarıyla Eklendi", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(OgrenciBilgiActivity.this, MainActivity.class));
                        }
                    }).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            binding.textViewBilgiTxtDurum.setTextColor(Color.parseColor("#CC0000"));
                            binding.textViewBilgiTxtDurum.setText("Ürünler eklenirken bir hata oluştu");

                        }
                    });
        } else {
            binding.textViewBilgiTxtDurum.setTextColor(Color.parseColor("#CC0000"));
            binding.textViewBilgiTxtDurum.setText("Lütfen Boş Alanları Doldurunuz");
        }

    }
}