public class Primes {
    public static void main (String[] args){
        for(int i = 2; i < 101; i++){ //Перебираем значения от 2 до 100
            if(isPrime(i)){  // Если isPrime равно true (то есть число простое), то выводится i
                System.out.print(i + " ");
            }
        }
    }
    /*
    Логика метода isPrime
    Задается переменная checker изначально равная true (Т.е. число простое)
    Перебираются числа от 2, до заданного числа
    Если заданное число делится на одно из подобранных чисел, то переменной checker задается значение false
    И цикл прекращается
    Возращается значение переменной checker

     */
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
