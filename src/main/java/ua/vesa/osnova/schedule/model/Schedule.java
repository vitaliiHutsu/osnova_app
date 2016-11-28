package ua.vesa.osnova.schedule.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private int number_train;
    private long date_add_or_update;
    @Column(columnDefinition = "TEXT")
    private String notation;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "schedule_id")
    @JsonManagedReference
    private List<DepartureAndArrival> departureAndArrivals;

    public Schedule(int id) {
        this.id = id;
    }

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_train() {
        return number_train;
    }

    public void setNumber_train(int number_train) {
        this.number_train = number_train;
    }

    public long getDate_add_or_update() {
        return date_add_or_update;
    }

    public void setDate_add_or_update(long date_add_or_update) {
        this.date_add_or_update = date_add_or_update;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public List<DepartureAndArrival> getDepartureAndArrivals() {
        return departureAndArrivals;
    }

    public void setDepartureAndArrivals(List<DepartureAndArrival> departureAndArrivals) {
        this.departureAndArrivals = departureAndArrivals;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", number_train=" + number_train +
                ", date_add_or_update=" + date_add_or_update +
                ", notation='" + notation + '\'' +
                ", departureAndArrivals=" + departureAndArrivals.size() +
                '}';
    }
}
