package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommonElements {
    //	Title is the string variable which gives the document its title.
	public static String DocumentStart(String Title){
		return
		"""
		<head>
			<title>
		"""
		+ Title + 
		"""
			</title>
			<link rel='stylesheet' type='text/css' href='common.css' />
			<link rel='stylesheet' type='text/css' href='TabsCommon.css' />
            
			<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'></head>
		<body>
		""";
	}
    
    public static String Header() {
		return
		"""
            <div class='header'>
                <h1>
                <a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
                    Climate Change Awareness
                </h1>
            </div>
		""";
	}

	public static String Topnav() {
		return
		"""
			<div class='topnav'>
			<a href='/'>Home</a>
			<div class='dropDown'>
			<button class='dropbtn'>Climate Data and Analysis
			<i class='fa fa-caret-down'></i>
			</button>
			<div class='dropdown-content'>
			<a href='page1B.html'>Introduction About Data</a>
			<a href='page2A.html'>Temperature & Population Change By Country/World</a>
			<a href='page2B.html'>Temperature Change By State/City</a>
			<a href='page3A.html'>Change In Temperature Over Extended Periods</a>
			<a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
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
	}

	public static String SideNavBar() {
		return
		"""
            <div class='SideNavBar'>
				<div id='mySidenav' class='sidenav'>
							<a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>
							<a href='/'>Home</a>
							<p>Climate Data and Analysis</p>
							<a href='page1B.html'>Introduction About Data</a>
							<a href='page2A.html'>Temperature & Population Change By Country/World</a>
							<a href='page2B.html'>Temperature Change By State/City</a>
							<a href='page3A.html'>Change In Temperature Over Extended Periods</a>
							<a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
							<p>About Us</p>
							<a href='mission.html'>Our Mission</a>
							<a href='mission.html#persona-section'>Personas</a>
							<a href='mission.html#aboutUs-section'>Contact Us</a>

						</div>
            <span style='color: #f1f1f1; position: fixed; top:10px; right:20px; font-size:40px; cursor:pointer' onclick='openNav()'> &#9776;</span>
			</div>
		""";
	}

	public static String Footer() {
		return
		"""
			<div class='footer'>

				<div class='footerBlock'>
						<div class='footerColumn'>
						  <p style='margin-top: 0;'>Shallow View</p>
						  <a href='page1B.html'>Introduction About Data</a>
						  <a href='page2A.html'>Temperature & Population Change By Country/World</a>
						  <a href='page2B.html'>Temperature Change By State/City</a>
						</div>
						<div class='footerColumn'>
						  <p style='margin-top: 0;'>In-Depth View</p>
						  <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
						  <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
						</div>
						<div class='footerColumn'>
						  <p style='margin-top: 0;'>About</p>
						  <a href='mission.html'>Our Mission</a>
						  <a href='mission.html#persona-section'>Personas</a>
						  <a href='mission.html#aboutUs-section'>Contact Us</a>
						</div>

				</div>
			</div>
		""";
	}	

	public static String goToHomePageandOpenNav(){
		return
		"""
		<script>
			function openNav() {
				document.getElementById('mySidenav').style.width = '250px';
			}
	
			function closeNav() {
				document.getElementById('mySidenav').style.width = '0px';
			}
			// Function to navigate to the home page
			function goToHomePage() {
			  window.location.href = '/';
			}
	
			document.addEventListener('keydown', function(event) {
			  // Check if the Esc key (key code 27) is pressed
			  if (event.keyCode === 27) {
				goToHomePage();
			  }
			})
			</script>
		""";
	}

	public static String CtoResetData(){
		return
		"""
			<script>
			document.addEventListener('DOMContentLoaded', function() {
				var button = document.querySelector('.reset');
				document.addEventListener('keydown', function(event) {
					// Check if the C key (key code 67) is pressed
					if (event.keyCode == 67) {
						button.click();
					}
				});
			});
			</script>
		""";
	}

}

