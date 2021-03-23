package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/anime")
public final class AnimeServlet extends HttpServlet {
    
    public class Anime {
        String name;
        String description;
        double rating;
        
        public Anime(String animeName, String desc, double rate)
        {
            name = animeName;
            description = desc;
            rating = rate;
        }
    }

    

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ArrayList<Anime> animes = new ArrayList<Anime>();

        Anime anime1 = new Anime("Jojo's Bizzare Adventure", 
                                 "This is one of my favorite animes and it only gets better with every part!", 
                                 10);
        Anime anime2 = new Anime("Death Note", 
                                 "I loved episodes 1-25 but afterwards I wasn't much of a fan. The ending was still beautiful nonetheless and allows me to give it a proud 9.", 
                                 9);
        Anime anime3 = new Anime("Promised Neverland", 
                                 "I have only watched the first season but I thought it was such a beauitful anime! Every episode introduced a new detail and it was such a thrill to watch.", 
                                 9);
        Anime anime4 = new Anime("Dragon Ball Super", 
                                 "I've always been a Dragon Ball fan and Dragon Ball Super was an okay anime. The anime in the beginning was a bit rough but the Tournament of Power arc was great!",
                                 7.5);
        Anime anime5 = new Anime("Haikyuu!!", 
                                 "This anime almost motivated me to play volleyball and is very inspiring! Loved every moment the team went through.",
                                 8);
        Anime anime6 = new Anime("Neon Genesis Evangelion", 
                                 "This is more than just a mecha anime. It is a psychological journey of a boy who was neglected by his father and is a pure masterpiece. It is so easy to get frustrated at Shinji but you begin to understand as you watch.",
                                 9.5);
        Anime anime7 = new Anime("Dr. Stone", 
                                 "I learned so much about chemistry in this anime and it's so interesting!!! It really motivated me to take my Biology class!",
                                 9);
        Anime anime8 = new Anime("Baki", 
                                 "Baki is such a fantastic fighting anime but there's so much focus on side characters that it makes it hard to watch. It's very slow in the beginning but Baki himself is so impressive.",
                                 7.5);

        animes.add(anime1);
        animes.add(anime2);
        animes.add(anime3);
        animes.add(anime4);
        animes.add(anime5);
        animes.add(anime6);
        animes.add(anime7);
        animes.add(anime8);

        String result = convertToJsonUsingGson(animes);

        // Send the JSON as the response
        response.setContentType("application/json;");
        response.getWriter().println(result);

  }

  private String convertToJsonUsingGson(ArrayList<Anime> animes) {
        Gson gson = new Gson();
        String json = gson.toJson(animes);
    return json;
  }
}