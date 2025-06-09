package br.com.unemat.samuelpatricio.atividade;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Manifestacao extends AppCompatActivity {

    private LinearLayout navInicio, navManifestacoes, navConsultar, navSobre;
    private FloatingActionButton fabAddManifestacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifestacao);

        // Inicializar componentes
        initComponents();

        // Configurar listeners
        setupListeners();
    }

    private void initComponents() {
        // Navegação
        navInicio = findViewById(R.id.nav_inicio);
        navManifestacoes = findViewById(R.id.nav_manifestacoes);
        navConsultar = findViewById(R.id.nav_consultar);
        navSobre = findViewById(R.id.nav_sobre);

        // FAB
        fabAddManifestacao = findViewById(R.id.fab_add_manifestacao);
    }

    private void setupListeners() {
        // Navegação
        navInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Voltar para tela inicial
                Intent intent = new Intent(Manifestacao.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        navManifestacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Já estamos na tela de manifestações
                Toast.makeText(Manifestacao.this, "Manifestações", Toast.LENGTH_SHORT).show();
            }
        });

        navConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para tela de consulta
                Toast.makeText(Manifestacao.this, "Consultar", Toast.LENGTH_SHORT).show();
                // Intent para ConsultarActivity seria implementado aqui
            }
        });

        navSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para tela sobre
                Toast.makeText(Manifestacao.this, "Sobre", Toast.LENGTH_SHORT).show();
                // Intent para SobreActivity seria implementado aqui
            }
        });

        // FAB para adicionar manifestação - atualizado
        fabAddManifestacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para tela de tipo de manifestação
                Intent intent = new Intent(Manifestacao.this, TipoManifestacao.class);
                startActivity(intent);
            }
        });
    }
}
