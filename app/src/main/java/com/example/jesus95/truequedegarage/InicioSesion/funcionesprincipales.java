package com.example.jesus95.truequedegarage.InicioSesion;

/**
 * Created by jesus95 on 05/06/17.
 */

public class funcionesprincipales {

    public int checarpassword(String password){
        int n;
        try{
            n = Integer.parseInt(password);
        }catch (Exception e){
            n = 0;
        }
        return n;
    }

    public int checarlongitud(String password){
        int n;
        n = password.length();
        return n;
    }

    public String ModificarNumeroUsuarios(String numero){
        int n;
        String mString = "";
        n = Integer.parseInt(numero);
        if (n<1000){
            if (n<100){
                if (n<10){
                    mString = "000"+(n+1);
                }else{
                    //mayor a los 10
                    mString = "00"+(n+1);
                }
            }else{//de los 100
                mString = "0"+(n+1);
            }
        }else{//de los 1000
            mString = ""+(n+1);
        }
        return mString;
    };

    public String Contadorparabusqueda(int numero){
        String mString = "";
        if (numero<1000){
            if (numero<100){
                if (numero<10){
                    mString = "a000"+(numero);
                }else{
                    //mayor a los 10
                    mString = "a00"+(numero);
                }
            }else{//de los 100
                mString = "a0"+(numero);
            }
        }else{//de los 1000
            mString = "a"+(numero);
        }
        return mString;
    };



}
