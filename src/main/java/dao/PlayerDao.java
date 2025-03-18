package dao;

import model.entity.Player;
import exception.DatabaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerDao implements Dao<Long, Player> {

    public static final PlayerDao PLAYER_DAO = new PlayerDao();
    public static final String FROM_PLAYER_BY_NAME_HQL = "from Player where name = :name";

    @Override
    public void save(Player entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            throw new DatabaseException("Database error.");
        }
    }

    @Override
    public Optional<Player> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public List<Player> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Player entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Player entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Optional<Player> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FROM_PLAYER_BY_NAME_HQL, Player.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
        } catch (HibernateException exception) {
            throw new DatabaseException("Database error.");
        }
    }
}
