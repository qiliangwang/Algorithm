---
title: Kafka Spring
date: 2019-07-12 09:06:42
tags:
---

在SpringBoot中集成kafka

Gradle依赖

```groovy
//spring
compile("org.springframework.boot:spring-boot-starter-web:2.1.5.RELEASE")
//Kafka
compile ("org.springframework.kafka:spring-kafka:2.2.0.RELEASE")
```

抽象发送消息这个动作为一个接口，具体的有Kafka，RabbitMQ等等实现。

```java
public interface ISender {

    /**
     * 发送数据到kafka
     * @param event
     */
    void sender(NotificationEvent event);
}
```

发送的是一个Event对象

```java
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class NotificationEvent {

    /**
     * @see NotificationEnum
     */
    private String type;

    private String topic;

    private List<String> missionIds;
}
```

```java
@Component("kafkaSender")
@Log
public class KafkaSender implements ISender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Autowired
    private INotificationService notificationService;

    @Override
    public void sender(NotificationEvent event) {

        kafkaTemplate.send(
                event.getTopic(), JSON.toJSONString(event)
        );
    }

    @KafkaListener(topics = {"notification-bus"}, groupId = "notification")
    public void processBossGroupData(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();

            NotificationEvent weChatEvent = JSON.parseObject(
                    message.toString(),
                NotificationEvent.class
            );
            weChatEvent.setTopic(EventTopicEnum.WE_CHAT.getTopic());

            NotificationEvent backendEvent = JSON.parseObject(
                message.toString(),
                NotificationEvent.class
            );
            backendEvent.setTopic(EventTopicEnum.BACKEND.getTopic());

            this.sender(weChatEvent);
            this.sender(backendEvent);

            log.info("kafka process bus data: " + message.toString());
        }
    }

    @KafkaListener(topics = {"notification-wechat"}, groupId = "notification")
    public void processWeChatData(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            NotificationEvent event = JSON.parseObject(
                message.toString(),
                NotificationEvent.class
            );
            if (! EventTopicEnum.WE_CHAT.getTopic().equals(event.getTopic())) {
                return;
            }
            notificationService.notificationWechat(event);
            log.info("kafka process wechat data: " + message.toString());
        }
    }

    @KafkaListener(topics = {"notification-backend"}, groupId = "notification")
    public void processBackendData(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            NotificationEvent event = JSON.parseObject(
                message.toString(),
                NotificationEvent.class
            );
            if (! EventTopicEnum.BACKEND.getTopic().equals(event.getTopic())) {
                return;
            }
            notificationService.notificationBackend(event);
            log.info("kafka process backend data: " + message.toString());
        }
    }
}
```

