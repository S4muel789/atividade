package br.com.unemat.samuelpatricio.atividade;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private LinearLayout cardManifestacoes;
    private LinearLayout cardConsultar;
    private LinearLayout cardSobre;

    // Navegação
    private LinearLayout navInicio;
    private LinearLayout navManifestacoes;
    private LinearLayout navConsultar;
    private LinearLayout navSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Iniciando MainActivity");

        initComponents();
        setupListeners();
    }

    private void initComponents() {
        Log.d(TAG, "initComponents: Inicializando componentes");

        cardManifestacoes = findViewById(R.id.nav_manifestacoes);
        cardConsultar = findViewById(R.id.nav_consultar);
        cardSobre = findViewById(R.id.nav_sobre);

        // Navegação
        navInicio = findViewById(R.id.nav_inicio);
        navManifestacoes = findViewById(R.id.nav_manifestacoes);
        navConsultar = findViewById(R.id.nav_consultar);
        navSobre = findViewById(R.id.nav_sobre);
    }

    private void setupListeners() {
        Log.d(TAG, "setupListeners: Configurando listeners");

        // Cards
        cardManifestacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Card Manifestações clicado");
                Intent intent = new Intent(MainActivity.this, Manifestacao.class);
                startActivity(intent);
            }
        });

        cardConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Card Consultar clicado");
                Intent intent = new Intent(MainActivity.this, Consultar.class);
                startActivity(intent);
            }
        });

        cardSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Card Sobre clicado");
                Toast.makeText(MainActivity.this, "Funcionalidade em desenvolvimento", Toast.LENGTH_SHORT).show();
            }
        });

        // Navegação
        // Não precisamos de listener para navInicio pois já estamos nessa tela

        navManifestacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Nav Manifestações clicado");
                Intent intent = new Intent(MainActivity.this, Manifestacao.class);
                startActivity(intent);
            }
        });

        navConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Nav Consultar clicado");
                Intent intent = new Intent(MainActivity.this, Consultar.class); // Correto
                startActivity(intent);
            }
        });

        navSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Nav Sobre clicado");
                Intent intent = new Intent(MainActivity.this, Sobre.class);
                startActivity(intent);
            }
        });
    }
}
