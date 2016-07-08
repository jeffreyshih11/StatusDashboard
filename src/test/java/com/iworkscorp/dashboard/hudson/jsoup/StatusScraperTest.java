package com.iworkscorp.dashboard.hudson.jsoup;

import org.junit.Test;

/**
 * Created by tmadison on 7/7/16.
 */
public class StatusScraperTest {
    StatusScraper statusScraper = new StatusScraper();

    @Test
    public void testTest() {
        System.setProperty("jsse.enableSNIExtension", "false");
        statusScraper.test("http://google.com");
        statusScraper.test("https://office.iworkscorp.com/hudson/login");
    }
}
