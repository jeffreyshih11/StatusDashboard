package com.iworkscorp.dashboard.hudson;

import static com.iworkscorp.dashboard.hudson.TestBase.driver;

/**
 * Created by jshih on 6/28/2016.
 */
public class Build {

    int revision;
    String builder;
    String dateBuilt;
    boolean status;

    public Build() {
        this.revision = 0;
        this.builder = "";
        this.dateBuilt = "";
        status = false;
    }

    public Build(int revision, String builder, String dateBuilt, boolean status) {
        this.revision = revision;
        this.builder = builder;
        this.dateBuilt = dateBuilt;
        this.status = status;
    }

    public Build(String url) {
        driver.navigate().to(url);
        String pageSource = driver.getPageSource();
        int revIndex = pageSource.indexOf("\"revision\":");
        this.revision = Integer.parseInt(pageSource.substring(revIndex + 11, revIndex + 16));

        int userIndex = pageSource.indexOf("Started by user");
        this.builder = pageSource.substring(userIndex + 16, pageSource.indexOf("userName") - 3);

        int dateIndex = pageSource.indexOf("id");
        this.dateBuilt = pageSource.substring(dateIndex + 5, pageSource.indexOf("keepLog") - 3);

        this.status = false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isMostRecent(Build other) {

        String[] thisFullTime = dateBuilt.split("_");
        String[] otherFullTime = other.dateBuilt.split("_");
        String[] thisDate = thisFullTime[0].split("-");
        String[] thisTime = thisFullTime[1].split("-");
        String[] otherDate = otherFullTime[0].split("-");
        String[] otherTime = otherFullTime[1].split("-");

        int thisYear = Integer.parseInt(thisDate[0]);
        int thisMonth = Integer.parseInt(thisDate[1]);
        int thisDay = Integer.parseInt(thisDate[2]);

        int otherYear = Integer.parseInt(otherDate[0]);
        int otherMonth = Integer.parseInt(otherDate[1]);
        int otherDay = Integer.parseInt(otherDate[2]);

        if (thisYear > otherYear) {
            return true;
        } else if (thisYear == otherYear) {
            if (thisMonth > otherMonth) {
                return true;
            } else if (thisMonth == otherMonth) {
                if (thisDay > otherDay) {
                    return true;
                } else if (thisDay == otherDay) {
                    int thisHour = Integer.parseInt(thisTime[0]);
                    int thisMin = Integer.parseInt(thisTime[1]);
                    int thisSec = Integer.parseInt(thisTime[2]);

                    int otherHour = Integer.parseInt(otherTime[0]);
                    int otherMin = Integer.parseInt(otherTime[1]);
                    int otherSec = Integer.parseInt(otherTime[2]);

                    if (thisHour > otherHour) {
                        return true;
                    } else if (thisHour == otherHour) {
                        if (thisMin > otherMin) {
                            return true;
                        } else if (thisMin == otherMin) {
                            if (thisSec > otherSec) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Build b1 = new Build(1234, "jeffrey", "2016-06-22_13-30-58", true);
        Build b2 = new Build(5678, "asdf", "2016-06-22_13-30-59", false);

        boolean is = b1.isMostRecent(b2);
        System.out.print("df");
    }
}
