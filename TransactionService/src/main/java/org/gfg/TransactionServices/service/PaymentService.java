package org.gfg.TransactionServices.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import org.gfg.TransactionServices.request.PaymentRequest;
import org.gfg.TransactionServices.response.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
public class PaymentService {
    private static final Logger logger =  LoggerFactory.getLogger(PaymentService.class);

    public PaymentResponse createPaymentIntent(PaymentRequest paymentRequest) {
        try {
            // Create customer first
            Customer customer = createCustomer(paymentRequest.getCustomerEmail(), paymentRequest.getCustomerName());

            // Create payment intent parameters
            Map<String, Object> params = new HashMap<>();
            params.put("amount", paymentRequest.getAmount().multiply(BigDecimal.valueOf(100)).longValue()); // for INR
            params.put("currency", paymentRequest.getCurrency());
            params.put("description", paymentRequest.getDescription());
            params.put("customer", customer.getId());

            // Add metadata
            Map<String, String> metadata = new HashMap<>();
            metadata.put("customer_email", paymentRequest.getCustomerEmail());
            metadata.put("customer_name", paymentRequest.getCustomerName());
            params.put("metadata", metadata);

            // Set automatic payment methods
            params.put("automatic_payment_methods", Map.of("enabled", true));

            // Create PaymentIntent
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            return new PaymentResponse(
                    paymentIntent.getClientSecret(),
                    paymentIntent.getId(),
                    paymentIntent.getStatus(),
                    "Payment intent created successfully"
            );

        } catch (StripeException e) {
            logger.error("Error creating payment intent: ", e);
            return new PaymentResponse(null, null, "failed", "Error creating payment: " + e.getMessage());
        }
    }

    private Customer createCustomer(String email, String name) throws StripeException {
        // Check if customer already exists
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("email", email);
        customerParams.put("limit", 1);

        CustomerCollection existingCustomers = Customer.list(customerParams);

        if (!existingCustomers.getData().isEmpty()) {
            return existingCustomers.getData().get(0);
        }

        // Create new customer
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("name", name);

        return Customer.create(params);
    }

    public PaymentResponse confirmPayment(String paymentIntentId) {
        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

            return new PaymentResponse(
                    paymentIntent.getClientSecret(),
                    paymentIntent.getId(),
                    paymentIntent.getStatus(),
                    "Payment status retrieved successfully"
            );

        } catch (StripeException e) {
            logger.error("Error confirming payment: ", e);
            return new PaymentResponse(null, null, "failed", "Error confirming payment: " + e.getMessage());
        }
    }

    public boolean refundPayment(String paymentIntentId, Long amount) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("payment_intent", paymentIntentId);
            if (amount != null) {
                params.put("amount", amount);
            }

            Refund.create(params);
            return true;

        } catch (StripeException e) {
            logger.error("Error processing refund: ", e);
            return false;
        }
    }
}
