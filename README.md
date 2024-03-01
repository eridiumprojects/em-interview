## Инструкция по сборке

1. Сделать git clone этого репозитория.
2. Перейти в корневую папку проекта `em-interview/docker/`.
3. Выполнить команду `docker compose up`.
4. Выполнить `mvn clean package` внутри проекта, чтобы сгенерировать классы MapStruct и скомпоновать проект.

## Инструкция по импортированию конфига

Для отправки и проверки запросов я использовал приложение Insomnia (аналог Postman), в корне репозитория я оставил файл `em-config.yaml`, который можно будет загрузить в Insomnia либо Postman, и вы сможете получить все тестовые сценарии со всеми эндпоинтами.

## Инструкция по ручному тестированию эндпоинтов

1. Используйте ручку `Sign up` и отправьте тестовый запрос на сервер.
2. Далее, тыкаем в эндпоинт `Sign In` и отправляем данные нового пользователя.
3. В Response придут Access и Refresh токены (JWT), копируем Access Token и находим в приложении `Base Environment`, куда вставляем Access токен (см. рис).
4. Готово. Любыми другими эндпоинтами можно пользоваться.

![insomnia_plot1](https://github.com/eridiumprojects/em-interview/blob/master/insomnia_plot1.png)

P.S Access token внутри системы действует 30 минут, по истечению этого времени клиенту придется войти в систему заново.

## Swagger-документация

Доступна по адресу: http://localhost:8080/api/swagger-ui/index.html#
[swagger_plot1](https://github.com/eridiumprojects/em-interview/blob/master/swagger_plot1.png)
