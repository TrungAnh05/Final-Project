package app;
 
import java.util.ArrayList;
 
import io.javalin.http.Context;
import io.javalin.http.Handler;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageMission implements Handler {
 
  // URL of this page relative to http://localhost:7001/
  public static final String URL = "/mission.html";
 
  @Override
  public void handle(Context context) throws Exception {
    // Create a simple HTML webpage in a String
    String html = "<html>";
 
    // Add some Head information
    html += "<head>" + "<meta charset='UTF-8'>" +
        "<title>Our Mission</title>";
 
    // Add some CSS (external file)
    html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='ourmission.css' />";
    // adds a cool icon on the nav menu
    html = html
        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
    html += "</head>";
 
    // Add the body
    html += "<body>";
 
    // Add header content block with logo on the right
    html += CommonElements.Header();
    // Add the topnav
    html += CommonElements.Topnav();

    //Add go to homepage and open nav function
    html += CommonElements.goToHomePageandOpenNav();

    //Add SideNavBar
    html += CommonElements.SideNavBar();

    // Add Div for page Content
    html = html + "<div class='content1'>";
 
    html += """
              <section class="mission-section">
          <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/6f4e7c4818131112a6a66f8c1905a586950dc38514575988ac460f581d8161af?apiKey=51f4311d3f4749feb9f6669c989090bb&" alt="" class="mission-background" loading="lazy" />
         
          <div class="mission-content">
            <div class="mission-grid">
              <div class="mission-title-column">
                <h1 class="mission-title">Our mission</h1>
              </div>
              <div class="mission-description-column">
                <p class="mission-description">
                Explore global climate change through detailed temperature and population data. Our platform offers insights at global, country, city, and state levels, empowering informed decisions for travelers, businesses, and the curious.
                </p>
              </div>
            </div>
          </div>
        </section>
                        """;
 
    // Personas Sections
    html = html + "<section id='persona-section'>";
    html = html + "<section class='persona-section'>";
    html = html + "<img src='https://cdn.builder.io/api/v1/image/assets/TEMP/d4e9ec81c06cd6850d8e63ccfcc03b5d2b780920adeb66353de32327ece1d1bc?apiKey=51f4311d3f4749feb9f6669c989090bb&'' alt='' class='background-image' />";
    html = html + "<div class='content-wrapper'>";
    html = html + "<h2 class='section-title'>Persona</h2>";
    
    html = html + "<div class='persona-card'>";
    html = html + "<div class='persona-content'>";
        html = html + "<div class='persona-details'>";
            html = html + "<div class='persona-description'>";
                
            ArrayList<PersonaData> data = JDBCConnection.getPersonaData();

            for(int i=0; i<1; i++){
              ArrayList<PersonaData> persona = JDBCConnection.getPersonaData();;
              String Name = persona.get(i).getName();
              String Quote = persona.get(i).getQuote();
              String Background = persona.get(i).getBackground();
              String Experience = persona.get(i).getExperience();
              String Requirements = persona.get(i).getRequirements();
          
              html += "<h2>" + Name + "</h2>";
              html += "<p>" + Quote + "</p>";
              html += " <h4> Background </h4>";
              html += "<p>" + Background + "</p>";
              html += " <h4> Experience </h4>";
              html += "<p>" + Experience + "</p>";
              html += " <h4> Requirements </h4>";
              html += "<p>" + Requirements + "</p>";
              html += String.format("<img style='width: 200px; height: 200px; float: right;' src='%s'/>",
              persona.get(i).getImagePath());
              html = html + "</div>";
              }
                html = html + "</div>";
            html = html + "</div>";
        html = html + "</div>";

    html = html + "<div class='persona-card'>";
    html = html + "<div class='persona-content'>";
        html = html + "<div class='persona-details'>";
            html = html + "<div class='persona-description'>";

            for(int i=1; i<2; i++){
              ArrayList<PersonaData> persona = JDBCConnection.getPersonaData();;
              String Name = persona.get(i).getName();
              String Quote = persona.get(i).getQuote();
              String Background = persona.get(i).getBackground();
              String Experience = persona.get(i).getExperience();
              String Requirements = persona.get(i).getRequirements();
          
              html += "<h2>" + Name + "</h2>";
              html += "<p>" + Quote + "</p>";
              html += " <h4> Background </h4>";
              html += "<p>" + Background + "</p>";
              html += " <h4> Experience </h4>";
              html += "<p>" + Experience + "</p>";
              html += " <h4> Requirements </h4>";
              html += "<p>" + Requirements + "</p>";
              html += String.format("<img style='width: 200px; height: 200px; float: right;' src='%s'/>",
              persona.get(i).getImagePath());
              html = html + "</div>";
              }
                html = html + "</div>";
            html = html + "</div>";
        html = html + "</div>";

    html = html + "<div class='persona-card'>";
    html = html + "<div class='persona-content'>";
        html = html + "<div class='persona-details'>";
            html = html + "<div class='persona-description'>";
                
            for(int i=2; i<3; i++){
              ArrayList<PersonaData> persona = JDBCConnection.getPersonaData();;
              String Name = persona.get(i).getName();
              String Quote = persona.get(i).getQuote();
              String Background = persona.get(i).getBackground();
              String Experience = persona.get(i).getExperience();
              String Requirements = persona.get(i).getRequirements();
          
              html += "<h2>" + Name + "</h2>";
              html += "<p>" + Quote + "</p>";
              html += " <h4> Background </h4>";
              html += "<p>" + Background + "</p>";
              html += " <h4> Experience </h4>";
              html += "<p>" + Experience + "</p>";
              html += " <h4> Requirements </h4>";
              html += "<p>" + Requirements + "</p>";
              html += String.format("<img style='width: 200px; height: 200px; float: right;' src='%s'/>",
              persona.get(i).getImagePath());
              html = html + "</div>";
              }
                html = html + "</div>";
            html = html + "</div>";
        html = html + "</div>";

    html = html + "</div>";
    html = html + "</section>";
    html = html + "</section>";

    // Student info section
    html = html + "<section id='aboutUs-section'>";
    html += """
              <section class="who-are-we">
          <div class="vertical-divider"></div>
          <h2 class="student-title">
            Who
            </br>
            are WE
          </h2>
            """;
            html = html + "<div class='member-info-left'>";

            for(int i=0; i<=1; i++){
              ArrayList<StudentInfo> info = JDBCConnection.getStudentInfo();
              String FName = info.get(i).getFname();
              String LName = info.get(i).getLname();
              String StudentNumber = info.get(i).getStudentNumber();
              String Email = info.get(i).getEmail();
          
              html +=  FName + " " + LName + "</br>" + "</br>";
              html += "Student Number " + StudentNumber + "</br>" + "</br>";
              html += "Email" + "</br>";
              html +=  Email + "</br>" + "</br>" + "</br>";
              }

          html = html + "</div>";

          html = html + "<div class='member-info-right'>";
            for(int i=2; i<=3; i++){
            ArrayList<StudentInfo> info = JDBCConnection.getStudentInfo();
            String FName = info.get(i).getFname();
            String LName = info.get(i).getLname();
            String StudentNumber = info.get(i).getStudentNumber();
            String Email = info.get(i).getEmail();

            html +=  FName + " " + LName + "</br>" + "</br>";
            html += "Student Number " + StudentNumber + "</br>" + "</br>";
            html += "Email" + "</br>";
            html +=  Email + "</br>" + "</br>" + "</br>";
            }
            html = html + "</div>";
          html = html + "<div class='vertical-divider-right'>";
          html = html + "</div>";
        html = html + "</section>";

    // Close Content div
    html += "</div>";
 
    // Footer
        html = html += CommonElements.Footer();
 
    // DO NOT MODIFY THIS
    // Makes Javalin render the webpage
    context.html(html);
  }
}