package com.epsi.music.service.impl;

import com.epsi.music.mapper.LoanMapper;
import com.epsi.music.service.LoanService;

public class JdbcLoanServiceImpl implements LoanService {

    private LoanMapper loanMapper;

    public JdbcLoanServiceImpl(LoanMapper loanMapper){
        super();
        this.loanMapper = loanMapper;
    }

    @Override
    public void addLoan(String id, String username){ this.loanMapper.addLoan(id,username);}

    @Override
    public void deleteLoan(String id, String username){this.loanMapper.deleteLoan(id,username);}

}
