package com.example.jesus95.truequedegarage.Articulos;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jesus95.truequedegarage.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.List;

/**
 * Created by jesus95 on 27/08/17.
 */

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloviewHolder>{

    List<Articulo> Larticulos;
    int mCategoria = 0;
    String mId;
    Bitmap Imagen;
    Uri uri;
    private Cursor items;
    Context context;


    public ArticuloAdapter(Context context, List<Articulo> Larticulos){
        this.Larticulos = Larticulos;
        this.context = context;
    }

    @Override
    public ArticuloviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
        ArticuloviewHolder holder = new ArticuloviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ArticuloviewHolder holder, int position) {
        Articulo articulo = Larticulos.get(position);
        holder.Titulo.setText(articulo.getmTitulo());
        holder.Rango.setText(articulo.getmRango());
        mId = articulo.getmId();
        mCategoria = articulo.getmCategoria();
        switch (mCategoria){
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
        }

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

        /*try{
            StorageReference imagePrincipalRef = storageRef.child("Articulos/"+mId+"/0.jpg");
            final File localFilePrincipal = File.createTempFile("ImagenP","jpg");
            imagePrincipalRef.getFile(localFilePrincipal).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Imagen = BitmapFactory.decodeFile(localFilePrincipal.getAbsolutePath());
                    holder.Foto.setImageBitmap(Imagen);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    holder.Foto.setImageResource(R.drawable.failtask);
                }
            });
        }catch (Exception e){
            Log.e("Mylog","Excepcion: "+e);
        }*/
    }

    @Override
    public int getItemCount() {
        return Larticulos.size();
    }

    public static class ArticuloviewHolder extends RecyclerView.ViewHolder {

        TextView Titulo, Rango;
        ImageView Foto, Categoria;


        public ArticuloviewHolder(View itemView){
            super(itemView);
            Titulo = (TextView) itemView.findViewById(R.id.txt_view_Titulo_Holder);
            Rango = (TextView) itemView.findViewById(R.id.txt_view_Rango_Holder);
            Foto = (ImageView) itemView.findViewById(R.id.image_view_image_holder);
            Categoria = (ImageView) itemView.findViewById(R.id.image_view_icon_holder);


        }

    }



}
