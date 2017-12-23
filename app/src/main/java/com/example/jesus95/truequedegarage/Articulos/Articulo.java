package com.example.jesus95.truequedegarage.Articulos;

/**
 * Created by jesus95 on 11/07/17.
 */

public class Articulo {
    String mTitulo, mDescripcion, mUid, mId, mRango, mFechayHora;
    int mViews, mCategoria, mNumeroArticulo;

    public Articulo(){

    }

    public Articulo(String mTitulo, String mDescripcion, String mUid, String mId, String mRango, int mViews, String mFechayHora,
                    int mCategoria, int mNumeroArticulo){
        this.mTitulo = mTitulo;
        this.mDescripcion = mDescripcion;
        this.mUid = mUid;
        this.mId = mId;
        this.mRango = mRango;
        this.mViews = mViews;
        this.mFechayHora = mFechayHora;
        this.mCategoria = mCategoria;
        this.mNumeroArticulo = mNumeroArticulo;
    }

    public String getmTitulo() {
        return mTitulo;
    }

    public void setmTitulo(String mTitulo) {
        this.mTitulo = mTitulo;
    }

    public String getmDescripcion() {
        return mDescripcion;
    }

    public void setmDescripcion(String mDescripcion) {
        this.mDescripcion = mDescripcion;
    }

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmRango() {
        return mRango;
    }

    public void setmRango(String mRango) {
        this.mRango = mRango;
    }

    public int getmViews() {
        return mViews;
    }

    public void setmViews(int mViews) {
        this.mViews = mViews;
    }

    public int getmCategoria() {
        return mCategoria;
    }

    public void setmCategoria(int mCategoria) {
        this.mCategoria = mCategoria;
    }

    public int getmNumeroArticulo() {
        return mNumeroArticulo;
    }

    public void setmNumeroArticulo(int mNumeroArticulo) {
        this.mNumeroArticulo = mNumeroArticulo;
    }

    public String getmFechayHora() {
        return mFechayHora;
    }

    public void setmFechayHora(String mFechayHora) {
        this.mFechayHora = mFechayHora;
    }
}
