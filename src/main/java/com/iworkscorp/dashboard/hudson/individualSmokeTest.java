package com.iworkscorp.dashboard.hudson;

/**
 * Created by jshih on 7/13/2016.
 */
public class individualSmokeTest{
    String environmentName;
    int revision;
    String status;


    public individualSmokeTest(){
        environmentName = "";
        revision = -1;
        status = "false";
    }
    public individualSmokeTest(String env){
        environmentName = env;
        revision = -1;
        status = "false";
    }

    public void setStatus(String set){
        status = set;
    }

    public void setRevision(int rev){
        revision = rev;
    }





}
