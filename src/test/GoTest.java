import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class GoTest {

    public static final String FLAG_FILE = "/tmp/emptyrepo/README";
    private static final WorkSpace workSpace = new WorkSpace("/tmp/emptyrepo");
    private static final GoCodeBase codeBase = new GoCodeBase("git@github.com:khu/emptyrepo.git");

    @Before
    public void setUp() throws Exception {
        workSpace.forceClean();
    }

    @After
    public void tearDown() throws Exception {
        workSpace.forceClean();
    }

    @Test
    public void should_do_nothing_by_default() throws Exception {
        GoServer goServer = createServerWithAction(null, Action.DO_NOTHING);
        new GoAgent(workSpace).workWith(goServer);
        assertThat(isFileExist(), is(false));
    }

    @Test
    public void should_checkout_code_from_somewhere() throws Exception {
        GoServer checkOutServer = createServerWithAction(codeBase, Action.CHECKOUT);
        new GoAgent(workSpace).workWith(checkOutServer);
        assertThat(isFileExist(), is(true));

    }

    @Test
    public void should_clean_up() throws Exception {
        workSpaceExists(codeBase);
        GoServer cleanUpServer = createServerWithAction(codeBase, Action.CLEAN_UP);

        new GoAgent(workSpace).workWith(cleanUpServer);

        assertThat(isFileExist(), is(false));
    }

    @Test
    public void should_fail_while_clean_up_return_error() throws Exception {
        GoServer cleanUpServer = createServerWithAction(codeBase, Action.CLEAN_UP);

        new GoAgent(workSpace).workWith(cleanUpServer);

        assertThat(cleanUpServer.shouldRerun(), is(true));
    }

    @Test
    public void should_report_ls_command_result() throws Exception {
        workSpaceExists(codeBase);
        GoServer lsCommandServer = createServerWithAction(codeBase, Action.LIST_DIR);

        new GoAgent(workSpace).workWith(lsCommandServer);
        assertThat(lsCommandServer.shouldRerun(), is(false));
        assertThat(lsCommandServer.report().isEmpty(), is(false));
    }

    private void workSpaceExists(GoCodeBase codeBase) {
        Action.CHECKOUT.execute(codeBase, workSpace);
    }

    private boolean isFileExist() throws IOException {
        return workSpace.contains(FLAG_FILE);
    }

    private GoServer createServerWithAction(GoCodeBase codeBase, Action action) {
        GoServer goServer = new GoServer(codeBase);
        goServer.setAction(action);
        return goServer;
    }
}
