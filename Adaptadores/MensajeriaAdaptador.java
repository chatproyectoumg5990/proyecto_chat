package com.chat.app.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.chat.app.Entidades.Logica.LMensaje;
import com.chat.app.Entidades.Logica.LUsuario;
import com.chat.app.Holder.MensajeriaHolder;
import com.chat.app.Persistencia.UsuarioDAO;
import com.chat.app.R;



public class MensajeriaAdaptador extends RecyclerView.Adapter<MensajeriaHolder> {

    private List<LMensaje> listMensaje = new ArrayList<>();
    private Context c;

    public MensajeriaAdaptador(Context c) {
        this.c = c;
    }

    public int addMensaje(LMensaje lMensaje){
        listMensaje.add(lMensaje);
        //3 mensajes
        int posicion = listMensaje.size()-1;//3
        notifyItemInserted(listMensaje.size());
        return posicion;
    }

    public void actualizarMensaje(int posicion,LMensaje lMensaje){
        listMensaje.set(posicion,lMensaje);//2
        notifyItemChanged(posicion);
    }

    @Override
    public MensajeriaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==1){
            view = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes_emisor_pr,parent,false);
        }else{
            view = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes_receptor_pr,parent,false);
        }
        return new MensajeriaHolder(view);
    }

    @Override
    public void onBindViewHolder(MensajeriaHolder holder, int position) {

        LMensaje lMensaje = listMensaje.get(position);

        LUsuario lUsuario = lMensaje.getlUsuario();

        if(lUsuario!=null){
            holder.getNombre().setText(lUsuario.getUsuario().getNombre());
            Glide.with(c).load(lUsuario.getUsuario().getFotoPerfilURL()).into(holder.getFotoMensajePerfil());
        }

        holder.getMensaje().setText(lMensaje.getMensaje().getMensaje());
        if(lMensaje.getMensaje().isContieneFoto()){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(lMensaje.getMensaje().getUrlFoto()).into(holder.getFotoMensaje());
        }else {
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

        holder.getHora().setText(lMensaje.fechaDeCreacionDelMensaje());
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(listMensaje.get(position).getlUsuario()!=null){
            if(listMensaje.get(position).getlUsuario().getKey().equals(UsuarioDAO.getInstancia().getKeyUsuario())){
                return 1;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
        //return super.getItemViewType(position);
    }
}
