package br.com.unemat.samuelpatricio.atividade;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    // Componentes da interface
    private EditText etUsuario;
    private EditText etSenha;
    private Button btnEntrar;
    private Button btnVoltar;
    private ProgressBar progressBar;
    private TextView tvTitulo;
    private TextView tvSubtitulo;

    // ========================================
    // 🔐 LISTA FIXA DE USUÁRIOS AUTORIZADOS
    // ========================================
    // IMPORTANTE: Esta é a lista de usuários que podem acessar a área administrativa
    // Para adicionar novos usuários, inclua na lista abaixo no formato: "usuario" -> "senha"
    // Para remover usuários, delete a linha correspondente
    // ========================================
    private static final Map<String, String> USUARIOS_AUTORIZADOS = new HashMap<String, String>() {{
        // 👤 USUÁRIO 1: Ouvidor Principal
        put("ouvidor987@city.fala", "20250605");

        // 👤 USUÁRIO 2: Ouvidor Secundário
        put("ouvidor007@city.fala", "12345678");

        // ========================================
        // 📝 INSTRUÇÕES PARA MODIFICAR USUÁRIOS:
        // ========================================
        // Para ADICIONAR novo usuário:
        // put("novo_usuario@email.com", "nova_senha");
        //
        // Para REMOVER usuário:
        // Comente ou delete a linha correspondente
        //
        // Para ALTERAR senha:
        // Modifique apenas o segundo parâmetro (senha)
        // ========================================
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG, "onCreate: Iniciando LoginActivity");

        initComponents();
        setupListeners();
    }

    private void initComponents() {
        Log.d(TAG, "initComponents: Inicializando componentes");

        try {
            etUsuario = findViewById(R.id.et_usuario);
            etSenha = findViewById(R.id.et_senha);
            btnEntrar = findViewById(R.id.btn_entrar);
            btnVoltar = findViewById(R.id.btn_voltar);
            progressBar = findViewById(R.id.progress_bar);
            tvTitulo = findViewById(R.id.tv_titulo);
            tvSubtitulo = findViewById(R.id.tv_subtitulo);

            // Inicialmente esconder o progress bar
            progressBar.setVisibility(View.GONE);

        } catch (Exception e) {
            Log.e(TAG, "initComponents: Erro ao inicializar componentes", e);
        }
    }

    private void setupListeners() {
        Log.d(TAG, "setupListeners: Configurando listeners");

        try {
            // Botão Entrar
            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realizarLogin();
                }
            });

            // Botão Voltar
            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish(); // Volta para a tela anterior (SobreActivity)
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "setupListeners: Erro ao configurar listeners", e);
        }
    }

    private void realizarLogin() {
        Log.d(TAG, "realizarLogin: Iniciando processo de login");

        try {
            // Obter dados dos campos
            String usuario = etUsuario.getText().toString().trim();
            String senha = etSenha.getText().toString().trim();

            // Validar campos vazios
            if (TextUtils.isEmpty(usuario)) {
                etUsuario.setError("Campo obrigatório");
                etUsuario.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(senha)) {
                etSenha.setError("Campo obrigatório");
                etSenha.requestFocus();
                return;
            }

            // Mostrar loading
            mostrarLoading(true);

            // Simular delay de autenticação (opcional)
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    validarCredenciais(usuario, senha);
                }
            }, 1000); // 1 segundo de delay

        } catch (Exception e) {
            Log.e(TAG, "realizarLogin: Erro durante login", e);
            mostrarLoading(false);
            Toast.makeText(this, "Erro interno. Tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private void validarCredenciais(String usuario, String senha) {
        Log.d(TAG, "validarCredenciais: Validando usuário: " + usuario);

        try {
            // Verificar se o usuário existe e a senha está correta
            if (USUARIOS_AUTORIZADOS.containsKey(usuario)) {
                String senhaCorreta = USUARIOS_AUTORIZADOS.get(usuario);

                if (senhaCorreta != null && senhaCorreta.equals(senha)) {
                    // Login bem-sucedido
                    Log.d(TAG, "validarCredenciais: Login bem-sucedido para: " + usuario);
                    loginBemSucedido(usuario);
                } else {
                    // Senha incorreta
                    Log.d(TAG, "validarCredenciais: Senha incorreta para: " + usuario);
                    loginFalhou("Senha incorreta");
                }
            } else {
                // Usuário não encontrado
                Log.d(TAG, "validarCredenciais: Usuário não encontrado: " + usuario);
                loginFalhou("Usuário não encontrado");
            }

        } catch (Exception e) {
            Log.e(TAG, "validarCredenciais: Erro durante validação", e);
            loginFalhou("Erro interno. Tente novamente.");
        }
    }

    private void loginBemSucedido(String usuario) {
        Log.d(TAG, "loginBemSucedido: Redirecionando para área administrativa");

        try {
            mostrarLoading(false);

            // Mostrar mensagem de sucesso
            Toast.makeText(this, "Bem-vindo, " + usuario + "!", Toast.LENGTH_SHORT).show();

            // Redirecionar para a área administrativa
            Intent intent = new Intent(Login.this, AdminActivity.class);
            intent.putExtra("usuario_logado", usuario);
            startActivity(intent);

            // Finalizar a tela de login
            finish();

        } catch (Exception e) {
            Log.e(TAG, "loginBemSucedido: Erro ao redirecionar", e);
            Toast.makeText(this, "Erro ao acessar área administrativa", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginFalhou(String mensagem) {
        Log.d(TAG, "loginFalhou: " + mensagem);

        try {
            mostrarLoading(false);

            // Mostrar mensagem de erro
            Toast.makeText(this, "Acesso negado: " + mensagem, Toast.LENGTH_LONG).show();

            // Limpar campos
            etSenha.setText("");
            etSenha.requestFocus();

            // Destacar campos com erro
            etUsuario.setError("Verifique suas credenciais");
            etSenha.setError("Verifique suas credenciais");

        } catch (Exception e) {
            Log.e(TAG, "loginFalhou: Erro ao processar falha", e);
        }
    }

    private void mostrarLoading(boolean mostrar) {
        try {
            if (mostrar) {
                progressBar.setVisibility(View.VISIBLE);
                btnEntrar.setEnabled(false);
                btnEntrar.setText("Verificando...");
            } else {
                progressBar.setVisibility(View.GONE);
                btnEntrar.setEnabled(true);
                btnEntrar.setText("Entrar");
            }
        } catch (Exception e) {
            Log.e(TAG, "mostrarLoading: Erro ao alterar estado do loading", e);
        }
    }

    @Override
    public void onBackPressed() {
        // Permitir voltar normalmente
        super.onBackPressed();
    }
}
