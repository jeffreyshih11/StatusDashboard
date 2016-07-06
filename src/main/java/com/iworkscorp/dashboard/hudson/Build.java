package com.iworkscorp.dashboard.hudson;

import static com.iworkscorp.dashboard.hudson.TestBase.driver;

/**
 * Created by jshih on 6/28/2016.
 */
public class Build {

    String environment;
    int revision;
    String builder;
    String dateBuiltFull;
    String dateBuilt;
    String timeBuilt;
    String buildStatus;
    String smokeTestRun;
    String smokeTestPass;


    public Build(){
        this.environment = "";
        this.revision = 0;
        this.builder = "";
        this.dateBuiltFull = "";
        this.dateBuilt = "";
        this.timeBuilt = "";
        buildStatus = "";
        this.smokeTestRun = "";
        this.smokeTestPass = "";


    }
    public Build(String environment, int revision, String builder, String dateBuiltFull, String status, String smokeTestRun, String smokeTestPass){

        this.environment = environment;
        this.revision = revision;
        this.builder = builder;
        this.dateBuiltFull = dateBuiltFull;
        this.dateBuilt = getDate(this.dateBuiltFull, getCommaIdx(this.dateBuiltFull));
        this.timeBuilt = getTime(this.dateBuiltFull, getCommaIdx(this.dateBuiltFull));

        this.buildStatus = status;
        this.smokeTestRun = smokeTestRun;
        this.smokeTestPass = smokeTestPass;

    }

    /**
     * Creates a build object given the URL
     * @param url
     */
    public Build(String url){
        driver.navigate().to(url);
        String pageSource = driver.getPageSource();

        this.environment = findEnvironment(url);

        if(pageSource.indexOf("Status Code: 404") > 0){
            this.revision = 0;
            this.builder = "N/A";
            this.dateBuiltFull = "N/A";
            this.dateBuilt = "N/A";
            this.timeBuilt = "N/A";
            buildStatus = "N/A";
        }

        else {
            //System.out.println("useless code");
            int revIndex = pageSource.indexOf("At revision");
            this.revision = Integer.parseInt(pageSource.substring(revIndex + 12, revIndex + 17));

            int userIndex = pageSource.indexOf("Started by user");
            if (userIndex <= 0) {
                userIndex = pageSource.indexOf("Started by an SCM change");
                this.builder = "Automated";
            } else {
                this.builder = pageSource.substring(userIndex + 16, pageSource.indexOf("\n", userIndex));
            }

            int dateIndex = pageSource.indexOf("revision");
            this.dateBuiltFull = pageSource.substring(dateIndex + 10, pageSource.indexOf("M", dateIndex + 13) + 1);
            this.dateBuilt = getDate(this.dateBuiltFull, getCommaIdx(this.dateBuiltFull));
            this.timeBuilt = getTime(this.dateBuiltFull, getCommaIdx(this.dateBuiltFull));


            int buildIdx = pageSource.indexOf("Progress:");
            if(buildIdx > 0){
                buildStatus = "building";
            }
            else{
                buildIdx = pageSource.indexOf("FAILURE");
                if(buildIdx > 0){
                    buildStatus = "false";
                }
                else{
                    buildStatus = "true";
                }
            }

        }

        this.smokeTestRun = "";
        this.smokeTestPass = "";

    }

    /*public void setStatus(boolean status){
        buildStatus = status;
    }*/

   /* public void setSmokeTestRun(boolean smokeTestRun){
        this.smokeTestRun = smokeTestRun;
    }

    public void setSmokeTestPass(boolean smokeTestPass){
        this.smokeTestPass = smokeTestPass;
    }*/

