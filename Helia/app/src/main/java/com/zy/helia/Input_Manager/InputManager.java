package com.zy.helia.Input_Manager;


public class InputManager {
    public static boolean ValidatePasswordInput(String str){
        return str.matches("\\S");
    }

    public static boolean ValidateUserNameInput(String str){
        return str.matches("[a-zA-Z0-9]*");
    }

    public static boolean ValidateEmailInput(String str){
        return str.matches("\"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$\"");
    }


}
