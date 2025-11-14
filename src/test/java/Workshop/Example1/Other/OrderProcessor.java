package Workshop.Example1.Other;

public class OrderProcessor {
    public OrderProcessor(PaymentGateway paymentGateway, InventoryService inventoryService, EmailService emailService, SlackNotifier slackNotifier, AuditLogger auditLogger, CustomerDatabase customerDb, ProductCatalog productCatalog, ShippingCalculator shippingCalc, TaxService taxService, CouponValidator couponValidator, FraudDetector fraudDetector, MetricsCollector metricsCollector, ConfigurationService configService, CacheManager cacheManager, ExternalApiClient externalApiClient) {
    }

    public Order processOrder(OrderRequest testOrderRequest) {
        return null;
    }
}
