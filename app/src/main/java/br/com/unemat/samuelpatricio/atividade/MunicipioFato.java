package br.com.unemat.samuelpatricio.atividade;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MunicipioFato extends AppCompatActivity {

    private static final String TAG = "MunicipioFato";

    private ImageView btnBack;
    private RadioGroup rgMunicipios;
    private RadioButton rbCaceres, rbSaoPaulo, rbRioJaneiro, rbBeloHorizonte, rbSalvador;
    private Button btnEnviar;

    // LinearLayouts clicáveis para cada cidade
    private LinearLayout layoutCaceres, layoutSaoPaulo, layoutRioJaneiro, layoutBeloHorizonte, layoutSalvador;

    private String municipioSelecionado = null;

    // Dados recebidos das telas anteriores
    private String tipoManifestacao;
    private String nivelSigilo;
    private String descricao;
    private String justificativa;
    private String numeroProtocolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate chamado");

        try {
            setContentView(R.layout.activity_municipio_fato);
            Log.d(TAG, "Layout carregado com sucesso");

            // Receber dados da intent
            receberDadosIntent();

            initComponents();
            setupListeners();

            Toast.makeText(this, "Selecione o município onde ocorreu o fato", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "Erro no onCreate: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "Erro ao carregar tela: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void receberDadosIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            tipoManifestacao = intent.getStringExtra("tipo_manifestacao");
            nivelSigilo = intent.getStringExtra("nivel_sigilo");
            descricao = intent.getStringExtra("descricao");
            justificativa = intent.getStringExtra("justificativa");
            numeroProtocolo = intent.getStringExtra("numero_protocolo");

            Log.d(TAG, "=== DADOS RECEBIDOS ===");
            Log.d(TAG, "Tipo: " + tipoManifestacao);
            Log.d(TAG, "Nível: " + nivelSigilo);
            Log.d(TAG, "Protocolo: " + numeroProtocolo);
            Log.d(TAG, "Descrição: " + (descricao != null ? descricao.substring(0, Math.min(50, descricao.length())) + "..." : "null"));
            Log.d(TAG, "Justificativa: " + (justificativa != null && !justificativa.isEmpty() ? justificativa.substring(0, Math.min(50, justificativa.length())) + "..." : "Não informada"));
        }
    }

    private void initComponents() {
        Log.d(TAG, "Inicializando componentes");

        btnBack = findViewById(R.id.btn_back);
        rgMunicipios = findViewById(R.id.rg_municipios);
        rbCaceres = findViewById(R.id.rb_caceres);
        rbSaoPaulo = findViewById(R.id.rb_sao_paulo);
        rbRioJaneiro = findViewById(R.id.rb_rio_janeiro);
        rbBeloHorizonte = findViewById(R.id.rb_belo_horizonte);
        rbSalvador = findViewById(R.id.rb_salvador);
        btnEnviar = findViewById(R.id.btn_enviar);

        // Encontrar os LinearLayouts clicáveis
        layoutCaceres = findViewById(R.id.layout_caceres);
        layoutSaoPaulo = findViewById(R.id.layout_sao_paulo);
        layoutRioJaneiro = findViewById(R.id.layout_rio_janeiro);
        layoutBeloHorizonte = findViewById(R.id.layout_belo_horizonte);
        layoutSalvador = findViewById(R.id.layout_salvador);

        // Verificar se todos os componentes foram encontrados
        if (btnBack == null) Log.e(TAG, "btnBack é null");
        if (rgMunicipios == null) Log.e(TAG, "rgMunicipios é null");
        if (rbCaceres == null) Log.e(TAG, "rbCaceres é null");
        if (btnEnviar == null) Log.e(TAG, "btnEnviar é null");
        else Log.d(TAG, "btnEnviar encontrado com sucesso");

        // Garantir que o botão esteja inicialmente oculto
        if (btnEnviar != null) {
            btnEnviar.setVisibility(View.GONE);
            Log.d(TAG, "Botão enviar definido como GONE inicialmente");
        }

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

        // Listeners para os layouts clicáveis (toda a linha é clicável)
        if (layoutCaceres != null) {
            layoutCaceres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("Cáceres", rbCaceres);
                }
            });
        }

        if (layoutSaoPaulo != null) {
            layoutSaoPaulo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("São Paulo", rbSaoPaulo);
                }
            });
        }

        if (layoutRioJaneiro != null) {
            layoutRioJaneiro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("Rio de Janeiro", rbRioJaneiro);
                }
            });
        }

        if (layoutBeloHorizonte != null) {
            layoutBeloHorizonte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("Belo Horizonte", rbBeloHorizonte);
                }
            });
        }

        if (layoutSalvador != null) {
            layoutSalvador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("Salvador", rbSalvador);
                }
            });
        }

        // Listeners individuais para os RadioButtons também
        if (rbCaceres != null) {
            rbCaceres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("Cáceres", rbCaceres);
                }
            });
        }

        if (rbSaoPaulo != null) {
            rbSaoPaulo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("São Paulo", rbSaoPaulo);
                }
            });
        }

        if (rbRioJaneiro != null) {
            rbRioJaneiro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("Rio de Janeiro", rbRioJaneiro);
                }
            });
        }

        if (rbBeloHorizonte != null) {
            rbBeloHorizonte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("Belo Horizonte", rbBeloHorizonte);
                }
            });
        }

        if (rbSalvador != null) {
            rbSalvador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecionarMunicipio("Salvador", rbSalvador);
                }
            });
        }

        // Botão enviar
        if (btnEnviar != null) {
            btnEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Botão enviar clicado");
                    enviarManifestacao();
                }
            });
        }

        Log.d(TAG, "Listeners configurados");
    }

    private void selecionarMunicipio(String nomeMunicipio, RadioButton radioButton) {
        Log.d(TAG, "Selecionando município: " + nomeMunicipio);

        // Se o mesmo município já está selecionado, desmarcar
        if (nomeMunicipio.equals(municipioSelecionado)) {
            Log.d(TAG, "Desmarcando município: " + nomeMunicipio);
            municipioSelecionado = null;
            limparSelecoes();
            ocultarBotaoEnviar();
            Toast.makeText(this, "Seleção removida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Limpar todas as seleções primeiro
        limparSelecoes();

        // Selecionar o novo município
        municipioSelecionado = nomeMunicipio;
        if (radioButton != null) {
            radioButton.setChecked(true);
        }

        // Mostrar botão enviar
        mostrarBotaoEnviar();

        Toast.makeText(this, "Município selecionado: " + nomeMunicipio, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Município selecionado com sucesso: " + nomeMunicipio);
    }

    private void limparSelecoes() {
        Log.d(TAG, "Limpando todas as seleções");

        if (rbCaceres != null) rbCaceres.setChecked(false);
        if (rbSaoPaulo != null) rbSaoPaulo.setChecked(false);
        if (rbRioJaneiro != null) rbRioJaneiro.setChecked(false);
        if (rbBeloHorizonte != null) rbBeloHorizonte.setChecked(false);
        if (rbSalvador != null) rbSalvador.setChecked(false);
    }

    private void mostrarBotaoEnviar() {
        if (btnEnviar != null) {
            Log.d(TAG, "Mostrando botão enviar");
            btnEnviar.setVisibility(View.VISIBLE);
        } else {
            Log.e(TAG, "btnEnviar é null, não é possível mostrar");
        }
    }

    private void ocultarBotaoEnviar() {
        if (btnEnviar != null) {
            Log.d(TAG, "Ocultando botão enviar");
            btnEnviar.setVisibility(View.GONE);
        }
    }

    private void enviarManifestacao() {
        Log.d(TAG, "=== INICIANDO ENVIO DA MANIFESTAÇÃO ===");

        if (municipioSelecionado == null) {
            Toast.makeText(this, "Selecione um município", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Log completo dos dados que serão enviados
            Log.d(TAG, "=== DADOS COMPLETOS DA MANIFESTAÇÃO ===");
            Log.d(TAG, "Protocolo: " + numeroProtocolo);
            Log.d(TAG, "Tipo de Manifestação: " + tipoManifestacao);
            Log.d(TAG, "Nível de Sigilo: " + nivelSigilo);
            Log.d(TAG, "Descrição: " + descricao);
            Log.d(TAG, "Justificativa: " + (justificativa != null && !justificativa.isEmpty() ? justificativa : "Não informada"));
            Log.d(TAG, "Município do Fato: " + municipioSelecionado);
            Log.d(TAG, "=== FIM DOS DADOS ===");

            // Mostrar dialog de sucesso
            mostrarDialogSucesso();

        } catch (Exception e) {
            Log.e(TAG, "Erro ao enviar manifestação: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "Erro ao enviar manifestação: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void mostrarDialogSucesso() {
        Log.d(TAG, "Mostrando dialog de sucesso");

        String mensagem = "Nova manifestação enviada\n\nProtocolo: " + numeroProtocolo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sucesso!")
                .setMessage(mensagem)
                .setPositiveButton("OK", (dialog, which) -> {
                    Log.d(TAG, "Dialog de sucesso confirmado pelo usuário");
                    dialog.dismiss();

                    // Voltar para a tela inicial
                    Intent intent = new Intent(MunicipioFato.this, Manifestacao.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        Log.d(TAG, "Dialog de sucesso exibido");
    }
}