package ua.vesa.osnova.speed.stage.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.vesa.osnova.speed.stage.model.Stage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class StageDaoImpl implements StageDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Stage> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Stage.class);
        criteria.addOrder(Order.asc("startStation"));
        return criteria.list();
    }

    @Override
    public List<Stage> getAllOrderByDate() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Stage.class);
        criteria.addOrder(Order.desc("date"));
        criteria.setMaxResults(15);

        return criteria.list();
    }

    @Override
    public List<Stage> getStages(List<String> station) {
        List<Stage> stages = new ArrayList<>();
        Criteria criteria;
        for (int i = 0; i < station.size() - 1; i++) {
            criteria = sessionFactory.getCurrentSession().createCriteria(Stage.class);
            Criterion res1 = Restrictions.and(Restrictions.eq("startStation", station.get(i)), Restrictions.eq("endStation", station.get(i + 1)));
            Criterion res2 = Restrictions.and(Restrictions.eq("endStation", station.get(i)), Restrictions.eq("startStation", station.get(i + 1)));
            criteria.add(Restrictions.or(res1, res2));
            stages.add((Stage) criteria.uniqueResult());
        }
        return stages;
    }

    @Override
    public Stage getByStations(String firstStation, String secondStation) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Stage.class);
        Criterion res1 = Restrictions.and(Restrictions.eq("startStation", firstStation), Restrictions.eq("endStation", secondStation));
        Criterion res2 = Restrictions.and(Restrictions.eq("startStation", secondStation), Restrictions.eq("endStation", firstStation));
        criteria.add(Restrictions.or(res1, res2));
        return (Stage) criteria.uniqueResult();
    }

    @Override
    public Stage getById(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Stage.class);
        criteria.add(Restrictions.eq("id", id));
        return (Stage) criteria.uniqueResult();
    }

    @Override
    public void add(Stage stage) {
        stage.setDate(GregorianCalendar.getInstance().getTimeInMillis());
        sessionFactory.getCurrentSession().saveOrUpdate(stage);
    }

    @Override
    public void addAll(List<List<String>> listStage) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("INSERT IGNORE INTO stage_speed " +
                "(endStation, startStation, stage_uniq, date) VALUES (:endStation, :startStation, :stage_uniq, :date)");

        for (List<String> list:listStage){
            query.setParameter("startStation", list.get(0));
            query.setParameter("endStation", list.get(1));
            query.setParameter("stage_uniq", list.get(0).hashCode() + list.get(1).hashCode());
            query.setParameter("date", GregorianCalendar.getInstance().getTimeInMillis());
            query.executeUpdate();
        }
    }

    @Override
    public void update(Stage stage) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Stage.class);
        criteria.add(Restrictions.eq("id", stage.getId()));
        Stage updateStage = (Stage) criteria.uniqueResult();

        updateStage.setSpeed_pass_even(stage.getSpeed_pass_even());
        updateStage.setSpeed_freight_even(stage.getSpeed_freight_even());
        updateStage.setSpeed_pass_odd(stage.getSpeed_pass_odd());
        updateStage.setSpeed_freight_odd(stage.getSpeed_freight_odd());
        updateStage.setNotation_even(stage.getNotation_even());
        updateStage.setNotation_odd(stage.getNotation_odd());




        updateStage.setDate(Calendar.getInstance().getTimeInMillis());

        session.saveOrUpdate(updateStage);
    }

    @Override
    public void remove(Stage stage) {

    }
}
