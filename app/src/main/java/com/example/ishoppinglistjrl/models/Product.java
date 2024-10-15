package com.example.ishoppinglistjrl.models;

import java.io.Serializable;

public class Product implements Serializable {
    int id;
    String name;
    String note;
    boolean state;

    //Cosntructor vacío
    public Product(){

    }

    /**
     * Constructor con los atributos de la clase Producut
     *
     * @param id    Id del producto
     * @param name  Nombre del producto
     * @param note  Nota informativa
     * @param state Estado del producto
     */
    public Product(int id, String name, String note, boolean state) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.state = state;
    }

    //Todos los getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    //Método toString() que convierte los atributos a texto
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", state=" + state +
                '}';
    }
}
