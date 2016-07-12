package com.iworkscorp.dashboard.hudson;

import org.junit.Test;

/**
 * Created by tmadison on 6/30/16.
 */
public class StatusFetcherTest {
    private StatusFetcher statusFetcher = new StatusFetcher();

    @Test
    public void testConnectToHudson() throws Exception {
        TestBase.initialize();
        statusFetcher.initialize();
        statusFetcher.collectFromHudson();
    }



}
