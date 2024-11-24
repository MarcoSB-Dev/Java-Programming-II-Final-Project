# Job Scraper Application


# How to Run the Job Scraper Application

## Steps to Run the Backend Server

### 1. Clone the Repository:

```bash
- git clone https://github.com/yourusername/jobscraper.git
- cd jobscraper 
```

### 2. Build the Project:
```
mvn clean install
```

### 3. Run the Server:
```
mvn spring-boot:run
```
### 4. Server Running:
```
The backend server should now be running at http://localhost:8080.
```

## Steps to Run the Frontend Google Chrome Extension

### 1. Add jobscraper extension to your Google Chrome:

```bash
Open Google Chrome and go to chrome://extensions/.
```

### 2. Enable Developer Mode:
```
Enable "Developer mode" by toggling the switch in the top right corner.
```

### 3.Unload the package to Google Chrome:
```
Click on "Load unpacked" and select the directory where you cloned the repository.
```

## Using the Jobscraper Extension with Job Tracker Webapp
1. ``` Click on the extension jobscraper icon in the Chrome toolbar.``` <br><br>
2. ``` Click the "Scrape Job Data" button to scrape job data from the current page.``` <br><br>
3. ``` The scraped data will be sent to the backend server running at http://localhost:8080/api/jobs.``` <br><br>
4. ``` While the server is running,on a new tab, in the address bar, please enter :"http://localhost:8080/"``` <br><br>
5. ``` Everytime you save a job post, refresh the Job track web app to see the new job you added. ``` <br><br>



