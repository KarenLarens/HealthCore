package com.example.registration;

public class RateFirebase {
    private String comentario, correo;
    private Float estrellas;

    public RateFirebase() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Float getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Float estrellas) {
        this.estrellas = estrellas;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
