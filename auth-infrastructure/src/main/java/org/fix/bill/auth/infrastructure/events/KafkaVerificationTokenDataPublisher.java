package org.fix.bill.auth.infrastructure.events;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.fix.bill.auth.core.domain.application.outbound.VerificationTokenDataPublisher;
import org.fix.bill.auth.core.domain.model.authentication.VerificationTokenData;
import org.fix.bill.auth.infrastructure.events.configuration.VerificationLinkProvider;
import org.fix.bill.notification.api.NotificationRequestedEvent;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaVerificationTokenDataPublisher implements VerificationTokenDataPublisher {

    private static final String TOPIC = "internal.notification.requested";
    private static final String EVENT_TYPE = "REGISTRATION_EMAIL_VERIFICATION";
    private static final String CHANNEL_EMAIL = "EMAIL";
    private static final String TEMPLATE_CODE = "AUTH_REGISTRATION_VERIFY_EMAIL";
    private static final String CATEGORY_REGISTRATION = "REGISTRATION";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final VerificationLinkProvider verificationLinkProvider;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publish(VerificationTokenData verificationTokenData) {
        NotificationRequestedEvent notificationRequestedEvent = buildNotificationEvent(verificationTokenData);
        kafkaTemplate.send(TOPIC, verificationTokenData.getEmail(), notificationRequestedEvent);
    }

    private NotificationRequestedEvent buildNotificationEvent(VerificationTokenData verificationTokenData) {
        String verificationLink = getVerificationLink(verificationTokenData.getActivationToken());

        Map<String, String> data = new HashMap<>();
        data.put("verificationLink", verificationLink);
        data.put("email", verificationTokenData.getEmail());

        Map<String, String> metadata = new HashMap<>();
        metadata.put("sourceSystem", "AUTH-SERVICE");

        Locale locale =  LocaleContextHolder.getLocale();
        String localeString = locale.toLanguageTag();


        return NotificationRequestedEvent.newBuilder()
                .setType(EVENT_TYPE)
                .setChannel(CHANNEL_EMAIL)
                .setRecipient(verificationTokenData.getEmail())
                .setTemplateCode(TEMPLATE_CODE)
                .setCategory(CATEGORY_REGISTRATION)
                .setCorrelationId(UUID.randomUUID().toString())
                .setData(data)
                .setMetadata(metadata)
                .setLocale(localeString)
                .build();
    }


    public String getVerificationLink(String token) {
        try {
            URIBuilder builder = new URIBuilder(verificationLinkProvider.getAddress());
            builder.addParameter("token", token);
            return builder.build().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
