package ua.vesa.osnova.speed.TRAStation.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "tra_station")
public class TRAModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(nullable = false)
    private String title;
    private long data;
    @Column(unique = true)
    private int id_station;

    public TRAModel(int id) {
        this.id = id;
    }

    public TRAModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public int getId_station() {
        return id_station;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
    }
}
