package api.data.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;

import api.messages.Config;
import pl.samuel.skygen.core;
import pl.samuel.skygen.utils.Logger;
import pl.samuel.skygen.utils.TimeUtil;

public class Database {

    private static String host;
    private static String user;
    private static String pass;
    private static String name;
    private static String prefix;
    private static int port;
    private static Connection conn;
    private static long time;
    private static Executor executor;
    private static AtomicInteger ai;

    public Database(final String host, final int port, final String user, final String pass, final String name) {
        Database.host = Config.DATABASE_MYSQL_HOST;
        Database.port = Config.DATABASE_MYSQL_PORT;
        Database.user = Config.DATABASE_MYSQL_USER;
        Database.pass = Config.DATABASE_MYSQL_PASS;
        Database.name = Config.DATABASE_MYSQL_NAME;
        Database.prefix = "{P}";
        Database.executor = Executors.newSingleThreadExecutor();
        Database.time = System.currentTimeMillis();
        Database.ai = new AtomicInteger();
        connect();
        Bukkit.getScheduler().runTaskTimer(core.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Database.query("SELECT CURTIME()");
                Logger.warning("Odswiezono mysql");
            }
        }, TimeUtil.MINUTE.getTick(35), TimeUtil.MINUTE.getTick(35));
    }

    public static void executeQuery(final String query) {
        if (Database.conn == null) {
            connect();
        }
        try {
            final Statement st = getConnection1().createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
    }


    public static ResultSet executeQuery1(final String query) {
        if (Database.conn == null) {
            connect();
        }
        try {
            final Statement st = getConnection1().createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection1() {
        if (Database.conn == null) {
            connect();
        }
        return Database.conn;
    }

    public static boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Database.conn = DriverManager.getConnection("jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.name, Database.user, Database.pass);
            Logger.info("Zaladowano serwer MySQL server!");
            return true;
        } catch (ClassNotFoundException var1) {
            Logger.warning("JDBC driver not found!", "Error: " + var1.getMessage());
            var1.printStackTrace();
        } catch (SQLException var2) {
            Logger.warning("Can not connect to a MySQL server!", "Error: " + var2.getMessage());
            var2.printStackTrace();
        }
        return false;
    }

    public static void update(final boolean now, final String update) {
        Database.time = System.currentTimeMillis();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Database.conn.createStatement().executeUpdate(update.replace("{P}", Database.prefix));
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }
        };
        if (now) {
            r.run();
        } else {
            Database.executor.execute(r);
        }
    }

    public static ResultSet update(final String update) {
        try {
            final Statement statement = Database.conn.createStatement();
            statement.executeUpdate(update.replace("{P}", Database.prefix), 1);
            final ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs;
            }
        } catch (SQLException var3) {
            Logger.warning("An error occurred with given query '" + update.replace("{P}", Database.prefix) + "'!", "Error: " + var3.getMessage());
            var3.printStackTrace();
        }
        return null;
    }

    public static void disconnect() {
        if (Database.conn != null) {
            try {
                Database.conn.close();
            } catch (SQLException var1) {
                Logger.warning("Can not close the connection to the MySQL server!", "Error: " + var1.getMessage());
                var1.printStackTrace();
            }
        }
    }

    public static void reconnect() {
        connect();
    }

    public static boolean isConnected() {
        try {
            return !Database.conn.isClosed() || Database.conn == null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet query(final String query) {
        try {
            return Database.conn.createStatement().executeQuery(query.replace("{P}", Database.prefix));
        } catch (SQLException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static void query1(final String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Database.conn.createStatement().executeQuery(query.replace("{P}", Database.prefix));
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }
        }, "MySQL Thread #" + Database.ai.getAndIncrement()).start();
    }

    public static Connection getConnection() {
        return Database.conn;
    }

    public static void disableMySQL() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException var1) {
            var1.printStackTrace();
        }
        if (isConnected()) {
            disconnect();
        }
    }

    public static ResultSet getQueryResult(final String query) {
        ResultSet rs = null;
        try {
            final Statement st = getConnection1().createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return rs;
    }
}
