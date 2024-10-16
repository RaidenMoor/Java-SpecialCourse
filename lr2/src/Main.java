import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Vectors v= CreateVector();
        while (true)
        {
            System.out.println("\nВаш вектор: " + v.ToString());
            System.out.println("\nВыберете пункт меню: \n0 - Выход\n1 - Значение под заданным индексом" +
                    "\n2 - Поменять значение под заданным индексом\n3 - Размерность вектора" +
                    "\n4 - Минимальное и максимальное значение вектора\n5 - Сортировка координат по возрастанию" +
                    "\n6 - Евклидова норма\n7 - Умножение вектора на число\n8 - Сложение двух векторов" +
                    "\n9 - Скалярное произведение двух векторов");
            System.out.println("Ваш выбор:");
            String answer_2 = in.next();
            switch (answer_2)
            {
                case "0":
                    return;
                case "1":
                    System.out.println("Введите индекс:");
                    String id = in.next();
                    System.out.println("Элемент под введенным идексом: " + v.GetEl(id));
                    in.nextLine();
                    System.out.println("Нажмите Enter, чтобы продолжить");
                    in.nextLine();
                    break;
                case "2":
                    System.out.println("Введите индекс:");
                    String id_2 = in.next();
                    System.out.println("Введите новое значение:");
                    Double new_val = in.nextDouble();
                    v.ChangeEl(id_2,new_val);
                    in.nextLine();
                    System.out.println("Нажмите Enter, чтобы продолжить");
                    in.nextLine();
                    break;
                case "3":
                    System.out.println("Размерность вектора: " + v.Length());
                    in.nextLine();
                    in.nextLine();
                    break;
                case "4":
                    System.out.println("Минимальное значение вектора: " + v.FindMin());
                    System.out.println("Максимальное значение вектора: " + v.FindMax());
                    in.nextLine();
                    System.out.println("Нажмите Enter, чтобы продолжить");
                    in.nextLine();
                    break;
                case "5":
                    v.Sort();
                    System.out.println("Отсортированный вектор: " + v.ToString());
                    in.nextLine();
                    in.nextLine();
                    break;
                case "6":
                    System.out.println("Евклидова норма вектора:" + v.Evclid());
                    in.nextLine();
                    System.out.println("Нажмите Enter, чтобы продолжить");
                    in.nextLine();
                    break;
                case "7":
                    System.out.println("Введите число, на которое хотите умножить вектор: ");
                    double ch = in.nextDouble();
                    v.ValMul(ch);
                    in.nextLine();
                    System.out.println("Нажмите Enter, чтобы продолжить");
                    in.nextLine();
                    break;
                case "8":
                    System.out.println("Создайте второй вектор: ");
                    Vectors v_2 = CreateVector();
                    if(v_2.Length() == v.Length())
                    {
                        Vectors v_sum = Vectors.VectorSum(v,v_2);
                        System.out.println("Сумма векторов: " + v_sum.ToString());
                    }
                    else
                    {
                        System.out.println("Размерности векторов не равны. Сложение невозможно");
                    }
                    in.nextLine();
                    System.out.println("Нажмите Enter, чтобы продолжить");
                    in.nextLine();
                    break;
                case "9":
                    System.out.println("Создайте второй вектор: ");
                    Vectors v_3 = CreateVector();
                    if(v_3.Length() == v.Length())
                    {
                        double scalar = Vectors.Scalar(v,v_3);
                        System.out.println("Скалярное произведение векторов: " + scalar);
                    }
                    else
                    {
                        System.out.println("Размерности векторов не равны. Произведение невозможно");
                    }
                    in.nextLine();
                    System.out.println("Нажмите Enter, чтобы продолжить");
                    in.nextLine();
                    break;
                default:
                    System.out.println("Такого пункта не существует!");
                    in.nextLine();
                    System.out.println("Нажмите Enter, чтобы продолжить");
                    in.nextLine();
                    break;
            }
        }

    }
    public static Vectors CreateVector()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размерность вектора:");
        int n = in.nextInt();
        Vectors v = new Vectors(n);
        boolean flag = true;
        while(flag) {
            System.out.println("Хотите ли Вы ввести координаты?\n1-Да\n2-Нет\nВаш ответ:");
            //Scanner in = new Scanner(System.in);
            String answer = in.next();
            if (answer.compareTo("1") == 0) {
                System.out.println("Введите координаты через пробел:");
                String coordinates;
                in.nextLine();
                coordinates = in.nextLine();
                if (coordinates.isEmpty()) {
                    System.out.println("Строка пуста.");
                }

                else {
                    try {
                        String[] v_user = coordinates.split(" ");
                        v.AddCoordinates(v_user);
                        flag = false;
                    }
                    catch (Exception ex)
                    {
                        System.out.println("Вы некорректно ввели координаты");
                    }


                }
            } else if (answer.compareTo("2") == 0) {
                v.AddRandCoordinates();
                System.out.println("\nВектор: " + v.ToString());
                flag = false;
            } else {
                System.out.println("Такого варианта нет! Выберете один из предложенных вариантов.");
            }
        }
        return v;
    }
}
