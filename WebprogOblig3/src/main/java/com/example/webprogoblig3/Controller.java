package com.example.webprogoblig3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {
    @Autowired
    BillettRepository rep;

    @Autowired
    private HttpSession session;

    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping("/lagre")
    public void lagreBillett(Billett innBillett){
        rep.lagreBillett(innBillett);
    }

    @GetMapping("/hentBillett")
    public List<Billett> hentBillett(HttpServletResponse response) throws IOException {
        List<Billett> alleBilletter = new ArrayList<>();
        if (session.getAttribute("innLogget")!=null){
            alleBilletter= rep.hentBillett();
            if(alleBilletter == null){
                response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
            }
            return alleBilletter;
        }
        response.sendError(HttpStatus.NOT_FOUND.value());
        return null;
    }

    @GetMapping("/clearArray")
    public void clearArray(){
        rep.clearArray();
    }

    @PostMapping("/login")
    public boolean loggInn(Bruker bruker){
        if (rep.loggInn(bruker)){
            session.setAttribute("innLogget", bruker);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/registrer")
    public void registrer(Bruker nyBruker){ rep.registrer(nyBruker);}

    @GetMapping("/loggUt")
    public void loggUt(){session.removeAttribute("innLogget");}
}
