package dao;

import dto.FinishedMatchDto;
import model.entity.Match;
import exception.DatabaseException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static service.FinishedMatchService.PAGE_SIZE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchDao implements Dao<Long, Match> {

    public static final MatchDao MATCH_DAO = new MatchDao();
    public static final String FROM_MATCH = "from Match";
    public static final String FROM_MATCH_BY_NAME_HQL = "from Match where player1.name ilike :filterByPlayerName or player2.name ilike :filterByPlayerName";

    @Override
    public void save(Match entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException exception) {
            throw new DatabaseException("Database error.");
        }
    }

    @Override
    public Optional<Match> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Match> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FROM_MATCH, Match.class).list();
        } catch (HibernateException exception) {
            throw new DatabaseException("Database error.");
        }
    }

    @Override
    public void update(Match entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Match entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Match> findAllPaginated(int offset, int maxResults) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Match> finishedMatches = session.createQuery(FROM_MATCH, Match.class).setFirstResult(offset).setMaxResults(maxResults).list();
            session.getTransaction().commit();
            return finishedMatches;
        } catch (HibernateException exception) {
            throw new DatabaseException("Database error.");
        }
    }

    public List<Match> findFilteredByPlayerNamePaginated(int offset, int maxResults, String filterByPlayerName) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Match> filteredFinishedMatches = session.createQuery(FROM_MATCH_BY_NAME_HQL, Match.class)
                    .setParameter("filterByPlayerName", "%" + filterByPlayerName + "%")
                    .setFirstResult(offset)
                    .setMaxResults(maxResults)
                    .list();
            session.getTransaction().commit();
            return filteredFinishedMatches;
        }
    }
}
