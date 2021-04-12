package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Loan;
import com.aaron.AA2_AD.domain.dto.LoanDTO;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface LoanService {

    Set<Loan> findAll();
    Optional<Loan> findById(long id);
    Loan addLoan(LoanDTO loanDTO);
    Loan modifyLoan(long id, Loan newLoan);
    void deleteLoan(long id);
    Set<Loan> findByLoanDateAfter(LocalDateTime loanDate);
}
