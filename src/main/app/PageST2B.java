package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2B.html";

    public static void sort(ArrayList<Stat> list) {

        list.sort((o2, o1) -> Float.compare(o1.getProportion(), o2.getProportion()));
    }

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" +
                "<title>Subtask 2.1</title>";

        // Add some CSS (external file)
        // html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        // adds a cool icon on the nav menu
        html = html
                + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
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

        html = html + """
                <h1>Focused view of temperature by Cities or States</h1>
                """;

        // declaring variables

        int fromDate;
        int toDate;
        String type = context.req.getParameter("type");
        try {
            fromDate = Integer.parseInt(context.req.getParameter("from"));
            toDate = Integer.parseInt(context.req.getParameter("to"));
        } catch (Exception e) {
            fromDate = 0;
            toDate = 0;
        }
        String countryParameterFromURL = context.req.getParameter("country");

        // header content block

        html += """
                   <div class='header'>
                       <h3> Temperatures by States or Cities </h3>
                   </div>
                """;

        // brief description of info on page
        html += """
                   <p> Explore local climate change impacts with our table generator. Rank cities/states by temperature changes for selected time periods.</p>
                     <p> <strong>Please Note:</strong> Data is not available for all countries. </p>

                """;

        // hashmap to get country names for dropdown menu

        ArrayList<Country> mapOfCountries = JDBCConnection.getCountryNames();
        html += """

                    <form id='form-id'>
                    <label for>Select Country:</label>
                    <select name="country" onchange='document.getElementById("form-id").submit();'>
                    <option value="" selected disabled hidden>Country</option>
                """;

        for (Country c : mapOfCountries) {

            String key = c.getId();
            String value = c.getName();
            if (countryParameterFromURL != null) {
                if (countryParameterFromURL.equals(key)) {

                    // part of code that makes the selected option stay after the page is refreshed

                    html += "<option selected='selected' value='";
                    html += key + "'>" + value + "</option>";
                } else {
                    html += "<option value='";
                    html += key + "'>" + value + "</option>";
                }
            } else {
                html += "<option value='";
                html += key + "'>" + value + "</option>";
            }
        }

        html += "</select>";

        // dropdown menu for cites or states

        html = html += "<br> <label for>Select Cities or States:</label>";
        html += """
                    <select name='type'>
                    <option value="" selected disabled hidden>City/State</option>
                """;

        if (countryParameterFromURL != null) {
            if (JDBCConnection.hasStates(countryParameterFromURL)) {
                if (type != null) {
                    if (type.equals("States")) {
                        html += "<option selected=selected value='States'>States</option> ";
                    } else {
                        html += "<option value='States'>States</option> ";
                    }
                } else {
                    html += "<option value='States'>States</option> ";
                }
            }

            if (JDBCConnection.hasCities(countryParameterFromURL)) {
                if (type != null) {

                    if (type.equals("Cities")) {
                        html += "<option selected=selected value='Cities'>Cities</option>";
                    } else {
                        html += "<option value='Cities'>Cities</option>";
                    }
                } else {
                    html += "<option value='Cities'>Cities</option> ";
                }

            }
        }
        // dropdown menues for start and end dates

        html += """
                    </select>
                    <br> <label for>Select Start Year:</label>
                    <select name='from'>
                    <option value='' selected disabled hidden>Year</option>
                """;

        for (int i = 1750; i < 2014; i++) {
            if (fromDate == i) {
                html += "<option selected='selected' value='";
                html += i + "'>" + i + "</option>";
            } else {

                html += "<option value='";
                html += i + "'>" + i + "</option>";
            }
        }

        html += "</select>";
        html += "<br> <label for>Select End Year:</label>";
        html += "<select name='to'>";
        html += """
                  <option value="" selected disabled hidden>Year</option>
                """;

        for (int i = 1749; i < 2014; i++) {
            if (toDate == i) {
                html += "<option selected ='selected' value='";
                html += i + "'>" + i + "</option>";
            } else {

                html += "<option value='";
                html += i + "'>" + i + "</option>";
            }
        }

        html += "</select>";

        html += "<div>";
        html += "<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";
        html += "</div>";
        html += "<div>";
        html = html + "<br> <button class='reset' type='reset' >Reset</button>";
        html += "</div>";
        html += "</form>";

        // javascript to allow 'c' to also reset the data
        html += CommonElements.CtoResetData();

        if (countryParameterFromURL != null) {
            ArrayList<TempData> data;


            if (type != null) {
                html += "<table><tr> <th> Year </th> ";

                if (type.equals("States")) {
                    data = JDBCConnection.getTempByState(countryParameterFromURL, fromDate, toDate);
                    html += "<th> State</th>";

                } else {
                    data = JDBCConnection.getTempByCity(countryParameterFromURL, fromDate, toDate);

                    html += "<th> City</th>";

                }

                html += "<th> Average Temperature </th> ";
                html += "<th> Minimum Temperature </th> <th> Maximum Temperature </th> </tr>";

                for (TempData d : data) {
                    html += "<tr><td>" + d.getYear() + "</td>";
                    html += "<td>" + d.getName() + "</td>";
                    html += "<td>" + d.getAvgTemp() + "</td>";
                    html += "<td>" + d.getMinTemp() + "</td>";
                    html += "<td>" + d.getMaxTemp() + "</td></tr>";
                }
                html += "<table>";
                // " <td> " + Years + "</td>";

                ArrayList<TempData> minYearTemp = getMaximumTemperatures(data);
                ArrayList<TempData> maxYearTemp = getMinimumTemperatures(data);

                ArrayList<Stat> stats = getAvgTempProportionalValues(minYearTemp, maxYearTemp);
                ArrayList<Stat> statsMin = getMinTempProportionalValues(minYearTemp, maxYearTemp);
                ArrayList<Stat> statsMax = getMaxTempProportionalValues(minYearTemp, maxYearTemp);
                html += "<h3> The Proportional Change of Average Temperatures</h3>";
                html += printOutRanking(stats);
                html += "<h3> The Proportional Change of Minimum Temperatures</h3>";
                html += printOutRanking(statsMin);
                html += "<h3> The Proportional Change of Maximum Temperatures</h3>";
                html += printOutRanking(statsMax);
                html += "</table>";
            } else {
                html += "</tr></table>";

            }

        }

        // Close Content div
        html = html + "</div>";


        // Footer
        html = html += CommonElements.Footer();

        html += "<script>$(\"#form-id\").html($(\"#form-id option\").sort(function (a, b) {\n" +
                "    return a.text == b.text ? 0 : a.text < b.text ? -1 : 1\n" +
                "}))</script>";
        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);

    }

    public ArrayList<TempData> getMinimumTemperatures(ArrayList<TempData> data) {
        ArrayList<TempData> tmp = new ArrayList<TempData>();
        for (TempData d : data) {
            boolean exists = false;
            for (TempData t : tmp) {
                if (d.getName().equals(t.getName())) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                return tmp;
            } else {
                tmp.add(d);
            }
        }
        return tmp;
    }

    public ArrayList<TempData> getMaximumTemperatures(ArrayList<TempData> data) {
        ArrayList<TempData> tmp = new ArrayList<TempData>();
        for (int i = data.size() - 1; i > 0; i--) {
            boolean exists = false;
            for (TempData t : tmp) {
                if (data.get(i).getName().equals(t.getName())) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                return tmp;
            } else {
                tmp.add(data.get(i));
            }
        }
        return tmp;
    }

    public ArrayList<Stat> getAvgTempProportionalValues(ArrayList<TempData> min, ArrayList<TempData> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (TempData m : min) {
            for (TempData x : max) {
                if (m.getName().equals(x.getName())) {
                    stats.add(new Stat(x.getName(), ((m.getAvgTemp() - x.getAvgTemp()) / m.getAvgTemp()) * 100));
                }
            }
        }
        return stats;
    }

    public ArrayList<Stat> getMinTempProportionalValues(ArrayList<TempData> min, ArrayList<TempData> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (TempData m : min) {
            for (TempData x : max) {
                if (m.getName().equals(x.getName())) {
                    stats.add(new Stat(x.getName(), ((m.getMinTemp() - x.getMinTemp()) / m.getMinTemp()) * 100));
                }
            }
        }
        return stats;
    }

    public ArrayList<Stat> getMaxTempProportionalValues(ArrayList<TempData> min, ArrayList<TempData> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (TempData m : min) {
            for (TempData x : max) {
                if (m.getName().equals(x.getName())) {
                    stats.add(new Stat(x.getName(), ((m.getMaxTemp() - x.getMaxTemp()) / m.getMaxTemp()) * 100));
                }
            }
        }
        return stats;
    }

    public String printOutRanking(ArrayList<Stat> stats) {
        sort(stats);
        String html = "";
        int i = 1;
        for (Stat s : stats) {
            html += "<li>Rank " + i + ". " + s.getId() + " has a change in proportion of: " + s.getProportion()
                    + "</li>";
            i++;
        }
        return html;
    }
}
