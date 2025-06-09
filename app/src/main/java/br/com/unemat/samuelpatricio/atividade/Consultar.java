package br.com.unemat.samuelpatricio.atividade;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Consultar extends AppCompatActivity {

    private static final String TAG = "Consutar";

    private EditText etNumeroProtocolo;
    private Button btnBuscar;
    private TextView tvInstrucao;
    private ScrollView svResultado;

    // TextViews para exibir os detalhes da manifestação
    private TextView tvProtocolo;
    private TextView tvTipoManifestacao;
    private TextView tvNivelSigilo;
    private TextView tvDescricao;
    private TextView tvJustificativa;
    private TextView tvMunicipio;
    private TextView tvStatus;

    // Navegação
    private LinearLayout navInicio;
    private LinearLayout navManifestacoes;
    private LinearLayout navConsultar;
    private LinearLayout navSobre;

    // Dados de exemplo para simulação
    private static final String PROTOCOLO_EXEMPLO = "OUV-2025-06-01-001";
    private static final String TIPO_MANIFESTACAO_EXEMPLO = "Denúncia";
    private static final String NIVEL_SIGILO_EXEMPLO = "Identificação Reservada";
    private static final String DESCRICAO_EXEMPLO = "Relato sobre possíveis irregularidades na prestação de serviços públicos no município.";
    private static final String JUSTIFICATIVA_EXEMPLO = "Necessidade de preservar a identidade do denunciante para evitar possíveis retaliações.";
    private static final String MUNICIPIO_EXEMPLO = "Cáceres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        Log.d(TAG, "onCreate: Iniciando ConsultarActivity");

        initComponents();
        setupListeners();
    }

    private void initComponents() {
        Log.d(TAG, "initComponents: Inicializando componentes");

        etNumeroProtocolo = findViewById(R.id.et_numero_protocolo);
        btnBuscar = findViewById(R.id.btn_buscar);
        tvInstrucao = findViewById(R.id.tv_instrucao);
        svResultado = findViewById(R.id.sv_resultado);

        // TextViews de resultado
        tvProtocolo = findViewById(R.id.tv_protocolo);
        tvTipoManifestacao = findViewById(R.id.tv_tipo_manifestacao);
        tvNivelSigilo = findViewById(R.id.tv_nivel_sigilo);
        tvDescricao = findViewById(R.id.tv_descricao);
        tvJustificativa = findViewById(R.id.tv_justificativa);
        tvMunicipio = findViewById(R.id.tv_municipio);
        tvStatus = findViewById(R.id.tv_status);

        // Navegação
        navInicio = findViewById(R.id.nav_inicio);
        navManifestacoes = findViewById(R.id.nav_manifestacoes);
        navConsultar = findViewById(R.id.nav_consultar);
        navSobre = findViewById(R.id.nav_sobre);
    }

    private void setupListeners() {
        Log.d(TAG, "setupListeners: Configurando listeners");

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarManifestacao();
            }
        });

        // Navegação
        navInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consultar.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        navManifestacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consultar.this, Manifestacao.class);
                startActivity(intent);
            }
        });

        // Não precisamos de listener para navConsultar pois já estamos nessa tela

        navSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Consultar.this, "Sobre", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buscarManifestacao() {
        String protocolo = etNumeroProtocolo.getText().toString().trim();

        if (protocolo.isEmpty()) {
            Toast.makeText(this, "Digite o número do protocolo", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "buscarManifestacao: Buscando protocolo: " + protocolo);

        // Em um app real, aqui faria uma consulta ao banco de dados ou API
        // Para este exemplo, vamos simular uma busca bem-sucedida se o protocolo
        // começar com "OUV-" ou for igual ao protocolo de exemplo

        if (protocolo.startsWith("OUV-") || protocolo.equals(PROTOCOLO_EXEMPLO)) {
            // Simular busca bem-sucedida
            exibirResultado(
                    protocolo,
                    TIPO_MANIFESTACAO_EXEMPLO,
                    NIVEL_SIGILO_EXEMPLO,
                    DESCRICAO_EXEMPLO,
                    JUSTIFICATIVA_EXEMPLO,
                    MUNICIPIO_EXEMPLO
            );
        } else {
            // Simular busca sem resultados
            Toast.makeText(this, "Manifestação não encontrada", Toast.LENGTH_LONG).show();
        }
    }

    private void exibirResultado(String protocolo, String tipoManifestacao, String nivelSigilo,
                                 String descricao, String justificativa, String municipio) {
        Log.d(TAG, "exibirResultado: Exibindo resultado para protocolo: " + protocolo);

        // Preencher os campos de resultado
        tvProtocolo.setText(protocolo);
        tvTipoManifestacao.setText(tipoManifestacao);
        tvNivelSigilo.setText(nivelSigilo);
        tvDescricao.setText(descricao);
        tvJustificativa.setText(justificativa);
        tvMunicipio.setText(municipio);

        // Ocultar a instrução e mostrar o resultado
        tvInstrucao.setVisibility(View.GONE);
        svResultado.setVisibility(View.VISIBLE);
    }
}