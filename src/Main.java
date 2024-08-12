// Определение пользовательских исключений
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {
    private final int row;
    private final int col;

    public MyArrayDataException(int row, int col, String message) {
        super(message);
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Ошибка в ячейке [" + row + "][" + col + "]: " + getMessage();
    }
}

// Основной класс для выполнения задания
public class Main {

    // Метод для проверки массива и подсчета суммы
    public static int calculateSum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Массив должен быть размером 4х4");
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, "Невозможно преобразовать в число: " + array[i][j]);
                }
            }
        }
        return sum;
    }

    // Главный метод для выполнения программы
    public static void main(String[] args) {
        // Корректный массив 4х4
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Массив с неправильным размером
        String[][] wrongSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8"}
        };

        // Массив с некорректными данными
        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "seven", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            System.out.println("Сумма элементов корректного массива: " + calculateSum(correctArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива с неправильным размером: " + calculateSum(wrongSizeArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов массива с некорректными данными: " + calculateSum(wrongDataArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }
}