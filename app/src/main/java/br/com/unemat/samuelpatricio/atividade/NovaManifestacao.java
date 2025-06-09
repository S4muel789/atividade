package br.com.unemat.samuelpatricio.atividade;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class NovaManifestacao extends AppCompatActivity {

    private static final String TAG = "NovaManifestacao";

    private ImageView btnBack, btnCopiarProtocolo;
    private TextInputEditText etDescricao, etJustificativa;
    private Button btnAvancar;
    private CardView cardProtocolo;
    private TextView tvNumeroProtocolo, tvDataProtocolo;

    private String tipoManifestacao;
    private String nivelSigilo;
    private String numeroProtocolo;
    private boolean protocoloGerado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate chamado");

        try {
            setContentView(R.layout.activity_nova_manifestacao);
            Log.d(TAG, "Layout carregado com sucesso");

            // Receber dados da intent
            receberDadosIntent();

            initComponents();
            setupListeners();

            // Mostrar toast de confirmação
            Toast.makeText(this, "Digite sua manifestação", Toast.LENGTH_SHORT).show();

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
            Log.d(TAG, "Dados recebidos - Tipo: " + tipoManifestacao + ", Nível: " + nivelSigilo);
        }
    }

    private void initComponents() {
        Log.d(TAG, "Inicializando componentes");

        btnBack = findViewById(R.id.btn_back);
        etDescricao = findViewById(R.id.et_descricao);
        etJustificativa = findViewById(R.id.et_justificativa);
        btnAvancar = findViewById(R.id.btn_avancar);
        cardProtocolo = findViewById(R.id.card_protocolo);
        tvNumeroProtocolo = findViewById(R.id.tv_numero_protocolo);
        tvDataProtocolo = findViewById(R.id.tv_data_protocolo);
        btnCopiarProtocolo = findViewById(R.id.btn_copiar_protocolo);

        // Verificar se todos os componentes foram encontrados
        if (btnBack == null) Log.e(TAG, "btnBack é null");
        if (etDescricao == null) Log.e(TAG, "etDescricao é null");
        if (etJustificativa == null) Log.e(TAG, "etJustificativa é null");
        if (btnAvancar == null) Log.e(TAG, "btnAvancar é null");
        if (cardProtocolo == null) Log.e(TAG, "cardProtocolo é null");

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

        // TextWatcher para o campo descrição
        if (etDescricao != null) {
            etDescricao.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Gerar protocolo quando começar a digitar
                    if (s.length() > 0 && !protocoloGerado) {
                        gerarProtocolo();
                    }
                    verificarCamposPreenchidos();
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        // TextWatcher para o campo justificativa
        if (etJustificativa != null) {
            etJustificativa.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    verificarCamposPreenchidos();
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        // Botão copiar protocolo
        if (btnCopiarProtocolo != null) {
            btnCopiarProtocolo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    copiarProtocolo();
                }
            });
        }

        // Botão avançar
        if (btnAvancar != null) {
            btnAvancar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Botão avançar clicado");

                    String descricao = etDescricao != null ? etDescricao.getText().toString().trim() : "";
                    String justificativa = etJustificativa != null ? etJustificativa.getText().toString().trim() : "";

                    Log.d(TAG, "Descrição: " + descricao);
                    Log.d(TAG, "Justificativa: " + justificativa);
                    Log.d(TAG, "Protocolo: " + numeroProtocolo);

                    if (!descricao.isEmpty()) {
                        try {
                            // Navegar para tela de município do fato
                            Intent intent = new Intent(NovaManifestacao.this, MunicipioFato.class);
                            intent.putExtra("tipo_manifestacao", tipoManifestacao);
                            intent.putExtra("nivel_sigilo", nivelSigilo);
                            intent.putExtra("descricao", descricao);
                            intent.putExtra("justificativa", justificativa);
                            intent.putExtra("numero_protocolo", numeroProtocolo);

                            Log.d(TAG, "Navegando para MunicipioFatoActivity");
                            Log.d(TAG, "Dados enviados - Tipo: " + tipoManifestacao + ", Nível: " + nivelSigilo + ", Protocolo: " + numeroProtocolo);

                            startActivity(intent);

                        } catch (Exception e) {
                            Log.e(TAG, "Erro ao navegar: " + e.getMessage());
                            e.printStackTrace();
                            Toast.makeText(NovaManifestacao.this,
                                    "Erro ao navegar: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(NovaManifestacao.this,
                                "Por favor, preencha a descrição da manifestação", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        Log.d(TAG, "Listeners configurados");
    }

    private void gerarProtocolo() {
        Log.d(TAG, "Gerando protocolo");

        try {
            // Gerar número do protocolo: AAAAMMDDHHMMSSXXX
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
            String dataHora = sdf.format(new Date());

            // Adicionar 3 dígitos aleatórios no final
            Random random = new Random();
            int numeroAleatorio = random.nextInt(1000);
            String sufixo = String.format("%03d", numeroAleatorio);

            numeroProtocolo = dataHora + sufixo;

            // Atualizar interface
            if (tvNumeroProtocolo != null) {
                tvNumeroProtocolo.setText(numeroProtocolo);
            }

            // Atualizar data/hora de geração
            if (tvDataProtocolo != null) {
                SimpleDateFormat sdfDisplay = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault());
                String dataFormatada = sdfDisplay.format(new Date());
                tvDataProtocolo.setText("Gerado em: " + dataFormatada);
            }

            // Mostrar card do protocolo
            if (cardProtocolo != null) {
                cardProtocolo.setVisibility(View.VISIBLE);
            }

            protocoloGerado = true;

            Log.d(TAG, "Protocolo gerado: " + numeroProtocolo);
            Toast.makeText(this, "Protocolo gerado: " + numeroProtocolo, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e(TAG, "Erro ao gerar protocolo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void copiarProtocolo() {
        if (numeroProtocolo != null && !numeroProtocolo.isEmpty()) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Protocolo", numeroProtocolo);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "Protocolo copiado: " + numeroProtocolo, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Protocolo copiado para clipboard: " + numeroProtocolo);
        }
    }

    private void verificarCamposPreenchidos() {
        if (etDescricao == null || btnAvancar == null) return;

        String descricao = etDescricao.getText().toString().trim();

        // Mostrar botão apenas se a descrição estiver preenchida
        // A justificativa é opcional
        if (!descricao.isEmpty()) {
            btnAvancar.setVisibility(View.VISIBLE);
            Log.d(TAG, "Botão avançar mostrado");
        } else {
            btnAvancar.setVisibility(View.GONE);
            Log.d(TAG, "Botão avançar ocultado");
        }
    }
}