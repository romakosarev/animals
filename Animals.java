import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Animals {

    public class FileEditor {
        public static void updateSection(int lineNumber, String newSection) {
            // Имя файла для чтения и записи
            String fileName = "animals.txt";
            // Создание временного массива для хранения строк
            ArrayList<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                // Чтение всех строк из файла
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Проверка корректности номера строки
            if (lineNumber >= 0 && lineNumber < lines.size()) {
                // Разделение строки на части с использованием символа "|"
                String[] parts = lines.get(lineNumber).split("\\|");
                if (parts.length > 1) {
                    parts[parts.length - 1] = newSection; // Изменение последней секции
                    lines.set(lineNumber, String.join("|", parts)); // Обновление строки

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        // Запись измененных строк обратно в файл
                        for (String updatedLine : lines) {
                            writer.write(updatedLine + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class Counter implements AutoCloseable {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @Override
        public void close() throws IOException {
            if (count > 0) {
                //throw new IOException("Ошибка, файл не был корректно закрыт");
            }
        }
    }

    public static void readFile(int nomer) {
        try (BufferedReader br = new BufferedReader(new FileReader("animals.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                ++i;
                if(nomer > 0 && nomer != i) continue;
                String[] elements = line.split("\\|");
                if(nomer > 0 && nomer == i) {
                    System.out.println("Животое № " + i + " понимает команды: " + elements[3]);
                    continue;
                }
                System.out.println("№ " + i + " Животое: " + elements[0] + " Имя: " + elements[1] + " Дата рождения: " + elements[2] + " Команды: " + elements[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void add(String animalType) {
        Scanner scanner = new Scanner(System.in);
        
        try (Counter counter = new Counter();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("animals.txt", true), "UTF-8"))) {

            System.out.println("Введите имя:");
            String animalName = scanner.next();
            System.out.println("Введите дату рождения:");
            String animalDob = scanner.next();
            System.out.println("Введите команду:");
            String animalTeam = scanner.next();

            // Запись данных в файл в указанном формате с учетом кодировки UTF-8
            writer.write(animalType + "|" + animalName + "|" + animalDob + "|" + animalTeam);
            writer.newLine();

            counter.increment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("Выберите действие:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Список всех животных");
            System.out.println("3. Умения животного");
            System.out.println("4. Обучить");
            System.out.println("5. Выйти");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                System.out.println("Выберите класс животного:");
                System.out.println("1. Домашнее");
                System.out.println("2. Вьючное");
                int subChoice = scanner.nextInt();
                switch (subChoice) {
                    case 1:
                        System.out.println("Вы выбрали Домашнее, выберите животное:");
                        System.out.println("1. Собака");
                        System.out.println("2. Кошка");
                        System.out.println("3. Хомяк");
                        int subSubChoice = scanner.nextInt();
                        switch (subSubChoice) {
                            case 1:
                                add("Собака");
                                break;
                            case 2:
                                add("Кошка");
                                break;
                            case 3:
                                add("Хомяк");
                                break;
                            default:
                                System.out.println("Ошибка");
                        }
                        break;
                    case 2:
                        System.out.println("Вы выбрали Вьючное, выберите животное:");
                        System.out.println("1. Лошадь");
                        System.out.println("2. Верблюд");
                        System.out.println("3. Осел");
                        int subSubChoice1 = scanner.nextInt();
                        switch (subSubChoice1) {
                            case 1:
                                add("Лошадь");
                                break;
                            case 2:
                                add("Верблюд");
                                break;
                            case 3:
                                add("Осел");
                                break;
                            default:
                                System.out.println("Ошибка");
                        }
                        break;
                    default:
                        System.out.println("Ошибка");
                }
                break;
                case 2:
                    readFile(0);
                    break;
                case 3:
                    System.out.println("Выберите номер животного:");
                    String animalNomer = scanner.next();
                    readFile(Integer.parseInt(animalNomer));
                    break;
                case 4:
                    System.out.println("Выберите номер животного:");
                    String animalNmSkill = scanner.next();
                    scanner.nextLine();
                    System.out.println("Выберите новые команды животного:");
                    String animalSkills = scanner.nextLine();
                    FileEditor.updateSection(Integer.parseInt(animalNmSkill) - 1, animalSkills);
                    break;
                case 5:
                    System.out.println("Программа завершена");
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова");
            }
        }
    }
}