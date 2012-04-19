public class GoAgent {
    private WorkSpace workSpace;

    public GoAgent(WorkSpace workSpace) {
        this.workSpace = workSpace;
    }

    public void workWith(GoServer goServer) {
        Action action = goServer.getAction();
        CommandResult commandResult = action.execute(goServer.getCodeBase(), workSpace);
        goServer.gatherResult(commandResult);
    }
}
