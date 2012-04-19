public class CommandResult {
    public static final int COMMAND_SUCCESS = 0;
    public static final int ERROR = -1;
    private final int retValue;
    private final String commandOutput;
    public static final CommandResult DEFAULT_FAILED = new CommandResult(ERROR, "");
    public static final CommandResult DEFAULT_SUCCESS = new CommandResult(COMMAND_SUCCESS, "");

    public CommandResult(int retValue, String CommandOutput) {
        this.retValue = retValue;
        commandOutput = CommandOutput;
    }

    public boolean isSuccessful() {
        return retValue == COMMAND_SUCCESS;
    }

    public String output() {
        return commandOutput;
    }
}
