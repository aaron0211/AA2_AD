package com.aaron.AA2_AD.repository;

import com.aaron.AA2_AD.domain.Loan;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    Set<Loan> findAll();
    Set<Loan> findByLoanDateAfter(LocalDateTime loanDate);
}
