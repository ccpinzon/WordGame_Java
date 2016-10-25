package devKoding.logic;

import devKoding.Persistence.Conection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by cristhian on 24/10/16.
 */
public class HaldingWords {

    private ArrayList<String> words;
    private Conection conection;


    public HaldingWords() throws SQLException, ClassNotFoundException {
        words = new ArrayList<>();
        conection = new Conection();
        loadWords();
    }


    public void loadWords() throws ClassNotFoundException, SQLException {
        String sql = "select sin_acentos from palabras";

        try(Connection conn = conection.Connect();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql)){

            while (rs.next()){
                words.add(rs.getString("sin_acentos"));
            }
        }catch (SQLException e){
            System.out.printf("ERROR CONSULTA" + e.getMessage());
        }

    }

    public boolean findWord(String wordSearch){
        for (String word:words)
            if(wordSearch.equals(word))
                return true;
       return false;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }
}