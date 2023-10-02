import java.util.*;

class Laptop {
    private String name;
    private String manufacturer;
    private Integer RAM;
    private Integer ROM;
    private Integer cost;

    public Laptop (String name, String manufacturer, Integer RAM, Integer ROM, Integer cost) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.RAM = RAM;
        this.ROM = ROM;
        this.cost = cost;
    }

    public String toString() {
        return "Модель: " + name + "; Производитель: " + manufacturer + "; ОЗУ: " + RAM + "; ПЗУ: " + ROM + "; Цена: " + cost + ".";
    }

    public Boolean SatisfiesFilter (Map<Integer, Integer> filterMap) {
        if (RAM >= filterMap.get(1) && ROM >= filterMap.get(2) && cost >= filterMap.get(3)) {
            return true;
        } else {
            return false;
        }
    }
}

class LaptopsSet {
    private Set<Laptop> laptops = new HashSet<>();

    public void initFilling() {
        laptops.add(new Laptop("MSI Modern", "MSI", 8192, 262144, 42000));
        laptops.add(new Laptop("HUAWEI MateBook D15", "HUAWEI", 16384, 524288, 65000));
        laptops.add(new Laptop("Apple MacBook Air", "Apple", 8192, 262144, 98400));
        laptops.add(new Laptop("ASUS ROG Strix SCAR 18", "ASUS", 32768, 1024000, 305000));
    }

    public void printAll() {
        for (Laptop item : laptops) {
            System.out.println(item);
        }
        System.out.println();
    }

    public void filterSet() {
        boolean flag = true;
        Map<Integer, Integer> filterMap = initFilterMap();

        Scanner in = new Scanner(System.in);

        while (flag) {
            printAllMap(filterMap);
            System.out.print("Введите цифру, которая соответствует характеристике, чтобы задать фильтр. Либо '0', чтобы выполнить отбор: ");
            int numKey = in.nextInt();
            if (numKey < 0 || numKey > 3) {
                System.out.println("Введено неверное значение!");
                continue;
            }
            if (numKey == 0) {
                flag = false;
                continue;
            }

            System.out.print("Введите число для фильтра: ");
            int numValue = in.nextInt();

            filterMap.put(numKey, numValue);
        }

        System.out.println("\nВот что мы нашли по вашим фильтрам:");
        for (Laptop item : laptops) {
            if (item.SatisfiesFilter(filterMap)) {
                System.out.println(item);
            }
        }
    }
    private Map<Integer, Integer> initFilterMap() {
        Map<Integer, Integer> filterMap = new HashMap<>();

        for (int i=1; i <= 3; i++) {
            filterMap.put(i, 0);
        }
        return filterMap;
    }

    private void printAllMap(Map<Integer, Integer> filterMap) {
        System.out.println("1. ОЗУ: " + filterMap.get(1) + " МБ;");
        System.out.println("2. ПЗУ: " + filterMap.get(2) + " МБ;");
        System.out.println("3. Цена: " + filterMap.get(3) + " руб.");
    }
}

public class Seminar_6 {
    public static void main(String[] args) {
        LaptopsSet laptops = new LaptopsSet(); //множество ноутбуков

        laptops.initFilling(); //первоначальное заполнение
        System.out.println("Полный список ноутбуков: ");
        laptops.printAll(); //выводим все из множества

        System.out.println("Задайте фильтр по следующим характеристикам: ");
        laptops.filterSet(); //запрашиваем ввод фильтров и после выводим отфильтрованное множество
    }
}