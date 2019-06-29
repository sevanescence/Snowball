package throwaway.command;

class Invalid_Command extends CommandExecutor {

    Invalid_Command() {
    }

    @Override
    public boolean cast(String label, String[] args) {
        return false;
    }

}
