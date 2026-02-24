import dao.UserDAO;
import model.User;
import util.HibernateUtil;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Меню: 1.Создать 2.Список 3.Найти 4.Обновить 5.Удалить 0.Выход ---");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1 -> {
                    System.out.print("Имя: "); String name = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Возраст: "); int age = sc.nextInt();
                    dao.save(new User(name, email, age));
                }
                case 2 -> dao.findAll().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID: ");
                    System.out.println(dao.findById(sc.nextLong()));
                }
                case 4 -> {
                    System.out.print("ID пользователя для обновления: ");
                    User u = dao.findById(sc.nextLong());
                    if (u != null) {
                        sc.nextLine();
                        System.out.print("Новое имя: "); u.setName(sc.nextLine());
                        dao.update(u);
                    }
                }
                case 5 -> {
                    System.out.print("ID для удаления: ");
                    dao.delete(sc.nextLong());
                }
            }
        }
        HibernateUtil.shutdown();
    }
}