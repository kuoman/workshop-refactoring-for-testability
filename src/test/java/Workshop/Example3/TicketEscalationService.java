package Workshop.Example3;

import Workshop.Example3.Other.*;

public class TicketEscalationService {
    private static final String SLACK_TOKEN = "slack token";

    // TODO: Refactor this monster method
    public void processTicket(Ticket ticket) {
        DatabaseConnection db = ConnectionPool.getConnection();
        EmailSender emailSender = new SmtpEmailSender();
        SlackClient slack = new SlackClient(SLACK_TOKEN);
        AuditTrail audit = new DatabaseAuditTrail(db);

        // Business rules buried in infrastructure:
        Customer customer = db.query("SELECT * FROM customers WHERE id = ?", ticket.getCustomerId());

        long responseTime = System.currentTimeMillis() - ticket.getCreatedTime();
        boolean shouldEscalate = false;

        if (customer.getTier().equals("PREMIUM") && responseTime > 3600000) { // 1 hour
            shouldEscalate = true;
        } else if (customer.getTier().equals("STANDARD") && responseTime > 7200000) { // 2 hours
            shouldEscalate = true;
        } else if (customer.getTier().equals("BASIC") && responseTime > 86400000) { // 24 hours
            shouldEscalate = true;
        }

        if (shouldEscalate) {
            ticket.setStatus("ESCALATED");
            db.update("UPDATE tickets SET status = 'ESCALATED' WHERE id = ?", ticket.getId());

            emailSender.send(createEscalationEmail(ticket, customer));
            slack.sendMessage("#support-escalations", "Ticket " + ticket.getId() + " escalated");
            audit.log("ESCALATION", "Ticket " + ticket.getId() + " escalated for customer " + customer.getId());
        }
    }

    private EmailSender createEscalationEmail(Ticket ticket, Customer customer) {
        return null;
    }
}
