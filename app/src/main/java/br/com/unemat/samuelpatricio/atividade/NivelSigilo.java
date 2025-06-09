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

public class NivelSigilo extends AppCompatActivity {

    private static final String TAG = "NivelSigilo";

    private ImageView btnBack;
    private TextView tvDescription;
    private RadioButton rbSemSigilo, rbSigiloso, rbAnonimo;
    private Button btnAvancar;

    private String tipoManifestacao;
    private String nivelSelecionado = "Sem Sigilo"; // Padrão

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate chamado");

        try {
            setContentView(R.layout.activity_nivel_sigilo);
            Log.d(TAG, "Layout carregado com sucesso");

            // Receber dados da intent
            Intent intent = getIntent();
            if (intent != null) {
                tipoManifestacao = intent.getStringExtra("tipo_manifestacao");
                Log.d(TAG, "Tipo de manifestação recebido: " + tipoManifestacao);
            }

            initComponents();
            setupListeners();

            // Mostrar toast de confirmação
            Toast.makeText(this, "Tela Nível de Sigilo carregada!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "Erro no onCreate: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "Erro ao carregar tela: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initComponents() {
        Log.d(TAG, "Inicializando componentes");

        btnBack = findViewById(R.id.btn_back);
        tvDescription = findViewById(R.id.tv_description);
        rbSemSigilo = findViewById(R.id.rb_sem_sigilo);
        rbSigiloso = findViewById(R.id.rb_sigiloso);
        rbAnonimo = findViewById(R.id.rb_anonimo);
        btnAvancar = findViewById(R.id.btn_avancar);

        // Verificar se todos os componentes foram encontrados
        if (btnBack == null) Log.e(TAG, "btnBack é null");
        if (tvDescription == null) Log.e(TAG, "tvDescription é null");
        if (rbSemSigilo == null) Log.e(TAG, "rbSemSigilo é null");
        if (btnAvancar == null) Log.e(TAG, "btnAvancar é null");

        // Definir descrição inicial
        updateDescription("Sem Sigilo");
        if (rbSemSigilo != null) rbSemSigilo.setChecked(true);

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
        if (rbSemSigilo != null) {
            rbSemSigilo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption("Sem Sigilo");
                }
            });
        }

        if (rbSigiloso != null) {
            rbSigiloso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption("Sigiloso");
                }
            });
        }

        if (rbAnonimo != null) {
            rbAnonimo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectOption("Anônimo");
                }
            });
        }

        // Botão avançar (ATUALIZADO AQUI!)
        if (btnAvancar != null) {
            btnAvancar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Botão avançar clicado");

                    try {
                        // Navegar para a próxima tela
                        Intent intent = new Intent(NivelSigilo.this, Nova_Manifestacao.class);
                        intent.putExtra("tipo_manifestacao", tipoManifestacao);
                        intent.putExtra("nivel_sigilo", nivelSelecionado);

                        Log.d(TAG, "Navegando para Nova_Manifestacao");
                        startActivity(intent);

                    } catch (Exception e) {
                        Log.e(TAG, "Erro ao navegar: " + e.getMessage());
                        e.printStackTrace();
                        Toast.makeText(NivelSigilo.this,
                                "Erro ao navegar: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        Log.d(TAG, "Listeners configurados");
    }

    private void selectOption(String nivel) {
        Log.d(TAG, "Selecionando nível: " + nivel);

        // Limpar todas as seleções
        if (rbSemSigilo != null) rbSemSigilo.setChecked(false);
        if (rbSigiloso != null) rbSigiloso.setChecked(false);
        if (rbAnonimo != null) rbAnonimo.setChecked(false);

        // Selecionar o correto
        nivelSelecionado = nivel;
        switch (nivel) {
            case "Sem Sigilo":
                if (rbSemSigilo != null) rbSemSigilo.setChecked(true);
                break;
            case "Sigiloso":
                if (rbSigiloso != null) rbSigiloso.setChecked(true);
                break;
            case "Anônimo":
                if (rbAnonimo != null) rbAnonimo.setChecked(true);
                break;
        }

        updateDescription(nivel);
    }

    private void updateDescription(String nivel) {
        if (tvDescription == null) return;

        String descricao = "";
        switch (nivel) {
            case "Sem Sigilo":
                descricao = "Sem sigilo: Não será dado qualquer tratamento de sigilo sobre os dados pessoais informados.";
                break;
            case "Sigiloso":
                descricao = "Sigiloso: O pedido de sigilo deve ser justificado. E caberá ao órgão destinatário da manifestação, em geral uma promotoria de justiça, o deferimento ou não do sigilo.";
                break;
            case "Anônimo":
                descricao = "Anônimo: O manifestante não será identificado, porém a manifestação poderá não ser atendida.";
                break;
        }

        tvDescription.setText(descricao);
        Log.d(TAG, "Descrição atualizada para: " + nivel);
    }
}
