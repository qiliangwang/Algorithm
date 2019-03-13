http://localhost:8080/order/dev

```json
{
    "name": "order",
    "profiles": [
        "dev"
    ],
    "label": null,
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "classpath:/config-repo/order-dev.yml",
            "source": {
                "spring.application.name": "order",
                "spring.datasource.driver-class-name": "com.mysql.jdbc.Driver",
                "spring.datasource.username": "root",
                "spring.datasource.password": 123456,
                "spring.datasource.url": "jdbc:mysql://127.0.0.1:3306/SpringCloud_Sell?characterEncoding=utf-8&useSSL=false",
                "eureka.client.service-url.defaultZone": "http://localhost:8761/eureka/",
                "env": "dev"
            }
        }
    ]
}
```

http://localhost:9901/i18n-dev.yml

http://localhost:9901/i18n/dev

http://localhost:9901/i18n/pre