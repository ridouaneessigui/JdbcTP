package com.example.jdbctp;

public class Personne {
    private int id;
    private String name;
    private String prenom;
    private String tel;

    public Personne(String name, String prenom, String tel) {
        super();
    }

    public Personne(int id, String name, String prenom, String tel) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
        this.tel = tel;
    }

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
