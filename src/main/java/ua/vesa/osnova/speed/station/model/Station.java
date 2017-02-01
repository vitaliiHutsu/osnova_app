package ua.vesa.osnova.speed.station.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import ua.vesa.osnova.schedule.model.DepartureAndArrival;
import ua.vesa.osnova.schedule.model.RouteSchedule;
import ua.vesa.osnova.speed.TRAStation.model.TRAModel;
import ua.vesa.osnova.speed.route_speed.model.RouteSpeed;
import ua.vesa.osnova.speed.station.valid.StationValid;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "station_speed")
public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Size(min = 3, max = 40, message = "Короткое название")
    @StationValid
    @NotEmpty
    @Column(name = "title_station", length = 30, unique = true)
    private String title_station;
    @Column(nullable = false)
    private Boolean notStation;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "station_id")
    @JsonBackReference
    private List<DepartureAndArrival> departureAndArrivals = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "route_schedule_station", joinColumns = @JoinColumn(name = "station_route_id"),
    inverseJoinColumns = @JoinColumn(name = "route_station_id"))
    @JsonBackReference
    private List<RouteSchedule> routeSchedules = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany()
    @JoinTable(name = "route_speed_station", joinColumns = @JoinColumn(name = "station_route_speed_id"),
            inverseJoinColumns = @JoinColumn(name = "route_speed_station_id"))
    @JsonBackReference
    private List<RouteSpeed> routeSpeeds = new ArrayList<>();
    private int speed_chief_even_pass; //гл. чет. пасс
    private int speed_chief_odd_pass;  //гл. не чет. пасс
    private int speed_chief_even_freight; //гл. чет. груз
    private int speed_chief_odd_freight; //гл. не чет. груз
    private int branch_track; // боковой путь

    private Float km_pk; //км.пк станции

    private Long date;

    @Column(name = "notation", length = 500)
    private String notation;

    public Station(int id) {
        this.id = id;
    }

    public Station() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_station() {
        return title_station;
    }

    public void setTitle_station(String title_station) {
        this.title_station = title_station;
    }

    public int getSpeed_chief_even_pass() {
        return speed_chief_even_pass;
    }

    public void setSpeed_chief_even_pass(int speed_chief_even_pass) {
        this.speed_chief_even_pass = speed_chief_even_pass;
    }

    public int getSpeed_chief_odd_pass() {
        return speed_chief_odd_pass;
    }

    public void setSpeed_chief_odd_pass(int speed_chief_odd_pass) {
        this.speed_chief_odd_pass = speed_chief_odd_pass;
    }

    public int getSpeed_chief_even_freight() {
        return speed_chief_even_freight;
    }

    public void setSpeed_chief_even_freight(int speed_chief_even_freight) {
        this.speed_chief_even_freight = speed_chief_even_freight;
    }

    public int getSpeed_chief_odd_freight() {
        return speed_chief_odd_freight;
    }

    public void setSpeed_chief_odd_freight(int speed_chief_odd_freight) {
        this.speed_chief_odd_freight = speed_chief_odd_freight;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public int getBranch_track() {
        return branch_track;
    }

    public void setBranch_track(int branch_track) {
        this.branch_track = branch_track;
    }

    public List<DepartureAndArrival> getDepartureAndArrivals() {
        return departureAndArrivals;
    }

    public void setDepartureAndArrivals(List<DepartureAndArrival> departureAndArrivals) {
        this.departureAndArrivals = departureAndArrivals;
    }

    public List<RouteSchedule> getRouteSchedules() {
        return routeSchedules;
    }

    public void setRouteSchedules(List<RouteSchedule> routeSchedules) {
        this.routeSchedules = routeSchedules;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Boolean getNotStation() {
        return notStation;
    }

    public void setNotStation(Boolean notStation) {
        this.notStation = notStation;
    }

    public Float getKm_pk() {
        return km_pk;
    }

    public void setKm_pk(Float km_pk) {
        this.km_pk = km_pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (branch_track != station.branch_track) return false;
        if (id != station.id) return false;
        if (speed_chief_even_freight != station.speed_chief_even_freight) return false;
        if (speed_chief_even_pass != station.speed_chief_even_pass) return false;
        if (speed_chief_odd_freight != station.speed_chief_odd_freight) return false;
        if (speed_chief_odd_pass != station.speed_chief_odd_pass) return false;
        if (date != null ? !date.equals(station.date) : station.date != null) return false;
        if (departureAndArrivals != null ? !departureAndArrivals.equals(station.departureAndArrivals) : station.departureAndArrivals != null)
            return false;
        if (km_pk != null ? !km_pk.equals(station.km_pk) : station.km_pk != null) return false;
        if (notStation != null ? !notStation.equals(station.notStation) : station.notStation != null) return false;
        if (notation != null ? !notation.equals(station.notation) : station.notation != null) return false;
        if (routeSchedules != null ? !routeSchedules.equals(station.routeSchedules) : station.routeSchedules != null)
            return false;
        if (routeSpeeds != null ? !routeSpeeds.equals(station.routeSpeeds) : station.routeSpeeds != null) return false;
        if (title_station != null ? !title_station.equals(station.title_station) : station.title_station != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = title_station.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", title_station='" + title_station + '\'' +
                ", notStation=" + notStation +
                ", departureAndArrivals=" + departureAndArrivals +
                ", routeSchedules=" + routeSchedules +
                ", routeSpeeds=" + routeSpeeds +
                ", speed_chief_even_pass=" + speed_chief_even_pass +
                ", speed_chief_odd_pass=" + speed_chief_odd_pass +
                ", speed_chief_even_freight=" + speed_chief_even_freight +
                ", speed_chief_odd_freight=" + speed_chief_odd_freight +
                ", branch_track=" + branch_track +
                ", km_pk=" + km_pk +
                ", date=" + date +
                ", notation='" + notation + '\'' +
                '}';
    }

}
