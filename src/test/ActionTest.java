import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ActionTest {

    private final WorkSpace workSpace = new WorkSpace("/tmp/emptyrepo/");
    private final GoCodeBase codeBase = new GoCodeBase("git@github.com:khu/emptyrepo.git");

    @Before
    public void setUp() throws Exception {
        workSpace.forceClean();
    }

    @After
    public void tearDown() throws Exception {
        workSpace.forceClean();
    }

    @Test
    public void should_checkout() throws Exception {
        Action.CHECKOUT.execute(codeBase, workSpace);
        assertThat(workSpace.contains("/tmp/emptyrepo/README"), is(true));
    }

    @Test
    public void should_list_dir() throws Exception {
        CommandResult result = Action.LIST_DIR.execute(null, new WorkSpace("/tmp"));
        assertThat(result.output().contains(" root "), is(true));
    }
}
