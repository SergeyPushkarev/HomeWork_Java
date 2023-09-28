import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ExternalProcessing {
    public static void printTelephoneBook(HashMap<String, ArrayList<String>> telephoneBook) {
        //здесь выводим в консоль всю телефонную книгу в порядкеы убывания по количеству номеров
        HashMap<String, ArrayList<String>> tempTelephoneBook = (HashMap<String, ArrayList<String>>) telephoneBook.clone();

        int maxCount = 0;
        String key = "";
        while (!tempTelephoneBook.isEmpty()) {
            for (Map.Entry entry : tempTelephoneBook.entrySet()) { //обходим все элементы и определяем наибольший по размеру списка номеров по ключу
                ArrayList<String> currentVal = (ArrayList<String>) entry.getValue();
                if (maxCount < currentVal.toArray().length) {
                    maxCount = currentVal.toArray().length;
                    key = (String) entry.getKey();
                }
            }
            System.out.println(key + ": " + tempTelephoneBook.get(key)); //выводим найденный контакт с наибольшим количеством номеров
            tempTelephoneBook.remove(key); //удаляем этот контакт, после чего обходим книгу заново, пока контакты не закончатся
            maxCount = 0;
        }
    }

    public static HashMap<String, ArrayList<String>> add(HashMap<String, ArrayList<String>> telephoneBook, String key, String value) {
        //здесь добавляем новые номера с учетом повторений имен
        if (!telephoneBook.containsKey(key))
        {
            telephoneBook.put(key, new ArrayList<String>());
        }
        telephoneBook.get(key).add(value);
        return telephoneBook;
    }

    public static HashMap<String, ArrayList<String>> initFilling(HashMap<String, ArrayList<String>> telephoneBook) {
        //здесь можно реализовать заполнение из внешних источников, но пока что добавляются контакты статично
        add(telephoneBook, "Русакова Эмилия Денисовна", "8-999-999-99-91");
        add(telephoneBook, "Русакова Эмилия Денисовна", "8-999-999-99-92");
        add(telephoneBook, "Бородин Артём Тихонович", "8-999-999-99-96");
        add(telephoneBook, "Егорова Маргарита Максимовна", "8-999-999-99-93");
        add(telephoneBook, "Егорова Маргарита Максимовна", "8-999-999-99-94");
        add(telephoneBook, "Егорова Маргарита Максимовна", "8-999-999-99-95");
        return telephoneBook;
    }
}

class TelephoneBook {
    public static void main(String[] args) {

        HashMap<String, ArrayList<String>> telephoneBook = new HashMap<>(); //телефонный справочник

        ExternalProcessing.initFilling(telephoneBook); //вызываем заполнение существующих контактов

        ExternalProcessing.add(telephoneBook, "Бородин Артём Тихонович", "8-999-999-99-96"); //дополнительно добавляем два номера
        ExternalProcessing.add(telephoneBook, "Русакова Эмилия Денисовна", "8-999-999-99-97");

        ExternalProcessing.printTelephoneBook(telephoneBook); //выводим контакты в порядке убывания по количеству номеров
    }
}
