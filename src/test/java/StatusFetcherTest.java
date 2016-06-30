import com.iworkscorp.dashboard.hudson.StatusFetcher;
import com.iworkscorp.dashboard.hudson.TestBase;
import org.junit.Test;

/**
 * Created by tmadison on 6/30/16.
 */
public class StatusFetcherTest {
    private StatusFetcher statusFetcher = new StatusFetcher();

    @Test
    public void testConnectToHudson() throws Exception {
        TestBase.initialize();
        statusFetcher.connectToHudson();
    }
}
