package com.google.sps.servlets;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sentiment")
public class SentimentAnalyzerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String msg = request.getParameter("message");

    Document doc =
        Document.newBuilder().setContent(msg).setType(Document.Type.PLAIN_TEXT).build();
    LanguageServiceClient languageService = LanguageServiceClient.create();
    Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
    float score = sentiment.getScore();
    languageService.close();

    // am i supposed to do computation here and then present it as HTML to the webpage?
    String result = convertToJsonUsingGson(score);

     // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(result);
  }

  private String convertToJsonUsingGson(float score) {
        Gson gson = new Gson();
        String json = gson.toJson(score);
        return json;
    }
}
