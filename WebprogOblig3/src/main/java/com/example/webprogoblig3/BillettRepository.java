package com.example.webprogoblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillettRepository {

    @Autowired
    private JdbcTemplate db;


    public void lagreBillett(Billett billett){
        String sql ="INSERT INTO Billett (filmer, antall, etternavn , fornavn, nummer, epost) VALUES(?,?,?,?,?,?)";
        db.update(sql,billett.getFilmer(), billett.getAntall(), billett.getEtternavn(), billett.getFornavn(),
                billett.getNummer(), billett.getEpost());
    }
    public List<Billett> hentBillett(){
        String sql = "SELECT * FROM Billett ORDER BY etternavn";
        List<Billett> alleBilletter = db.query(sql, new BeanPropertyRowMapper<>(Billett.class));
        return alleBilletter;
    }

    public void clearArray(){
        String sql ="DELETE FROM Billett";
        db.update(sql);
    }

    public void registrer(Bruker bruker){
        String sql= "INSERT INTO Bruker(brukernavn, passord) VALUES (?,?)";
        try{
            db.update(sql,bruker.getBrukernavn(), bruker.getPassord());
        }
       catch (Exception e){
            return;
       }
    }

    public boolean loggInn(Bruker bruker){
        Object[] param = new Object[]{bruker.getBrukernavn(), bruker.getPassord()};
        String sql = "SELECT COUNT(*) FROM Bruker WHERE brukernavn = ? AND passord = ?";
        try {
            int funnetEnBruker = db.queryForObject(sql, param, Integer.class);
            if (funnetEnBruker>0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


}