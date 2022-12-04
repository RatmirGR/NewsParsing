# NewsParsing
Веб-сервис по парсингу новостного сайта с использованием jsoup, принимающий json и возвращающий результаты в формате json.

Инструкция для запуска приложения в терминале:

<b>1)</b> Скачать проект

<b>2)</b> Зайти в проект и выполнить команду: ./mvnw package
<p><img src="https://github.com/RatmirGR/NewsParsing/blob/main/2.png" width="500" height="300"/></p>
<p>(Примечание: Если maven не установлен, то нужно сначала запустить скрипт для установки maven, выполнив команду: mvnw (для Linux/Mac) или mvnw.cmd (для Windows))</p>

<b>3)</b> Зайти в созданный пакет target и выполнить команду: java -jar webservice-0.0.1-SNAPSHOT.jar
<p>(Примечание: Приложение прослушивает порт 8080, поэтому нужно закрыть приложение, которое использует этот порт)</p>

<b>4)</b> После запуска приложения нужно сделать post-запрос в другом терминале, передав json
  <p>curl -X POST http://localhost:8080/load -H 'Content-Type: application/json' -d '{ "date": "2022-11-11"}'</p>
<p>или</p>
<ul><b>Воспользоваться Postman:</b>
    <li> указав в url: http://localhost:8080/load</li>
    <li> указав в разделе Headers: Content-Type: application/json и Accept: application/json
    </li>
    <li> указав в разделе Body json: { "date": "2022-11-11"}</li>
</ul>
<p><img src="https://github.com/RatmirGR/NewsParsing/blob/main/1.png"  width="500" height="500"/></p>
<p>Ответом будет "Ok" - это значит, что по переданной дате был произведен парсинг сайта https://news.ycombinator.com/, а полученный список новостей добавлен в базу данных</p>

<b>5)</b> Для получение данных в формате json, нужно сделать get-запрос
  <p>curl -X GET 'http://localhost:8080/info?date=2022-11-11'</p>
<p>или</p>
  <ul><b>Воспользоваться Postman или браузером:</b>
    <li>http://localhost:8080/info?date=2022-11-15</li>
  </ul>


<p>Примечание: В качестве даты, нужно указывать любую дату в формате yyyy-MM-dd.</p>
<p>Примечание: Чтобы завершить работу программы, в терминале нужно нажать Ctrl+C</p>

Инструкция для запуска приложения в IntelliJ IDEA:
