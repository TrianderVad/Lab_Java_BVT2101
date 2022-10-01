
// java Palindrome madam racecar apple kayak song noon
public class Palindrome {
    /*
    Логика функции main
    Идет перебор строк заданных в консоле (аргументов)
    Переменной s присваивается значение слова
    Потом слово проверяется на палиндром и выводится true (если оно является) или false (если оно не является)
     */
    public static void main(String[] args){

        for (int i= 0; i < args.length; i++){
            String s = args[i];
            System.out.print(isPalindrome(s) + " ");
        }
    }
    /*
    Логика метода reverseString
    На вход подается строка orig_string
    Создается пустая строка reverse_string
    Цикл for перебирает эту строку с конца, и буквы заносятся в переменную reverse_string
    Пример: o_string = "Привет", r_string = "тевирП"
    Возращается перевернутая строка
     */
    public static String reverseString(String orig_string){
        String reverse_string = "";
        for (int i = orig_string.length()-1;i > -1; i--){

            reverse_string += orig_string.charAt(i);
        }
        return reverse_string;
    }
    /*
    Логика метода isPalindrome
    На вход подается строка s
    Создается переменная orig_string, она равна s
    Создается переменная reverse_string, она равняется return метода reverseString
    Они сравниваются, и возращается true, если равны и false, если нет
     */
    public static boolean isPalindrome(String s){
        String orig_string = s;
        String reverse_string = reverseString(orig_string);
        return orig_string.equals(reverse_string);
    }
}
