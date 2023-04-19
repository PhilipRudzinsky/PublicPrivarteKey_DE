package com.example.kluczejakies;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button generujLiczbyButton;
    private Button generujKluczeButton;
    private TextView wynikTextView;
    private TextView wynikTextView1;
    private TextView wynikTextView2;
    private TextView wynikTextView3;

    private int p, q, n, k, e, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generujLiczbyButton = findViewById(R.id.button);
        generujKluczeButton = findViewById(R.id.button3);
        wynikTextView = findViewById(R.id.p);
        wynikTextView1 = findViewById(R.id.q);
        wynikTextView2 = findViewById(R.id.publiczny);
        wynikTextView3 = findViewById(R.id.prywatny);

        generujLiczbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = generuj();
                q = generuj();
                while (p == q) {
                    q = generuj();
                }
                n = p * q;
                String wynik= "P:" + p;
                String wynik1= "Q:" + q;
                wynikTextView.setText(wynik);
                wynikTextView1.setText(wynik1);
            }
        });

        generujKluczeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k = (p - 1) * (q - 1);
                e = k + 2;
                while (nwd(e, k) != 1 && !czyPierwsza(e)) {
                    e++;
                }
                d = 1;
                while (e * d % k != 1) {
                    d++;
                }
                String wynik2 = "Klucz publiczny:" + e;
                String wynik3 = "Klucz prywatny:" + d;
                wynikTextView2.setText(wynik2);
                wynikTextView3.setText(wynik3);
            }
        });
    }

    private static boolean czyPierwsza(int x) {
        if (x < 0) return false;
        int i = 2;
        while (i * i < x) {
            if (x % i == 0) return false;
            i++;
        }
        return true;
    }

    private static int rand(int start, int end) {
        if (start > end) throw new IllegalArgumentException("Illegal Argument");
        return new Random().nextInt(end - start + 1) + start;
    }

    private static int generuj() {
        int pierwsza = rand(0, 10000);
        while (!czyPierwsza(pierwsza)) {
            pierwsza++;
        }
        return pierwsza;
    }

    private static int nwd(int ax, int bx) {
        int temp = 0;
        int a = ax;
        int b = bx;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}