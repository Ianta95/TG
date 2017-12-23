package com.example.jesus95.truequedegarage.Articulos;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jesus95.truequedegarage.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by jesus95 on 10/10/17.
 */

public class MiniAdapter extends RecyclerView.Adapter<MiniAdapter.ArticulosMiniViewHolder>{

    List<Articulo> Larticulos;
    int mCategoria = 0;
    String mId;
    Bitmap Imagen;
    Uri uri;
    private Cursor items;
    Context context;

    public MiniAdapter(Context context, List<Articulo> Larticulos){
        this.Larticulos = Larticulos;
        this.context = context;
    }

    @Override
    public ArticulosMiniViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mini_recycler, parent, false);
        ArticulosMiniViewHolder holder = new ArticulosMiniViewHolder(v);
        final LinearLayout Seleccion;
        Seleccion = (LinearLayout) v.findViewById(R.id.ln_layout_row_mini_recycler_holder);

        v.setOnTouchListener(new View.OnTouchListener() {
            private float xDelta;
            private float xMove;
            private float yDelta;
            int mSeleccion;
            private int izquierdaoderecha = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xDelta = v.getX() - event.getRawX();
                        xMove = v.getX();
                        Log.e("Mylog","getx es: "+v.getX());
                        Log.e("Mylod","getRawX es: "+event.getRawX());
                        Log.e("Mylog","xDelta es: "+xDelta);

                        if (v.getX() == 650.0f){
                            izquierdaoderecha = 1;
                        }
                        if (v.getX() == 0.0f){
                            izquierdaoderecha = 0;
                        }


                        break;
                    case MotionEvent.ACTION_MOVE:
                            v.animate().x(event.getRawX() + xDelta).setDuration(0).start();
                            Log.e("Mylog","event Raw x es: "+event.getRawX());
                            Log.e("Mylog","x Delta es: "+ xDelta);
                        break;
                    case MotionEvent.ACTION_UP:

                        if (izquierdaoderecha == 0){
                            v.animate().x(650.0f).setDuration(100).start();
                            Seleccion.setBackgroundResource(R.color.verdeok);
                        }
                        if (izquierdaoderecha == 1){
                            v.animate().x(0.0f).setDuration(100).start();
                            Seleccion.setBackgroundResource(R.color.naranja);
                        }


                       /* if (v.getX() > 50.0f && izquierdaoderecha == 0){
                            v.animate().x(550.0f).setDuration(100).start();
                        }*/
                        /*if (v.getX() < 50.0f && izquierdaoderecha == 0){
                            v.animate().x(0.0f).setDuration(100).start();
                        }*/
                        /*if (v.getX() < 500.0f && izquierdaoderecha == 1) {
                            v.animate().x(0.0f).setDuration(100).start();
                        }*/
                        /*if (v.getX() > 500.0f && izquierdaoderecha == 1){
                            v.animate().x(550.0f).setDuration(100).start();
                        }*/
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        if (izquierdaoderecha == 0){
                            v.animate().x(650.0f).setDuration(100).start();
                            Seleccion.setBackgroundResource(R.color.verdeok);
                        }
                        if (izquierdaoderecha == 1){
                            v.animate().x(0.0f).setDuration(100).start();
                            Seleccion.setBackgroundResource(R.color.naranja);
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });



        return holder;
    }

    @Override
    public void onBindViewHolder(final ArticulosMiniViewHolder holder, int position) {
        Articulo articulo = Larticulos.get(position);
        holder.Titulo.setText(articulo.getmTitulo());
        holder.Rango.setText(articulo.getmRango());
        mId = articulo.getmId();
        mCategoria = articulo.getmCategoria();
        /*switch (mCategoria){
            case 0: holder.Categoria.setImageResource(R.drawable.phone);
                break;
            case 1: holder.Categoria.setImageResource(R.drawable.laptop);
                break;
            case 2: holder.Categoria.setImageResource(R.drawable.clothes);
                break;
            case 3: holder.Categoria.setImageResource(R.drawable.watch);
                break;
            case 4: holder.Categoria.setImageResource(R.drawable.nintendo);
                break;
            case 5: holder.Categoria.setImageResource(R.drawable.car);
                break;
            case 6: holder.Categoria.setImageResource(R.drawable.baby);
                break;
            case 7: holder.Categoria.setImageResource(R.drawable.soccerball);
                break;
            case 8: holder.Categoria.setImageResource(R.drawable.microwave);
                break;
            case 9: holder.Categoria.setImageResource(R.drawable.chip);
                break;
            case 10: holder.Categoria.setImageResource(R.drawable.home);
                break;
            case 11: holder.Categoria.setImageResource(R.drawable.guitarr);
                break;
            case 12: holder.Categoria.setImageResource(R.drawable.toy);
                break;
            case 13: holder.Categoria.setImageResource(R.drawable.book);
                break;
            case 14: holder.Categoria.setImageResource(R.drawable.beauty);
                break;
            case 15: holder.Categoria.setImageResource(R.drawable.cd);
                break;
        }*/

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://trueque-de-garage.appspot.com/");
        StorageReference imagePrincipalRef = storageRef.child("Articulos/"+mId+"/0.jpg");
        imagePrincipalRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("Mylog","Cargo el holder");
                Glide.with(context).load(uri).centerCrop().fitCenter().into(holder.Foto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Mylog","Error en el holder");
            }
        });

        Glide.with(context).load(uri).fitCenter().centerCrop().into(holder.Foto);
    }

    @Override
    public int getItemCount() {
        return Larticulos.size();
    }

    public static class ArticulosMiniViewHolder extends RecyclerView.ViewHolder {


        TextView Titulo, Rango;
        ImageView Foto, Categoria;

        public ArticulosMiniViewHolder(View itemView){
            super(itemView);
            Titulo = (TextView) itemView.findViewById(R.id.txt_view_titulo_row_mini_recycler);
            Rango = (TextView) itemView.findViewById(R.id.txt_view_costo_row_mini_recycler);
            Foto = (ImageView) itemView.findViewById(R.id.img_view_foto_row_mini_recycler_holder);
            //Seleccion = (LinearLayout) itemView.findViewById(R.id.ln_layout_row_mini_recycler_holder);
            /*Categoria = (ImageView) itemView.findViewById(R.id.img_view_icon_row_mini_recycler);*/

        }

    }

}
