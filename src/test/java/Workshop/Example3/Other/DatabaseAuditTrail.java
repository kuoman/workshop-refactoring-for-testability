package Workshop.Example3.Other;

public class DatabaseAuditTrail implements AuditTrail{
    public DatabaseAuditTrail(DatabaseConnection db) {
    }

    @Override
    public void log(String escalation, String s) {

    }
}
