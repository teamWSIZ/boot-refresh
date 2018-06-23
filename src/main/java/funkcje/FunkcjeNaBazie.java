package funkcje;

import lombok.extern.slf4j.Slf4j;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
class DbUpdater {
    Connection cn = null;

    void init(){
        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection("jdbc:postgresql://10.10.22.255:5432/student", "student", "wsiz#1234");
            //showMessageDialog(null, "Baza danych dostępna");
        } catch (Exception e) {
            log.error("Wystąpił błąd ", e);
        }
        log.info("Mamy połączenie z bazą");
    }

    void updateQuestionType(int qtid, String newDescription) {
        CallableStatement cst = null;
        try {
//        select * from updatetype(29, 'Medicine');
            cst = cn.prepareCall("{call egzamin.updatetype(?,?)}");
            cst.setInt(1, 29);
            cst.setString(2, "AbraKadabra");
        } catch (SQLException e) {
            log.error("Błąd tworzenia statement-u");
        }
        log.info("Przygotowano statement");

        try {
            cst.execute();
        } catch (SQLException e) {
            log.error("Błąd wykonania statement-u: ", e);
        }
        log.info("Update typu pytań OK");
    }

    void updateQuestion(int qid, String tekstpytania, int correctanswer) {
        CallableStatement cst = null;
        try {
//            select * from updatepytanie(63, 'pytanie 63', 1);
            cst = cn.prepareCall("{call egzamin.updatepytanie(?,?,?)}");
            cst.setInt(1, qid);
            cst.setString(2, tekstpytania);
            cst.setInt(3, correctanswer);
        } catch (SQLException e) {
            log.error("Błąd tworzenia statement-u");
        }
        log.info("Przygotowano statement");

        try {
            cst.execute();
        } catch (SQLException e) {
            log.error("Błąd wykonania statement-u: ", e);
            return;
        }
        log.info("Update pytania OK");
    }

    void destroy() {
        try {
            cn.close();
        } catch (SQLException e) {
            log.error("Nie można zamknąć połączenia", e);
        }
    }


}


@Slf4j
public class FunkcjeNaBazie {
    public static void main(String[] args) throws Exception {
        DbUpdater dbUpdater = new DbUpdater();
        dbUpdater.init();

//        dbUpdater.updateQuestionType(29, "Music");
        dbUpdater.updateQuestion(63, "Kadabra question!", 2);


        dbUpdater.destroy();
    }
}
