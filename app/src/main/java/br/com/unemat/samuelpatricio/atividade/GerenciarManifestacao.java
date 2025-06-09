package br.com.unemat.samuelpatricio.atividade;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class GerenciarManifestacao extends AppCompatActivity {

    private static final String TAG = "GerenciarManifestacao";

    private TextView tvTituloGerenciar;
    private TextView tvUsuarioLogado;
    private TextView tvTotalManifestacoes;
    private RecyclerView recyclerViewManifestacoes;
    private Button btnLogout;
    private Button btnAtualizarLista;

    private GerenciarManifestacaoAdapter manifestacaoAdapter;
    private List<ManifestacaoGerenciar> listaManifestacoes;
    private String usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_manifestacao);

        Log.d(TAG, "onCreate: Iniciando GerenciarManifestacaoActivity");

        // Obter usuário logado
        usuarioLogado = getIntent().getStringExtra("usuario_logado");
        if (usuarioLogado == null) {
            usuarioLogado = "Administrador";
        }

        initComponents();
        setupListeners();
        setupUserInfo();
        carregarManifestacoes();
        setupRecyclerView();
    }

    private void initComponents() {
        Log.d(TAG, "initComponents: Inicializando componentes");

        try {
            tvTituloGerenciar = findViewById(R.id.tv_titulo_gerenciar);
            tvUsuarioLogado = findViewById(R.id.tv_usuario_logado);
            tvTotalManifestacoes = findViewById(R.id.tv_total_manifestacoes);
            recyclerViewManifestacoes = findViewById(R.id.recycler_manifestacoes);
            btnLogout = findViewById(R.id.btn_logout);
            btnAtualizarLista = findViewById(R.id.btn_atualizar_lista);

        } catch (Exception e) {
            Log.e(TAG, "initComponents: Erro ao inicializar componentes", e);
        }
    }

    private void setupListeners() {
        Log.d(TAG, "setupListeners: Configurando listeners");

        try {
            // Botão Logout
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realizarLogout();
                }
            });

            // Botão Atualizar Lista
            btnAtualizarLista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    atualizarListaManifestacoes();
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "setupListeners: Erro ao configurar listeners", e);
        }
    }

    private void setupUserInfo() {
        try {
            tvTituloGerenciar.setText("Gerenciar Manifestações");
            tvUsuarioLogado.setText("Ouvidor: " + usuarioLogado);
        } catch (Exception e) {
            Log.e(TAG, "setupUserInfo: Erro ao configurar informações do usuário", e);
        }
    }

    private void carregarManifestacoes() {
        Log.d(TAG, "carregarManifestacoes: Carregando manifestações do banco de dados");

        try {
            // Simular dados do banco de dados
            listaManifestacoes = new ArrayList<>();

            // Manifestação 1 - Denúncia
            listaManifestacoes.add(new ManifestacaoGerenciar(
                    "PROT2024001",
                    "Denúncia",
                    "Irregularidades no atendimento ao público na Promotoria de Justiça",
                    "João Silva Santos",
                    "15/01/2024",
                    "PENDENTE",
                    "Maceió"
            ));

            // Manifestação 2 - Reclamação
            listaManifestacoes.add(new ManifestacaoGerenciar(
                    "PROT2024002",
                    "Reclamação",
                    "Demora excessiva no andamento de processo judicial há mais de 2 anos",
                    "Maria Oliveira Costa",
                    "14/01/2024",
                    "PENDENTE",
                    "Arapiraca"
            ));

            // Manifestação 3 - Sugestão
            listaManifestacoes.add(new ManifestacaoGerenciar(
                    "PROT2024003",
                    "Sugestão",
                    "Implementar sistema de agendamento online para atendimento presencial",
                    "Carlos Eduardo Lima",
                    "13/01/2024",
                    "RESOLVIDA",
                    "Palmeira dos Índios"
            ));

            // Manifestação 4 - Denúncia
            listaManifestacoes.add(new ManifestacaoGerenciar(
                    "PROT2024004",
                    "Denúncia",
                    "Comportamento inadequado de servidor público durante atendimento",
                    "Ana Paula Ferreira",
                    "12/01/2024",
                    "PENDENTE",
                    "União dos Palmares"
            ));

            // Manifestação 5 - Elogio
            listaManifestacoes.add(new ManifestacaoGerenciar(
                    "PROT2024005",
                    "Elogio",
                    "Excelente atendimento e agilidade na resolução do meu caso",
                    "Pedro Henrique Alves",
                    "11/01/2024",
                    "RESOLVIDA",
                    "Penedo"
            ));

            // Manifestação 6 - Reclamação
            listaManifestacoes.add(new ManifestacaoGerenciar(
                    "PROT2024006",
                    "Reclamação",
                    "Falta de acessibilidade nas instalações do prédio do MP",
                    "Lucia Maria Santos",
                    "10/01/2024",
                    "INVALIDA",
                    "São Miguel dos Campos"
            ));

            // Atualizar contador
            atualizarContador();

            Log.d(TAG, "carregarManifestacoes: " + listaManifestacoes.size() + " manifestações carregadas");

        } catch (Exception e) {
            Log.e(TAG, "carregarManifestacoes: Erro ao carregar manifestações", e);
        }
    }

    private void setupRecyclerView() {
        Log.d(TAG, "setupRecyclerView: Configurando RecyclerView");

        try {
            manifestacaoAdapter = new GerenciarManifestacaoAdapter(listaManifestacoes, new GerenciarManifestacaoAdapter.OnManifestacaoListener() {
                @Override
                public void onStatusChanged(ManifestacaoGerenciar manifestacao, String novoStatus) {
                    atualizarStatusManifestacao(manifestacao, novoStatus);
                }
            });

            recyclerViewManifestacoes.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewManifestacoes.setAdapter(manifestacaoAdapter);

        } catch (Exception e) {
            Log.e(TAG, "setupRecyclerView: Erro ao configurar RecyclerView", e);
        }
    }

    private void atualizarStatusManifestacao(ManifestacaoGerenciar manifestacao, String novoStatus) {
        Log.d(TAG, "atualizarStatusManifestacao: Atualizando " + manifestacao.getProtocolo() + " para " + novoStatus);

        try {
            // Atualizar status da manifestação
            manifestacao.setStatus(novoStatus);

            // Atualizar adapter
            manifestacaoAdapter.notifyDataSetChanged();

            // Enviar notificação
            enviarNotificacao(manifestacao, novoStatus);

            // Mostrar feedback
            String mensagem = "✅ Manifestação " + manifestacao.getProtocolo() + " marcada como " + novoStatus;
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();

            // Atualizar contador
            atualizarContador();

        } catch (Exception e) {
            Log.e(TAG, "atualizarStatusManifestacao: Erro ao atualizar status", e);
        }
    }

    private void enviarNotificacao(ManifestacaoGerenciar manifestacao, String status) {
        Log.d(TAG, "enviarNotificacao: Enviando notificação para " + manifestacao.getProtocolo());

        try {
            String titulo = "";
            String mensagem = "";

            switch (status) {
                case "RESOLVIDA":
                    titulo = "✅ Manifestação Resolvida";
                    mensagem = "Sua manifestação " + manifestacao.getProtocolo() + " foi resolvida com sucesso!\n\nObrigado por utilizar nossos serviços.";
                    break;

                case "PENDENTE":
                    titulo = "⏳ Manifestação em Análise";
                    mensagem = "Sua manifestação " + manifestacao.getProtocolo() + " está sendo analisada por nossa equipe.\n\nEm breve você terá uma resposta.";
                    break;

                case "INVALIDA":
                    titulo = "❌ Manifestação Inválida";
                    mensagem = "Sua manifestação " + manifestacao.getProtocolo() + " foi considerada inválida.\n\nVerifique se atende aos critérios estabelecidos.";
                    break;
            }

            // Simular envio de notificação
            NotificationHelperGerenciar.enviarNotificacao(this, titulo, mensagem);

            Log.d(TAG, "enviarNotificacao: Notificação enviada - " + titulo);

        } catch (Exception e) {
            Log.e(TAG, "enviarNotificacao: Erro ao enviar notificação", e);
        }
    }

    private void atualizarListaManifestacoes() {
        Log.d(TAG, "atualizarListaManifestacoes: Atualizando lista");

        try {
            // Recarregar dados (simular refresh do banco)
            carregarManifestacoes();
            manifestacaoAdapter.notifyDataSetChanged();

            Toast.makeText(this, "📋 Lista atualizada com sucesso!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "atualizarListaManifestacoes: Erro ao atualizar lista", e);
        }
    }

    private void atualizarContador() {
        try {
            int total = listaManifestacoes.size();
            int resolvidas = 0;
            int pendentes = 0;
            int invalidas = 0;

            for (ManifestacaoGerenciar m : listaManifestacoes) {
                switch (m.getStatus()) {
                    case "RESOLVIDA":
                        resolvidas++;
                        break;
                    case "PENDENTE":
                        pendentes++;
                        break;
                    case "INVALIDA":
                        invalidas++;
                        break;
                }
            }

            String contador = "📊 Total: " + total + " | ✅ Resolvidas: " + resolvidas + " | ⏳ Pendentes: " + pendentes + " | ❌ Inválidas: " + invalidas;
            tvTotalManifestacoes.setText(contador);

        } catch (Exception e) {
            Log.e(TAG, "atualizarContador: Erro ao atualizar contador", e);
        }
    }

    private void realizarLogout() {
        Log.d(TAG, "realizarLogout: Fazendo logout");

        try {
            Toast.makeText(this, "🚪 Logout realizado com sucesso", Toast.LENGTH_SHORT).show();

            // Voltar para a tela principal
            Intent intent = new Intent(GerenciarManifestacao.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            // Finalizar todas as activities relacionadas ao admin
            finish();

        } catch (Exception e) {
            Log.e(TAG, "realizarLogout: Erro durante logout", e);
        }
    }

    @Override
    public void onBackPressed() {
        // Confirmar se deseja sair
        realizarLogout();
    }
}
