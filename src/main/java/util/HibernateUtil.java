package util;

import exception.ResourcesException;
import model.entity.Match;
import model.entity.Player;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@UtilityClass
public class HibernateUtil {

    private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";

    @Getter
    private static final SessionFactory sessionFactory = new Configuration()
            .configure(HIBERNATE_CFG_XML)
            .addAnnotatedClass(Match.class)
            .addAnnotatedClass(Player.class)
            .buildSessionFactory();

    private static final String INIT_DATA_SQL = "init_data.sql";

    public static void initDatabase() {
        try (InputStream rawInitSql = HibernateUtil.class.getClassLoader().getResourceAsStream(INIT_DATA_SQL)) {
            byte[] initSql = Objects.requireNonNull(rawInitSql).readAllBytes();
            String sql = new String(initSql);
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException exception) {
            throw new ResourcesException("Resources not found.");
        }
    }
}
