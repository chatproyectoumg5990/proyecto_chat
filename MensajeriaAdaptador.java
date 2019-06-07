package cristian.cruz.com.firebasechat.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cristian.cruz.com.firebasechat.Entidades.Logica.LMensaje;
import cristian.cruz.com.firebasechat.Holder.MensajeriaHolder;
import cristian.cruz.com.firebasechat.R;

public class MensajeriaAdaptador extends RecyclerView.Adapter<MensajeriaHolder> {

    private List<LMensaje> listMensaje = new ArrayList<>();
    private Context c;

    public MensajeriaAdaptador(Context c) {
        this.c = c;
    }

    public void addMensaje(LMensaje lMensaje){
        listMensaje.add(lMensaje);
        notifyItemInserted(listMensaje.size());


    }

    @NonNull
    @Override
    public MensajeriaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes,parent,false);
        return new MensajeriaHolder(v);
    }

    @Override
    public void onBindViewHolder(MensajeriaHolder holder, int position) {

        LMensaje lMensaje= listMensaje.get(position);

        holder.getNombre().setText(listMensaje.get(position).getlUsuario().getUsuario().getNombre());
        holder.getMensaje().setText(listMensaje.get(position).getMensaje().getMensaje());
        if (lMensaje.getMensaje().getContieneFoto()){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(lMensaje.getMensaje().getMensaje()).into(holder.getFotoMensaje());

        }else {
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getFotoMensaje().setVisibility(View.VISIBLE);

        }

        Glide.with(c).load(lMensaje.getlUsuario().getUsuario().getFotoPerfilURL()).into(holder.getFotoMensaje());


        holder.getHora().setText(lMensaje.fechaDeCreacionDelMensaje());

        }
    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}
