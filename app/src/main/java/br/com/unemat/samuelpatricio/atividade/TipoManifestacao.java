package br.com.unemat.samuelpatricio.atividade;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class TipoManifestacao extends AppCompatActivity {

    private static final String TAG = "TipoManifestacao";

    private ImageView btnBack;
    private TextView tvDescription;
    private RadioButton rbElogio, rbSugestao, rbReclamacao, rbInformacao, rbDenuncia;
    private Button btnAvancar;

    private String tipoSelecionado = "Elogio"; // Padrão

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate chamado");

        try {
            setContentView(R.layout.activity_tipo_manifestacao);
            Log.d(TAG, "Layout carregado com sucesso");

            initComponents();
            setupListeners();

        } catch (Exception e) {
            Log.e(TAG, "Erro no onCreate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initComponents() {
        Log.d(TAG, "Inicializando componentes");

        btnBack = findViewById(R.id.btn_back);
        tvDescription = findViewById(R.id.tv_description);
        rbElogio = findViewById(R.id.rb_elogio);
        rbSugestao = findViewById(R.id.rb_sugestao);
        rbReclamacao = findViewById(R.id.rb_reclamacao);
        rbInformacao = findViewById(R.id.rb_informacao);
        rbDenuncia = findViewById(R.id.rb_denuncia);
        btnAvancar = findViewById(R.id.btn_avancar);

        // Verificar se todos os componentes foram encontrados
        if (btnBack == null) Log.e(TAG, "btnBack é null");
        if (tvDescription == null) Log.e(TAG, "tvDescription é null");
        if (rbElogio == null) Log.e(TAG, "rbElogio é null");
        if (btnAvancar == null) Log.e(TAG, "btnAvancar é null");

        // Definir descrição inicial
        updateDescription("Elogio");
        rbElogio.setChecked(true);

        Log.d(TAG, "Componentes inicializados");
    }

    private void setupListeners() {
        Log.d(TAG, "Configurando listeners");

        // Botão voltar
        if (btnBack != null) {
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Botão voltar clicado");
                    finish();
                }
            });
        }

        // RadioButtons
        if (rbElogio != null) {
            rbElogio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Elogio selecionado");
                    selectOption("Elogio");
                }
            });
        }

        if (rbSugestao != null) {
            rbSugestao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Sugestão selecionada");
                    selectOption("Sugestão e Comentário");
                }
            });
        }

        if (rbReclamacao != null) {
            rbReclamacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Reclamação selecionada");
                    selectOption("Reclamação");
                }
            });
        }

        if (rbInformacao != null) {
            rbInformacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Informação selecionada");
                    selectOption("Informação");
                }
            });
        }

        if (rbDenuncia != null) {
            rbDenuncia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Denúncia selecionada");
                    selectOption("Denúncia");
                }
            });
        }

        // Botão avançar
        if (btnAvancar != null) {
            btnAvancar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Botão avançar clicado");
                    Log.d(TAG, "Tipo selecionado: " + tipoSelecionado);

                    try {
                        // Mostrar toast de debug
                        Toast.makeText(TipoManifestacao.this,
                                "Tentando navegar para NivelSigiloActivity", Toast.LENGTH_LONG).show();

                        // Tentar navegar
                        Intent intent = new Intent(TipoManifestacao.this, NivelSigilo.class);
                        intent.putExtra("tipo_manifestacao", tipoSelecionado);

                        Log.d(TAG, "Intent criado, iniciando activity");
                        startActivity(intent);
                        Log.d(TAG, "startActivity chamado com sucesso");

                    } catch (Exception e) {
                        Log.e(TAG, "Erro ao navegar: " + e.getMessage());
                        e.printStackTrace();
                        Toast.makeText(TipoManifestacao.this,
                                "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        Log.d(TAG, "Listeners configurados");
    }

    private void selectOption(String tipo) {
        Log.d(TAG, "Selecionando opção: " + tipo);

        // Limpar todas as seleções
        if (rbElogio != null) rbElogio.setChecked(false);
        if (rbSugestao != null) rbSugestao.setChecked(false);
        if (rbReclamacao != null) rbReclamacao.setChecked(false);
        if (rbInformacao != null) rbInformacao.setChecked(false);
        if (rbDenuncia != null) rbDenuncia.setChecked(false);

        // Selecionar o correto
        tipoSelecionado = tipo;
        switch (tipo) {
            case "Elogio":
                if (rbElogio != null) rbElogio.setChecked(true);
                break;
            case "Sugestão e Comentário":
                if (rbSugestao != null) rbSugestao.setChecked(true);
                break;
            case "Reclamação":
                if (rbReclamacao != null) rbReclamacao.setChecked(true);
                break;
            case "Informação":
                if (rbInformacao != null) rbInformacao.setChecked(true);
                break;
            case "Denúncia":
                if (rbDenuncia != null) rbDenuncia.setChecked(true);
                break;
        }

        updateDescription(tipo);
    }

    private void updateDescription(String tipo) {
        if (tvDescription == null) return;

        String descricao = "";
        switch (tipo) {
            case "Elogio":
                descricao = "Elogio: Satisfação ou reconhecimento da qualidade dos serviços prestados, dos atos ou procedimentos dos executados pelo Ministério Público, pelos membros e pelos seus serviços auxiliares.";
                break;
            case "Sugestão e Comentário":
                descricao = "Sugestão e Comentário: Proposta de melhoria e aprimoramento dos serviços do Ministério Público.";
                break;
            case "Reclamação":
                descricao = "Reclamação: Manifestação de insatisfação relativa aos serviços prestados pelo Ministério Público.";
                break;
            case "Informação":
                descricao = "Informação: Pedidos de informação sobre procedimentos, processos ou atividades do Ministério Público.";
                break;
            case "Denúncia":
                descricao = "Denúncia: Comunicação de prática de ato ilícito ou irregular no âmbito do Ministério Público.";
                break;
        }

        tvDescription.setText(descricao);
        Log.d(TAG, "Descrição atualizada para: " + tipo);
    }
}