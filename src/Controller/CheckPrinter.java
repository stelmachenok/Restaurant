package Controller;

import Model.Dish;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by y50-70 on 27.10.2017.
 */
public class CheckPrinter {
    private static final String FILENAME = "check.txt";

    public static void printCheck(String tableName, List<Dish> components) {
        File file = new File(FILENAME);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                String checkText = "";
                checkText += "======================\n";
                checkText += tableName + "\n";
                checkText += "======================\n";
                checkText += "========Заказ=========\n";
                int sum = 0;
                for (Dish dish : components) {
                    checkText += dish.getDishName() + " " + dish.getPrice() + "\n";
                    sum += dish.getPrice();
                }
                checkText += "========Итого=========\n";
                checkText += sum + "\n";
                out.print(checkText);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
