package com.user.Assistance;

public interface InterfaceLogin {
    void validateUser(String email, String senha);
    interface InsertUser{
        void registerUser(String email, String senha, String confSenha);
    }
}
