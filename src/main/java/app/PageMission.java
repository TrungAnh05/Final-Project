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
    html = html + "<link rel='stylesheet' type='text/css' href='ourMission.css' />";
    // adds a cool icon on the nav menu
    html = html
        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
    html += "</head>";

    // Add the body
    html += "<body>";

    // Add header content block
    html += """
            <div class='header'>
            <h1><a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
                <h1>Climate Change Awareness</h1>
            </div>
        """;

    // Add the topnav
    html = html + """
        <div class='topnav'>
            <a href='/'>Home</a>
            <div class='dropDown'>
                <button class='dropbtn'>Climate Data and Analysis
                    <i class='fa fa-caret-down'></i>
                </button>
                <div class='dropdown-content'>
                    <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                    <a href='page2B.html'>Temperature Change By State/City</a>
                    <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                    <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                    <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                    <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                </div>
            </div>
            <div class='dropDown'>
                <button class='dropbtn'>About Us
                    <i class='fa fa-caret-down'></i>
                </button>
                <div class='dropdown-content'>
                    <a href='mission.html'>Our Mission</a>
                    <a href='mission.html#persona-section'>Personas</a>
                    <a href='mission.html#aboutUs-section'>Contact Us</a>
                </div>
            </div>
        </div>
        """;

    html = html + "<script>";
    html = html + """

                        function openNav() {
          document.getElementById('mySidenav').style.width = '250px';
        }

        function closeNav() {
          document.getElementById('mySidenav').style.width = '0px';
        }
                                        """;
    html = html + "</script>";
    html = html + "<div class='SideNavBar'>";
    html = html
        + """
                    <div id='mySidenav' class='sidenav'>
                        <a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>
                        <a href='/'>Home</a>
                        <p>Climate Data and Analysis</p>
                        <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                        <a href='page2B.html'>Temperature Change By State/City</a>
                        <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                        <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                        <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                        <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                        <p>About Us</p>
                        <a href='mission.html'>Our Mission</a>
                <a href='mission.html#persona-section'>Personas</a>
                <a href='mission.html#aboutUs-section'>Contact Us</a>
                <p>Help & Support</p>
                <a href='PageHelp.html'>Page Assistance</a>
                <a href='PageHelp.html#faq-section'>FAQ</a>
                <a href='PageHelp.html#advanced-section'>Advanced Features</a>

                    </div>
            <span style='color: #f1f1f1; position: fixed; top:10px; right:20px; font-size:40px; cursor:pointer' onclick='openNav()'> &#9776;</span>
                    """;
    html = html + "</div>";
    html = html + "<script>";
    // take to help page
    html = html + """

             // Function to navigate to the help page
        function goToHelpPage() {
          window.location.href = 'PageHelp.html';
        }
                                document.addEventListener('keydown', function(event) {
                      // Check if the h key (key code 72) is pressed
                      if (event.keyCode === 72) {
                        goToHelpPage();
                      }
                    });
                                """;
    // take to home page
    html = html + """
             // Function to navigate to the home page
        function goToHomePage() {
          window.location.href = '/';
        }

        document.addEventListener('keydown', function(event) {
          // Check if the Esc key (key code 27) is pressed
          if (event.keyCode === 27) {
            goToHomePage();
          }
        });
                    """;
    html = html + "</script>";
    // Add Div for page Content
    html += "<div class='content'>";

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
    html += """
      
<section class="persona-section">
<img src="https://cdn.builder.io/api/v1/image/assets/TEMP/d4e9ec81c06cd6850d8e63ccfcc03b5d2b780920adeb66353de32327ece1d1bc?apiKey=51f4311d3f4749feb9f6669c989090bb&" alt="" class="background-image" />
<div class="content-wrapper">
  <h2 class="section-title">Persona</h2>
  <div class="persona-card">
    <div class="persona-content">
      <div class="persona-details">
        <p class="persona-description">
          Heath Young<br />
          "I just want to look at broad climate change statistics and get a better understanding of how climate change affects me and the world today."<br />
          <br />
          <span class="bold-text">Background</span><br />
          <br />
          <br />
          <span class="bold-text">Experience</span><br />
          <br />
          <br />
          <span class="bold-text">Requirements</span><br />
        </p>
      </div>
      <div class="persona-image-wrapper">
        <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/a7478163189cfaec779a41db75d922d765dd685afad78ed5b135222acf5f455b?apiKey=51f4311d3f4749feb9f6669c989090bb&" alt="Heath Young" class="persona-image" />
      </div>
    </div>
  </div>
  <div class="persona-card">
    <div class="persona-content">
      <div class="persona-details">
        <p class="persona-description">
          Heath Young<br />
          "I just want to look at broad climate change statistics and get a better understanding of how climate change affects me and the world today."<br />
          <br />
          <span class="bold-text">Background</span><br />
          <br />
          <br />
          <span class="bold-text">Experience</span><br />
          <br />
          <br />
          <span class="bold-text">Requirements</span><br />
        </p>
      </div>
      <div class="persona-image-wrapper">
        <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/8f874fe63df2d1d45e69007cd9e8c94fd7c2f82dd2cf1389438bec827ab70677?apiKey=51f4311d3f4749feb9f6669c989090bb&" alt="Heath Young" class="persona-image" />
      </div>
    </div>
  </div>
  <div class="persona-card">
    <div class="persona-content">
      <div class="persona-details">
        <p class="persona-description">
          Heath Young<br />
          "I just want to look at broad climate change statistics and get a better understanding of how climate change affects me and the world today."<br />
          <br />
          <span class="bold-text">Background</span><br />
          <br />
          <br />
          <span class="bold-text">Experience</span><br />
          <br />
          <br />
          <span class="bold-text">Requirements</span><br />
        </p>
      </div>
      <div class="persona-image-wrapper">
        <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/de3543b17432724c92cd696c39e5919cc94c5174d7a2ee17ded47cede0a7ff2e?apiKey=51f4311d3f4749feb9f6669c989090bb&" alt="Heath Young" class="persona-image" />
      </div>
    </div>
  </div>
</div>
</section>
                """;

    
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
          <div class="member-info-left">
            Chang Fang Cih
            <br />
            Student Number s4073761
            <br />
            Email
            <br />
            s4073761@rmit.edu.vn
            <br />
            <br />
            Nguyen Pham Trung Anh
            <br />
            Student Number s4053394
            <br />
            Email
            <br />
            s4053394@rmit.edu.vn
          </div>
          <div class="member-info-right">
            Ngo Dang Khoi
            <br />
            Student Number s4033100
            <br />
            Email
            <br />
            s4033100@rmit.edu.vn
            <br />
            <br />
            Huynh Minh Nam
            <br />
            Student Number s3974896
            <br />
            Email
            <br />
            s3974896@rmit.edu.vn
          </div>
          <div class="vertical-divider-right"></div>
        </section>
        """;

    // Finish the List HTML
    html += "</ul>";
    html = html + "</section>";
    // Close Content div
    html += "</div>";

    // Footer
    html = html
        + """
            <div class='footer'>

                      <div class='footerBlock'>
                                  <div class='footerColumn'>
                                    <p style='margin-top: 0;'>Shallow View</p>
                                    <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                                    <a href='page2B.html'>Temperature Change By State/City</a>
                                    <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                                  </div>
                                  <div class='footerColumn'>
                                    <p style='margin-top: 0;'>In-Depth View</p>
                                    <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                                    <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                                    <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                                  </div>
                                  <div class='footerColumn'>
                                    <p style='margin-top: 0;'>About</p>
                                    <a href='mission.html'>Our Mission</a>
                                    <a href='mission.html#persona-section'>Personas</a>
                                    <a href='mission.html#aboutUs-section'>Contact Us</a>
                                  </div>
                                  <div class='footerColumn'>
                                    <p style='margin-top: 0;'>Help & Support</p>
                                    <a href='PageHelp.html'>Page Assistance</a>
                                    <a href='PageHelp.html#faq-section'>FAQ</a>
                                    <a href='PageHelp.html#advanced-section'>Advanced Features</a>

                                  </div>
                                </div>
                              </div>
                  """;

    // Finish the HTML webpage
    html += "</body>" + "</html>";

    // DO NOT MODIFY THIS
    // Makes Javalin render the webpage
    context.html(html);
  }

}
