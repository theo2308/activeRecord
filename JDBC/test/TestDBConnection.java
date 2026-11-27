import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestDBConnection {

    private Connection con1, con2;

    @BeforeEach
    public void init() throws SQLException {
        con1 = DBConnection.getConnection();
    }
    @Test
    public void testConstruct(){
        try {
            con2 = DBConnection.getConnection();
            assertNotEquals(con1, null);
            assertEquals(con1, con2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSetNomDB(){
        try {
            DBConnection.setNomDB("Iks");
            con2 = DBConnection.getConnection();
            assertNotEquals(con1, con2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
