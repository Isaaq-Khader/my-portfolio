package com.google.sps.servlets;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sentiment")
public class SentimentAnalyzerServlet extends HttpServlet {

    Gson gson = new Gson();

    public class UserMessage {
        private String message;
    }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String msg = request.getReader().lines().collect(Collectors.joining());
    UserMessage m = new UserMessage();

    m = gson.fromJson(msg, UserMessage.class);

    System.out.println(m.message);

    System.out.println("Going to get score... ");
    Document doc =
        Document.newBuilder().setContent(m.message).setType(Document.Type.PLAIN_TEXT).build();
    LanguageServiceClient languageService = LanguageServiceClient.create();
    Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
    float score = sentiment.getScore();
    languageService.close();

    System.out.println("got the score!!!");

    String result = convertToJsonUsingGson(score);


    // does this have to become a GET function?
    System.out.println("Sentiment Result: " + result);
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
