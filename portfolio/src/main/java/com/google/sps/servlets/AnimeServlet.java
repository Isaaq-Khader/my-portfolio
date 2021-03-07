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
        int rating;
        public Anime(String animeName, int rate)
        {
            name = animeName;
            rating = rate;
        }
    }

    

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Anime animes[];
        // animes[0] = new Anime("Jojo's Bizzare Adventure", 10);

        ArrayList<Anime> animes = new ArrayList<Anime>();

        Anime anime1 = new Anime("Jojo's Bizzare Adventure", 10);
        Anime anime2 = new Anime("Death Note", 9);

        animes.add(anime1);
        animes.add(anime2);

        // needs random functionality but hard-coded for now
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