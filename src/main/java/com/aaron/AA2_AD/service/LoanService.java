package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Loan;

import java.util.Optional;
import java.util.Set;

public interface LoanService {

    Set<Loan> findAll();
    Optional<Loan> findById(long id);
    Loan addLoan(Loan loan);
    Loan modifyLoan(long id, Loan newLoan);
    void deleteLoan(long id);
}
