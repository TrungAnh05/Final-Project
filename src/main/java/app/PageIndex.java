package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageIndex implements Handler {

  // URL of this page relative to http://localhost:7001/
  public static final String URL = "/";

  @Override
  public void handle(Context context) throws Exception {
    // Create a simple HTML webpage in a String
    String html = "<html>";

    // Add some Header information
    html = html + "<head>" +
        "<title>Homepage</title>";

    // Add some CSS (external file)
    html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
    // adds a cool icon on the nav menu
    html = html
        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
    html = html + "</head>";

    // Add the body
    html = html + "<body>";

    // Add header content block with logo on the right and add wrapper
    html = html
        + """
                    <div class='header'>
                    <h1>
                    <a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
                        Climate Change Awareness
                    </h1>
                    </div>
            """;
    
    html = html
        + """
            <section class="showcase">
            <video src="22059-325253411_medium.mp4" muted loop autoplay></video>
            <div class="overlay"></div>
            <div class="text">
            <h2>Welcome to<br> 
            Climate Change Awareness </h2> 
            <p>where we're committed to raising awareness about climate temperature change and its impact on our planet.</p>
            <a href='landing.html'>Explore</a>
            </div>
        </section>

            """;
                
              
    html = html + "</section>";
    // Finish the HTML webpage
    html = html + "</body>" + "</html>";

    // DO NOT MODIFY THIS
    // Makes Javalin render the webpage
    context.html(html);
  }

}
