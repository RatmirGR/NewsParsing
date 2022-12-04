# NewsParsing
Веб-сервис по парсингу новостного сайта с использованием jsoup, принимающий json и возвращающий результаты в формате json.

Инструкция для запуска в терминале:

<b>1)</b> Скачать проект

<b>2)</b> Зайти в проект и выполнить команду: ./mvnw package
[фото]
<p>(Примечание: Если maven не установлен, то нужно сначала запустить скрипт для установки maven команда: mvnw (для Linux/Mac) или mvnw.cmd (для Windows))</p>

<b>3)</b> Зайти в созданный пакет target и выполнить команду: java -jar webservice-0.0.1-SNAPSHOT.jar
<p>(Примечание: Приложение прослушивает порт 8080, поэтому нужно закрыть приложение, которое использует этот порт)</p>
[фото]

<b>4)</b> После запуска приложения нужно сделать post-запрос, передав json
  <p>curl -X POST http://localhost:8080/load -H 'Content-Type: application/json' -d '{ "date": "2022-11-11"}'</p>
или
  <ol>Воспользоваться Postman:</ol>
    <li> указав в url: http://localhost:8080/load</li>
    <li> указав в разделе Headers:
        <p>Content-Type: application/json</p>
        <p>Accept: application/json</p>
    </li>
    <li> указав в разделе Body json: { "date": "2022-11-11"}</li>


<b>5)</b> Для получение данных в формате json, нужно сделать get-запрос
  curl -X GET 'http://localhost:8080/info?date=2022-11-11'
или
  Воспользоваться Postman:
    - http://localhost:8080/info?date=2022-11-15


<p>Примечание: В качестве даты, нужно указывать любую дату в формате yyyy-MM-dd.</p>
<p>Примечание: Чтобы закрыть завершить программу, в терминале нужно нажать Ctrl+C</p>
