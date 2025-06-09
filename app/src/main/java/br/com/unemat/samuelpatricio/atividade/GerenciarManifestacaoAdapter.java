package br.com.unemat.samuelpatricio.atividade;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GerenciarManifestacaoAdapter extends RecyclerView.Adapter<GerenciarManifestacaoAdapter.GerenciarViewHolder> {

    private List<ManifestacaoGerenciar> manifestacoes;
    private OnManifestacaoListener listener;

    public interface OnManifestacaoListener {
        void onStatusChanged(ManifestacaoGerenciar manifestacao, String novoStatus);
    }

    public GerenciarManifestacaoAdapter(List<ManifestacaoGerenciar> manifestacoes, OnManifestacaoListener listener) {
        this.manifestacoes = manifestacoes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GerenciarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gerenciar, parent, false);
        return new GerenciarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GerenciarViewHolder holder, int position) {
        ManifestacaoGerenciar manifestacao = manifestacoes.get(position);
        holder.bind(manifestacao, listener);
    }

    @Override
    public int getItemCount() {
        return manifestacoes.size();
    }

    static class GerenciarViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView tvProtocolo;
        private TextView tvTipo;
        private TextView tvDescricao;
        private TextView tvManifestante;
        private TextView tvData;
        private TextView tvCidade;
        private TextView tvStatus;
        private Spinner spinnerStatus;
        private Button btnAplicarStatus;

        public GerenciarViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_gerenciar_manifestacao);
            tvProtocolo = itemView.findViewById(R.id.tv_protocolo);
            tvTipo = itemView.findViewById(R.id.tv_tipo);
            tvDescricao = itemView.findViewById(R.id.tv_descricao);
            tvManifestante = itemView.findViewById(R.id.tv_manifestante);
            tvData = itemView.findViewById(R.id.tv_data);
            tvCidade = itemView.findViewById(R.id.tv_cidade);
            tvStatus = itemView.findViewById(R.id.tv_status);
            spinnerStatus = itemView.findViewById(R.id.spinner_status);
            btnAplicarStatus = itemView.findViewById(R.id.btn_aplicar_status);
        }

        public void bind(ManifestacaoGerenciar manifestacao, OnManifestacaoListener listener) {
            // Preencher dados
            tvProtocolo.setText("📋 " + manifestacao.getProtocolo());
            tvTipo.setText(manifestacao.getTipo());
            tvDescricao.setText(manifestacao.getDescricao());
            tvManifestante.setText("👤 " + manifestacao.getNomeManifestante());
            tvData.setText("📅 " + manifestacao.getDataRegistro());
            tvCidade.setText("📍 " + manifestacao.getCidade());
            tvStatus.setText("Status: " + manifestacao.getStatus());

            // Configurar cor do card baseada no status
            configurarCorCard(manifestacao.getStatus());

            // Configurar spinner
            String[] statusOptions = {"PENDENTE", "RESOLVIDA", "INVALIDA"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(itemView.getContext(),
                    android.R.layout.simple_spinner_item, statusOptions);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStatus.setAdapter(adapter);

            // Selecionar status atual no spinner
            for (int i = 0; i < statusOptions.length; i++) {
                if (statusOptions[i].equals(manifestacao.getStatus())) {
                    spinnerStatus.setSelection(i);
                    break;
                }
            }

            // Configurar botão
            btnAplicarStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String novoStatus = spinnerStatus.getSelectedItem().toString();
                    if (listener != null) {
                        listener.onStatusChanged(manifestacao, novoStatus);
                    }
                }
            });
        }

        private void configurarCorCard(String status) {
            int corFundo;
            switch (status) {
                case "RESOLVIDA":
                    corFundo = itemView.getContext().getResources().getColor(R.color.status_resolvida);
                    break;
                case "PENDENTE":
                    corFundo = itemView.getContext().getResources().getColor(R.color.status_pendente);
                    break;
                case "INVALIDA":
                    corFundo = itemView.getContext().getResources().getColor(R.color.status_invalida);
                    break;
                default:
                    corFundo = itemView.getContext().getResources().getColor(R.color.status_default);
                    break;
            }
            cardView.setCardBackgroundColor(corFundo);
        }
    }
}

