/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.model;

import java.time.*;
/**
 *
 * @author Familia
 */
public class Notificacion {
    private int id;
    private String mensaje;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    
    public Notificacion (int Id, String Mensaje, LocalDateTime Inicio, LocalDateTime Fin, int autor) {
        this.id = Id;
        this.mensaje = Mensaje;
        this.inicio = Inicio;
        this.fin = Fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }
}
