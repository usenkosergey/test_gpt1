package ru.demo.gpt;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Usenko Sergey, 23.06.2024
 */
public class Async {
    public static void main(String[] args) {
        {
            String apiKey = "AQVN3TUSruyi7aGYUDCLKcYJiDQxS9CeVVa61VbD";
            String folderId = "b1gtqtr2emf3sv24gi8g";

            String t1 = "Ты ассистент поликлиники «Талисия». " +
                    "Ты знаешь только про эту поликлинику. " +
                    "Больше у тебя нет никаких данных. " +
                    "Ты можешь отвечать только про поликлинику «Талисия». " +
                    "Если вопрос касается чего-то другого, " +
                    "ты должен отвечать – я ассистент поликлиники «Талисия» и могу отвечать только про нее. " +
                    "Какие процедуры есть в поликлинике?";
            String t2 = "О поликлинике «Талисия». " +
                    "Более 10 лет заботимся о красоте и здоровье. " +
                    "Сеть клиник в Центральном и Южном регионах. " +
                    "Более 10.000 довольных клиентов. " +
                    "Система лояльности, накопительных скидок, абонементов, рассрочка. " +
                    "Ассоциация Отельеров АМОС* рекомендует! " +
                    "Лицензия на осуществление медицинской деятельности №Л041-01126-23/00643266. " +
                    "с опытом работы от 5 до 20+лет. горячо увлеченные своим делом. постоянно расширяющие круг своих компетенций. внимательно и бережно относящиеся к здоровью каждого пациента. ФОТОТЕРАПИЯ НА АППАРАТЕ IPL Quantum. Лечение пигментации, сосудов, купероза и розацеа. Как работает аппарат IPL-Quantum генерирует импульсное световое излучение с разной длиной волны. Параметры рассчитываются для каждой процедуры и с учетом индивидуальных особенностей кожи пациента. Излучение избирательно разрушает патологические участки дермы и эпидермиса: скопления меланина, то есть пигментные пятна; расширенные сосуды; волосяные фолликулы; поврежденные клетки. Такое воздействие стимулирует и собственные ресурсы кожи. В ней усиливается выработка молодых клеток, что приводит к положительным внешним изменениям: повышению эластичности, лифтингу, исчезновению дефектов. Эпидермис, мышцы, нервы, здоровые сосуды остаются неприкосновенными. Световые импульсы влияют на более глубокие слои кожи и только на их дефектные участки. В каких услугах используют IPL-Quantum Аппарат применяют для: фотоомоложения лица, шеи, декольте, рук, после которого кожа становится подтянутой, гладкой, приобретает здоровый цвет; устранения купероза, при котором растянутые сосуды склеиваются и перестают просвечиваться сквозь кожу; удаления пигментных пятен, розацеа, в результате чего разрушаются скопления меланина, и цвет участка становится равномерным. Эффект от процедуры микроигольчатого RF-лифтинга достигается за счет механического травмирования кожи микроиглами и термического воздействия. Основной целью процедуры является улучшение качества кожи через запуск регенерационных процессов. Процедура Безоперационная подтяжка лица: Микроигольчатый RF-лифтинг DeAge-EX DUAL. Показания к применению: дряблая, потерявшая упругость кожа, избыточная жирность и пористость кожи, морщины, неровный рельеф, цвет и тон кожи, атрофические рубцы, в т.ч. постакне растяжки, стрии. Возможные процедуры: Тело: лифтинг кожи, выравнивание рельефа и цвета кожи, лечение гипергидроза подмышечных впадин, коррекция стрий. Лицо: коррекция гравитационного птоза, повышение тонуса и тургора кожи, уменьшение глубины морщин, выравнивание рельефа кожи, сужение расширенных пор, улучшение цвета и выравнивание тона, коррекция шрамов и рубцов, лечение угревой сыпи и постакне. Первый адрес поликлиники «Талисия»: Сочи, Красноармейская ул., 4/1, микрорайон Заречный. Второй адрес поликлиники «Талисия»: поселок городского типа Красная Поляна, ул. Трудовой Славы, д.4 ";

//"/yandexgpt-lite/rc\",\n" +
// /yandexgpt/latest - пересказ
            String st1 = "Сделай краткий пересказ статьи.";
            String st2 = "ФОТОТЕРАПИЯ НА АППАРАТЕ IPL Quantum. Лечение пигментации, сосудов, купероза и розацеа. Как работает аппарат IPL-Quantum генерирует импульсное световое излучение с разной длиной волны. Параметры рассчитываются для каждой процедуры и с учетом индивидуальных особенностей кожи пациента. Излучение избирательно разрушает патологические участки дермы и эпидермиса:. скопления меланина, то есть пигментные пятна;. расширенные сосуды;. волосяные фолликулы;. поврежденные клетки. Такое воздействие стимулирует и собственные ресурсы кожи. В ней усиливается выработка молодых клеток, что приводит к положительным внешним изменениям: повышению эластичности, лифтингу, исчезновению дефектов. Эпидермис, мышцы, нервы, здоровые сосуды остаются неприкосновенными. Световые импульсы влияют на более глубокие слои кожи и только на их дефектные участки. В каких услугах используют IPL-Quantum. Аппарат применяют для:. фотоомоложения лица, шеи, декольте, рук, после которого кожа становится подтянутой, гладкой, приобретает здоровый цвет;. устранения купероза, при котором растянутые сосуды склеиваются и перестают просвечиваться сквозь кожу;. удаления пигментных пятен, розацеа, в результате чего разрушаются скопления меланина, и цвет участка становится равномерным. Каково воздействие IPL-Quantum. Аппарат генерирует вспышки света, которыми в течение 30-50 минут обрабатывают кожу. Микротравмы вызывают незначительный дискомфорт, который гасится охлаждающей системой. Для улучшения проводимости света используют гель. Последовательность процедуры такая:. Кожу очищают, оценивают ее особенности. Косметолог настраивает аппарат на нужные показатели. Оба участника сеанса надевают защитные очки. Косметолог приближает манипулу устройства к поверхности кожи, и излучение воздействует на ее глубокие слои. Когда все участки обработаны, используют очищающий и успокаивающий состав. После процедуры кожа розовеет и становится более чувствительной, а пигментные пятна бледнеют. Затем 2-3 недели в ней идет восстановительный процесс. За курс удается добиться следующих изменений:. разгладить мелкие и сократить глубокие морщин;. повысить тонус кожи, подтянуть овал;. избавить ее от тусклого цвета, гиперпигментации и сосудистых «звездочек»;. восстановить ровный рельеф после угрей;. сузить поры;. удалить лишние волосы с волосяными луковицами. Почему именно IPL-Quantum. избавляет от недостатков в сложных случаях, когда менее мощные устройства справляются недостаточно;. обеспечивает комфортность процедуры без обезболивания;. не оставляет на коже видимых надрезов, проколов, папул, ожогов;. дает возможность в течение курса работать и вести привычный образ жизни;. изменения заметны через 7-10 дней после единственного сеанса;. дает результат за 3-6 процедур, а с другими аппаратами может потребоваться до 10;. оставляет лифтинг-эффект на 6-12 месяцев;. безопасность подтверждена 20 годами использования.";

            String jsonObject = "{\"modelUri\": \"gpt://" + folderId + "/yandexgpt/latest\",\n" +
                    "  \"completionOptions\": {\"stream\": false, \"temperature\": 0.1, \"maxTokens\": \"1000\"},\n" +
                    "  \"messages\": [{\"role\": \"system\",\"text\": \"" + st1 + "\"}, {\"role\": \"user\",\"text\": \"" + st2 + "\"}]}";

            try {
                URL url = new URL("https://llm.api.cloud.yandex.net/foundationModels/v1/completionAsync");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.addRequestProperty("Content-Type", "application/json");
                conn.addRequestProperty("Authorization", "Api-Key " + apiKey);
                conn.addRequestProperty("x-folder-id", folderId);
                conn.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(jsonObject.toString());
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonObject1 = new JSONObject(response.toString());
                // Выводим ответ от сервера
//            JSONObject j12 = jsonObject1.getJSONObject("result");
//            JSONArray a12 = j12.optJSONArray("alternatives");
//            JSONObject j13 = a12.optJSONObject(0);
//            JSONObject j14 = j13.optJSONObject("message");
                String id = jsonObject1.getString("id");
                System.out.println(id);

                boolean dd = true;
                StringBuffer response1 = new StringBuffer();
                while (dd) {
                    System.out.println("sleep");
//                    Thread.sleep(1);
                    URL urlID = new URL("https://operation.api.cloud.yandex.net/operations/" + id);
                    HttpURLConnection conn1 = (HttpURLConnection) urlID.openConnection();
                    conn1.setRequestMethod("GET");
                    conn1.addRequestProperty("Authorization", "Api-Key " + apiKey);
                    conn1.setDoOutput(true);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
                    String line1;
                    response1 = new StringBuffer();

                    while ((line1 = in.readLine()) != null) {
                        response1.append(line1);
                    }
                    in.close();
                    System.out.println("----" + response1.substring(2, 14));
                    if (response1.substring(2, 14).equals("\"done\": fals")) {
                        Thread.sleep(5000);
                    } else if (response1.substring(2, 14).equals("\"done\": true")) {
                        dd = false;
                    }

                }

                JSONObject jsonObject2 = new JSONObject(response1.toString());

                System.out.println();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
