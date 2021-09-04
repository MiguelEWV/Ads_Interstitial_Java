package com.example.ads_interstitial_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;


public class MainActivity extends AppCompatActivity {

    //Creacion de variables
    private InterstitialAd mInterstitialAd;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//Inicializador
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
//-----------------------------------------------------------------------------------------

// Carga del private class void

//----------------------------------------------------------------------------------------- -

// Identificacion del boton e importacion del layount
        button = findViewById(R.id.adbutton);
//--------------------------------------------------------------------------------------------

// Funcion del boton al clicar
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null)
                { setAds();
//MOstrar la ads en esta actividad
                    mInterstitialAd.show(MainActivity.this);
//gestionar eventos relacionados con la visualizacion de la ads
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
//Avanzar para la proxima diapositiva mismo perdida la ads
                            startActivity(new Intent(MainActivity.this, MainActivity2.class));
// Called when fullscreen content is dismissed.
                            Toast.makeText(MainActivity.this, "Opcion 1", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                    else{ setAds();
//Si no funcionar la ads mismo asi ir para la siguiente diapo
                            startActivity(new Intent(MainActivity.this, MainActivity2.class));
//Mostrar un log con mensage en caso de error de la ads
                    Toast.makeText(MainActivity.this, "Opcion 2", Toast.LENGTH_SHORT).show();
                        }


                }

        });
}
//--------------------------------------------------------------------------------------------------------------


        //Creacion de una clase publica void para
        // cargar un anuncio intersticial, llama al método InterstitialAd estático load()
        // y transfiere una InterstitialAdLoadCallback para recibir el
        // anuncio cargado o cualquier error.
        // Ten en cuenta que, al igual que otras retrollamadas de carga de formato,
        // InterstitialAdLoadCallback utiliza LoadAdError para proporcionar detalles
        // de los errores de fidelidad más alta.

    private void setAds () {
        AdRequest adRequest = new AdRequest.Builder().build();
//codigo pasado sin estring que contiene el adUnitID:: InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest)
        InterstitialAd.load(this, getString(R.string.id_del_ads), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Toast.makeText(MainActivity.this, "Dentro Ad 1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
                Toast.makeText(MainActivity.this, "Dentro Ad 2", Toast.LENGTH_SHORT).show();
            }
        });


    }
}