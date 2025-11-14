package Workshop.Example2.Fakes;

import Workshop.Example2.Other.AuditLogger;

import java.util.ArrayList;
import java.util.List;

public class SpyAuditLogger implements AuditLogger {
    private List<String> loggedMessages = new ArrayList<>();

    @Override
    public void log(String message) {
        loggedMessages.add(message);
    }

    public List<String> getLoggedMessages() { return new ArrayList<>(loggedMessages); }
    public boolean wasMessageLogged(String message) { return loggedMessages.contains(message); }
}
