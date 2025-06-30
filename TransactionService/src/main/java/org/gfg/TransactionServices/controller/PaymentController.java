package org.gfg.TransactionServices.controller;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import org.gfg.TransactionServices.request.PaymentRequest;
import org.gfg.TransactionServices.response.PaymentResponse;
import org.gfg.TransactionServices.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stripe.model.Event;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<PaymentResponse> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
        try {
            // Validate request
            if (paymentRequest.getAmount() == null ) {
                return ResponseEntity.badRequest()
                        .body(new PaymentResponse(null, null, "failed", "Invalid amount"));
            }

            if (paymentRequest.getCurrency() == null || paymentRequest.getCurrency().isEmpty()) {
                paymentRequest.setCurrency("INR"); // default currency
            }

            PaymentResponse response = paymentService.createPaymentIntent(paymentRequest);

            if ("failed".equals(response.getStatus())) {
                return ResponseEntity.badRequest().body(response);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new PaymentResponse(null, null, "failed", "Internal server error"));
        }
    }

    @GetMapping(" ")
    public ResponseEntity<PaymentResponse> confirmPayment(@PathVariable String paymentIntentId) {
        try {
            PaymentResponse response = paymentService.confirmPayment(paymentIntentId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new PaymentResponse(null, null, "failed", "Error confirming payment"));
        }
    }

    @PostMapping("/refund")
    public ResponseEntity<Map<String, Object>> refundPayment(
            @RequestParam String paymentIntentId,
            @RequestParam(required = false) Long amount) {

        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = paymentService.refundPayment(paymentIntentId, amount);

            if (success) {
                response.put("status", "success");
                response.put("message", "Refund processed successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "failed");
                response.put("message", "Refund processing failed");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            response.put("status", "failed");
            response.put("message", "Error processing refund");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {

        // Webhook endpoint secret - set this in your application.properties
        String endpointSecret = "whsec_your_webhook_secret_here";

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid payload");
        }

        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().orElse(null);
                // Handle successful payment
                handleSuccessfulPayment(paymentIntent);
                break;
            case "payment_intent.payment_failed":
                PaymentIntent failedPayment = (PaymentIntent) event.getDataObjectDeserializer().getObject().orElse(null);
                // Handle failed payment
                handleFailedPayment(failedPayment);
                break;
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok("Success");
    }

    private void handleSuccessfulPayment(PaymentIntent paymentIntent) {
        // Update order status in your database
        // Send confirmation email
        // Update inventory
        System.out.println("Payment succeeded for: " + paymentIntent.getId());
    }

    private void handleFailedPayment(PaymentIntent paymentIntent) {
        // Handle failed payment
        // Maybe send notification to customer
        System.out.println("Payment failed for: " + paymentIntent.getId());
    }
}