    /**
     * Compares two build objects to see which one is more recent
     * @param other
     * @return
     */
    public boolean isMostRecent(Build other){

        String[] thisDate = formatDate(this.dateBuilt).split(":");
        String[] thisTime = formatTime(timeBuilt).split(":");
        String[] otherDate = formatDate(other.dateBuilt).split(":");
        String[] otherTime = formatTime(other.timeBuilt).split(":");

        int thisMonth = Integer.parseInt(thisDate[0]);
        int thisDay = Integer.parseInt(thisDate[1]);
        int thisYear = Integer.parseInt(thisDate[2]);

        int otherMonth = Integer.parseInt(otherDate[0]);
        int otherDay = Integer.parseInt(otherDate[1]);
        int otherYear = Integer.parseInt(otherDate[2]);

        if(thisYear > otherYear){
            return true;
        }
        else if(thisYear == otherYear){
            if(thisMonth > otherMonth){
                return true;
            }
            else if(thisMonth == otherMonth){
                if(thisDay > otherDay){
                    return true;
                }
                else if(thisDay == otherDay){
                    int thisHour = Integer.parseInt(thisTime[0]);
                    int thisMin = Integer.parseInt(thisTime[1]);
                    int thisSec = Integer.parseInt(thisTime[2]);

                    int otherHour = Integer.parseInt(otherTime[0]);
                    int otherMin = Integer.parseInt(otherTime[1]);
                    int otherSec = Integer.parseInt(otherTime[2]);

                    if(thisHour > otherHour){
                        return true;
                    }
                    else if(thisHour == otherHour){
                        if(thisMin > otherMin){
                            return true;
                        }
                        else if(thisMin == otherMin){
                            if(thisSec > otherSec){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;

    }

    /*public String convertTime(String time){

        String[] FullTime = time.split("_");
        String cDate = FullTime[0].replace("-", "/");
        String cTime = FullTime[1].replace("-", ":");

        return cDate + " " + cTime;

    }*/


    /**
     * Finds the comma index given the full date and time
     * @param fullDateBuilt
     * @return
     */
    public static int getCommaIdx(String fullDateBuilt){
        return fullDateBuilt.indexOf(",");
    }

    /**
     * Gets just the date a build was built
     * @param fullDateBuilt
     * @param commaIdx
     * @return
     */
    public static String getDate(String fullDateBuilt, int commaIdx){
        return fullDateBuilt.substring(0, commaIdx + 6);
    }

    /**
     * Gets the time a build was built
     * @param fullDateBuilt
     * @param commaIdx
     * @return
     */
    public static String getTime(String fullDateBuilt, int commaIdx){

        return fullDateBuilt.substring(commaIdx + 7);
    }

    /**
     * Formats the date for easy comparison
     * @param fullDateBuilt
     * @return
     */
    public static String formatDate(String fullDateBuilt){
        if(fullDateBuilt.equals("N/A")){
            return "0:0:0";
        }
        int month = getMonthNum(fullDateBuilt);
        String[] splitDate = fullDateBuilt.split(" ");
        return month + ":" + splitDate[1].substring(0, splitDate[1].length()-1) + ":" + splitDate[2];
    }

    /**
     * Formats the time into 24 hour clock for easy comparison
     * @param time
     * @return
     */
    public static String formatTime(String time){
        if(time.indexOf("PM") > 0){
            String[] splitTime = time.split(":");
            String hour = splitTime[0];

            int intHour = Integer.parseInt(hour);
            if(intHour != 12){
                intHour += 12;
                return intHour + ":" + time.substring(2,7);
            }
            return time.substring(0, time.length()-3);

        }
        else if(time.indexOf("AM") > 0){
            if(time.substring(0, 2).equals("12")){
                return 0 + time.substring(2,8);
            }
            return time.substring(0, time.length()-3);
        }
        else{
            return "0:0:0";
        }

    }

    /**
     * Given the month, assigns a numerical equivalent for comparison
     * @param date
     * @return
     */
    public static int getMonthNum(String date){
        if (date.indexOf("Jan") >= 0) {
            return 1;
        }
        else if (date.indexOf("Feb") >= 0) {
            return 2;
        }
        else if (date.indexOf("Mar") >= 0) {
            return 3;
        }
        else if (date.indexOf("Apr") >= 0) {
            return 4;
        }
        else if (date.indexOf("May") >= 0) {
            return 5;
        }
        else if (date.indexOf("Jun") >= 0) {
            return 6;
        }
        else if (date.indexOf("Jul") >= 0) {
            return 7;
        }
        else if (date.indexOf("Aug") >= 0) {
            return 8;
        }
        else if (date.indexOf("Sep") >= 0) {
            return 9;
        }
        else if (date.indexOf("Oct") >= 0) {
            return 10;
        }
        else if (date.indexOf("Nov") >= 0) {
            return 11;
        }
        else if (date.indexOf("Dec") >= 0) {
            return 12;
        }
        else{
            return -1;
        }
    }

    public String findEnvironment(String url){
        if(url.indexOf("Baseline") > 0){
            return "Baseline";
        }
        else if(url.indexOf("DEMO") > 0){
            return "DEMO";
        }
        else if(url.indexOf("DEV") > 0){
            return "DEV";
        }
        else if(url.indexOf("GAT") > 0){
            return "GAT";
        }
        else if(url.indexOf("QA") > 0){
            return "QA";
        }
        else if(url.indexOf("UAT") > 0){
            return "UAT";
        }
        else {
            return "Not Available";
        }

    }


    /*public static void main(String[] args){
        Build b1 = new Build(1234, "jeffrey", "Oct 16, 2015 2:46:48 PM", true);
        Build b2 = new Build(5678, "asdf", "Oct 11, 2015 2:46:48 PM", false);

        boolean is = b1.isMostRecent(b2);
        System.out.print("df");
    }*/
}