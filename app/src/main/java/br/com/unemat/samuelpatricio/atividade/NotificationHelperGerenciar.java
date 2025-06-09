package br.com.unemat.samuelpatricio.atividade;



import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class NotificationHelperGerenciar {

    private static final String TAG = "NotificationGerenciar";
    private static final String CHANNEL_ID = "ouvidoria_gerenciar_channel";
    private static final String CHANNEL_NAME = "Ouvidoria Gerenciar";
    private static final String CHANNEL_DESCRIPTION = "Notificações de gerenciamento de manifestações";

    public static void enviarNotificacao(Context context, String titulo, String mensagem) {
        Log.d(TAG, "enviarNotificacao: " + titulo + " - " + mensagem);

        try {
            // Criar canal de notificação (Android 8.0+)
            criarCanalNotificacao(context);

            // Simular envio de notificação com Toast
            // Em produção, aqui seria enviada uma notificação real
            String notificacaoCompleta = "📱 NOTIFICAÇÃO ENVIADA\n\n" + titulo + "\n\n" + mensagem;
            Toast.makeText(context, notificacaoCompleta, Toast.LENGTH_LONG).show();

            Log.d(TAG, "enviarNotificacao: Notificação simulada enviada");

        } catch (Exception e) {
            Log.e(TAG, "enviarNotificacao: Erro ao enviar notificação", e);
        }
    }

    private static void criarCanalNotificacao(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(CHANNEL_DESCRIPTION);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
