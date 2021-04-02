package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Loan;
import com.aaron.AA2_AD.exception.LoanNotFoundException;
import com.aaron.AA2_AD.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Set<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public Optional<Loan> findById(long id) {
        return loanRepository.findById(id);
    }

    @Override
    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan modifyLoan(long id, Loan newLoan) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(()->new LoanNotFoundException(id));
        newLoan.setId(loan.getId());
        return loanRepository.save(newLoan);
    }

    @Override
    public void deleteLoan(long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(()-> new LoanNotFoundException(id));
        loanRepository.deleteById(id);
    }
}
