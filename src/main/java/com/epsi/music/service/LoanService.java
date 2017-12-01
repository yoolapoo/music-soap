package com.epsi.music.service;

import org.springframework.stereotype.Service;

@Service
public interface LoanService {

    void addLoan(String id, String username);
    void deleteLoan(String id, String username);


}

