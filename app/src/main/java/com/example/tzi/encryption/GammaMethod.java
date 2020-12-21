package com.example.tzi.encryption;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class GammaMethod {
    static char [] alphabet = new char[]{ 'а', 'б', 'в', 'г', 'ґ','д', 'е', 'є', 'ж', 'з', 'и',
            'і', 'ї', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х',
            'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Ґ','Д', 'Е', 'Є', 'Ж', 'З', 'И',
            'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х',
            'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я', 'a','b','c','d','e','f','g','h','i'
            ,'j','k','l','m','n','o','p','q', 'r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
            'R','S','T','U','V','W','X','Y','Z',
            ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '\n'};

    private static int[] GetNumberByLetter(String message){
        int[] numbers = new int[message.length()];

        for (int i = 0; i < message.length(); i++){
            for (int j = 0; j < alphabet.length; j++){
                if (message.charAt(i) == alphabet[j]) numbers[i] = j;
            }
        }
        return numbers;
    }

    private static int[] GetGammaMessageSum(String message, String gamma){
        int[] messageNumbers = GetNumberByLetter(message);
        int[] gammaNumbers = GetNumberByLetter(gamma);
        int[] gammaMessageSum = new int[message.length()];

        for (int i = 0, j = 0; i < message.length(); i++, j++){
            if (j != gammaNumbers.length - 1) gammaMessageSum[i] = messageNumbers[i] +
                    gammaNumbers[j];
            else {
                gammaMessageSum[i] = messageNumbers[i] + gammaNumbers[j];
                j = 0;
            }
        }
        return gammaMessageSum;
    }

    private static int[] GetGammaMessageDifference(String message, String gamma){
        int[] messageNumbers = GetNumberByLetter(message);
        int[] gammaNumbers = GetNumberByLetter(gamma);
        int[] gammaMessageDifference = new int[message.length()];

        for (int i = 0, j = 0; i < message.length(); i++, j++){
            if (j != gammaNumbers.length - 1) gammaMessageDifference[i] = messageNumbers[i] -
                    gammaNumbers[j];
            else {
                gammaMessageDifference[i] = messageNumbers[i] - gammaNumbers[j];
                j = 0;
            }
            gammaMessageDifference[i] = gammaMessageDifference[i] + alphabet.length;
        }
        return gammaMessageDifference;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static int[] GetModuleSum(int[] sum){
        for (int i = 0; i < sum.length; i++){
            sum[i] = Math.floorMod(sum[i], alphabet.length);
        }
        return sum;
    }

    private static String GetLetterByNumber(int[] moduleSum){
        String encriptedMessage = "";

        for (int i = 0; i < moduleSum.length; i++){
            for (int j = 0; j < alphabet.length; j++){
                if (moduleSum[i] == j) encriptedMessage += alphabet[j];
            }
        }
        return encriptedMessage;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String Encript(String message, String gamma){
        return GetLetterByNumber(GetModuleSum(GetGammaMessageSum(message,gamma)));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String Decript(String message, String gamma){
        return GetLetterByNumber(GetModuleSum(GetGammaMessageDifference(message,gamma)));
    }
}
