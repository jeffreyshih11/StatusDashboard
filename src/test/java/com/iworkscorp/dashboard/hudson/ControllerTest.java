package com.iworkscorp.dashboard.hudson;

import org.junit.Test;

/**
 * Created by jshih on 7/14/2016.
 */
public class ControllerTest {
    private Controller con = new Controller();

    @Test
    public void setUpTest(){
        try {
            con.generateBuildInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.generateSmokeDoc();

        /*con.smokeInfo = con.readSmokeTestXML(con.smokeStatusDoc);
        con.results = con.createSmokeTestObjFromXML(con.smokeInfo);*/

        /*con.checkAndRunSmoke(con.builds.get(1), con.results.get(1));
        con.runAllSmokeTests();
        con.overWriteSmokeDoc();
        con.buildInfo = con.readBuildXML(con.environmentStatusDoc);

        con.builds = con.createBuildsFromXMLNodes(con.buildInfo);*/
    }

    @Test
    public void createBuildsTest(){
        con.createSmokeTestObjFromXML(con.smokeInfo);
        con.createBuildsFromXMLNodes(con.buildInfo);
        System.out.print("done");
    }

    @Test
    public void overWriteTest(){
        try {
            con.createBuildsFromXMLNodes(con.buildInfo);
            con.createSmokeTestObjFromXML(con.smokeInfo);
            con.checkAndRunSmoke(con.builds.get(0), con.results.get(0));
            con.checkAndRunSmoke(con.builds.get(1), con.results.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void webBrowsertest(){
        try{
            con.generateBuildInfo();
            con.createBuildsFromXMLNodes(con.buildInfo);
            con.createSmokeTestObjFromXML(con.smokeInfo);
            con.checkAndRunSmoke(con.builds.get(1), con.results.get(1));
            //con.checkAndRunSmoke(con.builds.get(4), con.results.get(4));
            con.closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DEVTEST(){
        con.smokeTest.environmentValidationDEV();
    }

    @Test
    public void runAllSmokes(){
        con.createBuildsFromXMLNodes(con.buildInfo);
        con.createSmokeTestObjFromXML(con.smokeInfo);
        try {
            con.runAllSmokeTests();
            con.closeBrowser();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
