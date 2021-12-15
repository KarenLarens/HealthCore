package com.example.registration;

public class PerfilFirebase{
    private String Nombre,ApePat,ApeMat,Edad,Sexo;
    private String Estatura,Peso,Alergias,TSangre;

    public PerfilFirebase() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApePat() {
        return ApePat;
    }

    public void setApePat(String apePat) {
        ApePat = apePat;
    }

    public String getApeMat() {
        return ApeMat;
    }

    public void setApeMat(String apeMat) {
        ApeMat = apeMat;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getEstatura() {
        return Estatura;
    }

    public void setEstatura(String estatura) {
        Estatura = estatura;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getAlergias() {
        return Alergias;
    }

    public void setAlergias(String alergias) {
        Alergias = alergias;
    }

    public String getTSangre() {
        return TSangre;
    }

    public void setTSangre(String TSangre) {
        this.TSangre = TSangre;
    }
}