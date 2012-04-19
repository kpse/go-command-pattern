import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public enum Action {
    CHECKOUT {
        @Override
        public CommandResult execute(GoCodeBase codeBase, WorkSpace workSpace) {
            return runCommand("/usr/bin/git clone " + codeBase.getUrl() + " " + workSpace.getPath());
        }
    }, CLEAN_UP {
        @Override
        public CommandResult execute(GoCodeBase codeBase, WorkSpace workSpace) {
            return runCommand("/bin/rm -r " + workSpace.getPath());
        }
    }, DO_NOTHING {
        @Override
        public CommandResult execute(GoCodeBase codeBase, WorkSpace workSpace) {
            return CommandResult.DEFAULT_SUCCESS;
        }
    }, LIST_DIR {
        @Override
        public CommandResult execute(GoCodeBase codeBase, WorkSpace workSpace) {
            return runCommand("ls -ls " + workSpace.getPath());
        }
    };

    private static CommandResult runCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            String output = captureOutput(process);
            return new CommandResult(process.waitFor(), output);
        } catch (Exception e) {
            return CommandResult.DEFAULT_FAILED;
        }
    }

    private static String captureOutput(Process process) throws IOException {
        InputStream stdin = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdin);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder lines = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            lines.append(line);
            lines.append("\n");
        }
        return lines.toString();
    }

    public abstract CommandResult execute(GoCodeBase codeBase, WorkSpace workSpace);

}
