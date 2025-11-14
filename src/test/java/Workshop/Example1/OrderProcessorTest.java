package Workshop.Example1;

import Workshop.Example1.Other.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderProcessorTest {
    @Mock private PaymentGateway paymentGateway;
    @Mock private InventoryService inventoryService;
    @Mock private EmailService emailService;
    @Mock private SlackNotifier slackNotifier;
    @Mock private AuditLogger auditLogger;
    @Mock private CustomerDatabase customerDb;
    @Mock private ProductCatalog productCatalog;
    @Mock private ShippingCalculator shippingCalc;
    @Mock private TaxService taxService;
    @Mock private CouponValidator couponValidator;
    @Mock private FraudDetector fraudDetector;
    @Mock private MetricsCollector metricsCollector;
    @Mock private ConfigurationService configService;
    @Mock private CacheManager cacheManager;
    @Mock private ExternalApiClient externalApiClient;

    private OrderProcessor orderProcessor;

    @BeforeEach
    void setUp() {
        // 180 lines of mock setup code...
        when(customerDb.findById(any())).thenReturn(createTestCustomer());
        when(productCatalog.findProduct(any())).thenReturn(createTestProduct());
        when(inventoryService.checkAvailability(any())).thenReturn(new Availability(true, 10));
        when(paymentGateway.authorize(any())).thenReturn(true);
        when(shippingCalc.calculateCost(any(), any())).thenReturn(5.99);
        when(taxService.calculateTax(any())).thenReturn(2.50);
        when(couponValidator.validate(any())).thenReturn(ValidationResult.valid());
        when(fraudDetector.screenOrder(any())).thenReturn(new Order(Money.of(102.49)));
        // ... 150+ more lines

        orderProcessor = new OrderProcessor(
                paymentGateway, inventoryService, emailService, slackNotifier,
                auditLogger, customerDb, productCatalog, shippingCalc,
                taxService, couponValidator, fraudDetector, metricsCollector,
                configService, cacheManager, externalApiClient
        );
    }


    private Product createTestProduct() {
        return new Product("TEST-PRODUCT", "Test Product", Money.of(100.0));
    }

    private Customer createTestCustomer() {
        return new Customer("CUST-001", "Test Customer", "test@example.com");
    }

    @Test
    void shouldCalculateOrderTotalCorrectly() {
        // The actual business logic being tested:
        // total = subtotal + shipping + tax - discount

        Order order = orderProcessor.processOrder(createTestOrderRequest());

        assertThat(order.getTotal()).isEqualTo(Money.of(102.49));
        // ^ This is the only line that matters!
    }



    private OrderRequest createTestOrderRequest() {
        return new OrderRequest("CUST-001", "TEST-PRODUCT", 1);
    }
}
