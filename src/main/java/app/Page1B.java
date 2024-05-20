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
import java.text.DecimalFormat;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Page1B implements Handler {

  // URL of this page relative to http://localhost:7001/
  public static final String URL = "/page1B.html";

  @Override
  public void handle(Context context) throws Exception {
    // Create a simple HTML webpage in a String
    String html = "<html>";

    // Add some Header information
    html = html + "<head>" +
        "<title>LandingPage</title>";

    // Add some CSS (external file)
    html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
    // adds a cool icon on the nav menu
    html = html
        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
    html = html + "</head>";

    // Add the body
    html = html + "<body>";

    // Add header content block with logo on the right
    html += CommonElements.Header();
    // Add the topnav
    html += CommonElements.Topnav();

    //Add go to homepage and open nav function
    html += CommonElements.goToHomePageandOpenNav();
    //Add SideNavBar
    html += CommonElements.SideNavBar();

    // Add Div for page Content
    html = html + "<div class='content'>";

    // Add HTML for heading
    html = html + "<h1>Welcome To Climate Change Awareness</h1>";
    html = html + "<h3 id='year_heading'> Average Temperature Global Heat Map</h3>";
    html = html + "<div id='regions_div' Style='height: 600px;'></div>";
    
    // Testing for the extension graph we need to do
    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<Climate> Countries = jdbc.getCountryClimateData();
    ArrayList<Climate> Years = jdbc.getCountryYearsOfData();
    int currentYearIndex = Years.size() - 1;
    html = html + "<form action='/page1B.html' method='post'>";
    if (context.formParam("year_dropdown") != null) {
      html = html + "<h4 id='year_heading'> Average Country Temperature In " +
          context.formParam("year_dropdown") + ".</h4>";
    } else {
      html = html + "<h4 id='year_heading'> Average Country Temperature In " +
          Years.get(Years.size() - 1).getYear() + ".</h4>";
    }

    html = html + "<p> Please note data on certain countries may be unavailable over different time periods </p> ";

    // Create dropdown menu for selecting year
    html = html + "<select id='year_dropdown' name='year_dropdown'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : Years) {
      html = html + "<option value='" + year.getYear() + "'>" + year.getYear() + "</option>";
    }
    html = html + "</select>";

    // Add submit button to update the graph
    html = html + "<button class='showGraph' onclick='submitForm()'>Submit</button>";
    html = html + "</form>";

    String Year = context.formParam("year_dropdown");
    if (Year == null) {
      Year = Integer.toString(Years.get(Years.size() - 1).getYear()); // Set a default value if the selected value is
                                                                      // null
    }

    //Get GlobalTemp LandOceanAvgTemp Population
    // Heading for 1B
    html = html + "<h1 class='intro'>INTRODUCTION</h1>";

    html = html + "<h3>Climate Change Data Overview</h3>";

    // Add container
    html = html + """
      <div class='Max_Container'>
      """;
  
          //Data Display Container
          html = html + """
          <div class='Disp_Container'>
              """;
              html = html + """
              <div class='Data_Ranges'>
              """;
              //data range display values
              html = html + "<h4>AvgGlobalTemp:</h4>";
              html = html + "<h7>Data range: </h7>";
              html = html + JDBCConnection.getMinYear("GlobalTemp", "AvgAirTemp") + " - " +JDBCConnection.getMaxYear("GlobalTemp", "AvgAirTemp") ;
              html = html + "<h4>LandOceanAvgTemp</h4>";
              html = html + "<h7>Data range: </h7>";
              html = html + JDBCConnection.getMinYear("GlobalLandOceanTemp", "Year") + " - " +JDBCConnection.getMaxYear("GlobalLandOceanTemp", "Year") ;
              html = html + "<h4>TotalPopulation</h4>";
              html = html + "<h7>Data range: </h7>";
              html = html + JDBCConnection.getMinYear("CountryPopulation", "Year") + " - " +JDBCConnection.getMaxYear("CountryPopulation", "Year") ;
              
              html = html + "</div>";
          html = html + "</div>";
      
    html = html + """
      <div class="Tabs_Container">
        <div class="tabs">
          <div>
            <div class="pane-content">
              <h3><a href="/climate-change-science"><span class="figure image file file-image file-image-png view-mode-block_header" style="width:336px;"><img alt="Photo of a woman in heavy rain." height="137" width="336" class="media-element file-block-header" typeof="foaf:Image" src="three-smokestacks_0.png" title=""></span><br></a>How is the climate changing?</h3>

              <ul><li><a href='page2A.html'>Temperature & Population Change By Country/World</a></li>
                <li><a href='page2B.html'>Temperature Change By State/City</a></li>
              </ul></div>
          </div>
        </div>
        <div class="tabs">
          <div class="pane-content1">
              <h3><a href="/climate-change/reducing-emissions"><span class="figure image file file-image file-image-png view-mode-full" style="width:336px;"><img alt="Photo of two windmills in a grassy field" height="137" width="336" class="media-element file-full" typeof="foaf:Image" src="iceberg-6779681_1920.jpg"></span><br></a>Climate changing by time and region?</h3>

              <ul><li><a href='page3A.html'>Change In Temperature Over Extended Periods</a></li>
                <li><a href='page3B.html'>Time Periods With Similar Temperature/Population</a></li>
              </ul></div>
          </div>
        </div>
      </div>

        """;
    html = html + "</div>";

    // html = html + "<div id='regions_div'></div>";
    html = html + "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>";
    html = html + "<script type='text/javascript'>";
    html = html + "google.charts.load('current', { 'packages':['geochart'] });";
    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
    html = html + "var currentYearIndex = " + currentYearIndex + ";";
    html = html + "function submitForm() {";
    html = html + "var dropdown = document.getElementById('year_dropdown');";
    html = html + "currentYearIndex = dropdown.selectedIndex;";
    html = html + "var currentYear = dropdown.value;";
    html = html
        + "document.getElementById('year_heading').innerText = 'Average Temperature Of The World In ' + currentYear + '.';";
    html = html + "drawRegionsMap();";
    html = html + "}";
    html = html + "function drawRegionsMap() {";
    html = html + "var data = google.visualization.arrayToDataTable([";
    html = html + "['Country', 'Average Temperature'],";
    for (Climate country : Countries) {
      if (Year != null) {

        if (country.getYear() == Integer.parseInt(Year)) {
          html = html + "['" + country.getCountryName() + "', " + country.getAverageTemperature() + "],";
        }
      } 
      else {
        html = html + "['" + Countries.get(0).getCountryName() + "', "
            + Countries.get(0).getAverageTemperature() + "],";
      }
    }
    html = html + "]);";
    html = html + "var options = {colorAxis: {colors: ['#00FF00', '#FFFF00', '#FF0000']},};";
    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
    html = html + "chart.draw(data, options);";
    html = html + "}";
    html = html + "</script>";

    // end content section
    html = html + "</div>";

    // Add Footer
    html = html += CommonElements.Footer();

    // Finish the HTML webpage
    html = html + "</body>" + "</html>";

    // DO NOT MODIFY THIS
    // Makes Javalin render the webpage
    context.html(html);
  }

}
