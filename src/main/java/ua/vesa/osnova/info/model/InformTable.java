package ua.vesa.osnova.info.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "info_table")
public class InformTable implements Serializable{

    //Хранения title в таблице inform stage по уникальному ключу uniq_station


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name_table;
    @Column(length = 500)
    private String title;
    private long date_add;
    @Column(length = 500)
    private String action;
    @Column
    private String uuid;


    public InformTable() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InformTable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_table() {
        return name_table;
    }

    public void setName_table(String name_table) {
        this.name_table = name_table;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate_add() {
        return date_add;
    }

    public void setDate_add(long date_add) {
        this.date_add = date_add;
    }
}
