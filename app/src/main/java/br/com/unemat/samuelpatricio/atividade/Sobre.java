package br.com.unemat.samuelpatricio.atividade;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Sobre extends AppCompatActivity {

    private static final String TAG = "SobreActivity";

    private Button btnTelefone;
    private Button btnEmail;
    private Button btnAreaAdministracao;

    // Navegação
    private LinearLayout navInicio;
    private LinearLayout navManifestacoes;
    private LinearLayout navConsultar;
    private LinearLayout navSobre;

    // Informações de contato
    private static final String TELEFONE = "8221223512";
    private static final String EMAIL = "ouvidoria@mpal.mp.br";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_sobre);
            Log.d(TAG, "onCreate: Layout carregado com sucesso");

            initComponents();
            setupListeners();
            setupNavigation();

        } catch (Exception e) {
            Log.e(TAG, "onCreate: Erro ao inicializar SobreActivity", e);
            Toast.makeText(this, "Erro ao carregar tela: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void initComponents() {
        Log.d(TAG, "initComponents: Inicializando componentes");

        try {
            btnTelefone = findViewById(R.id.btn_telefone);
            btnEmail = findViewById(R.id.btn_email);
            btnAreaAdministracao = findViewById(R.id.btn_area_administracao);

            // Navegação
            navInicio = findViewById(R.id.nav_inicio);
            navManifestacoes = findViewById(R.id.nav_manifestacoes);
            navConsultar = findViewById(R.id.nav_consultar);
            navSobre = findViewById(R.id.nav_sobre);

        } catch (Exception e) {
            Log.e(TAG, "initComponents: Erro ao inicializar componentes", e);
        }
    }

    private void setupListeners() {
        Log.d(TAG, "setupListeners: Configurando listeners");

        try {
            // Botão Telefone
            if (btnTelefone != null) {
                btnTelefone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ligarTelefone();
                    }
                });
            }

            // Botão Email
            if (btnEmail != null) {
                btnEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enviarEmail();
                    }
                });
            }

            // Botão Área de Administração - AGORA ABRE A TELA DE LOGIN
            if (btnAreaAdministracao != null) {
                btnAreaAdministracao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: Botão Área de Administração clicado");
                        abrirTelaLogin(); // Chama a função que abre a tela de login
                    }
                });
                Log.d(TAG, "setupListeners: Listener do botão Área de Administração configurado");
            } else {
                Log.e(TAG, "setupListeners: btnAreaAdministracao é null");
            }

        } catch (Exception e) {
            Log.e(TAG, "setupListeners: Erro ao configurar listeners", e);
        }
    }

    private void setupNavigation() {
        Log.d(TAG, "setupNavigation: Configurando navegação inferior");

        try {
            // Navegação
            if (navInicio != null) {
                navInicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Sobre.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            if (navManifestacoes != null) {
                navManifestacoes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Sobre.this, Manifestacao.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            if (navConsultar != null) {
                navConsultar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Sobre.this, Consultar.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

        } catch (Exception e) {
            Log.e(TAG, "setupNavigation: Erro ao configurar navegação", e);
        }
    }

    private void ligarTelefone() {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + TELEFONE));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Nenhum aplicativo de telefone encontrado", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e(TAG, "ligarTelefone: Erro ao tentar ligar", e);
            Toast.makeText(this, "Erro ao tentar ligar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void enviarEmail() {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + EMAIL));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contato via App Ouvidoria");
            intent.putExtra(Intent.EXTRA_TEXT, "Olá,\n\nEntro em contato através do aplicativo da Ouvidoria.\n\n");

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(intent, "Enviar email"));
            } else {
                Toast.makeText(this, "Nenhum aplicativo de email encontrado", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e(TAG, "enviarEmail: Erro ao tentar enviar email", e);
            Toast.makeText(this, "Erro ao tentar enviar email: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // NOVA FUNÇÃO: Abrir tela de login em vez de mostrar toast
    private void abrirTelaLogin() {
        try {
            Log.d(TAG, "abrirTelaLogin: Abrindo tela de login para área administrativa");

            // Criar Intent para LoginActivity
            Intent intent = new Intent(Sobre.this, Login.class);

            // Adicionar flags para melhor navegação
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Iniciar a LoginActivity
            startActivity(intent);

            Log.d(TAG, "abrirTelaLogin: LoginActivity iniciada com sucesso");

        } catch (Exception e) {
            Log.e(TAG, "abrirTelaLogin: Erro ao abrir tela de login", e);
            Toast.makeText(this, "Erro ao acessar área de login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
