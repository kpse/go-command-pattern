public class GoServer {
    private GoCodeBase codeBase;
    private Action action;
    private boolean needRerun;
    private CommandResult commandResult;

    public GoServer(GoCodeBase codeBase) {
        this.codeBase = codeBase;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public GoCodeBase getCodeBase() {
        return codeBase;
    }

    public boolean shouldRerun() {
        return !commandResult.isSuccessful();
    }

    public String report() {
        return commandResult.output();
    }

    public void gatherResult(CommandResult commandResult) {
        this.commandResult = commandResult;
    }
}
