package com.aaron.AA2_AD.service;

import com.aaron.AA2_AD.domain.Book;
import com.aaron.AA2_AD.domain.Loan;
import com.aaron.AA2_AD.domain.Member;
import com.aaron.AA2_AD.domain.dto.LoanDTO;
import com.aaron.AA2_AD.exception.BookNotFoundException;
import com.aaron.AA2_AD.exception.LoanNotFoundException;
import com.aaron.AA2_AD.exception.MemberNotFoundException;
import com.aaron.AA2_AD.repository.BookRepository;
import com.aaron.AA2_AD.repository.LoanRepository;
import com.aaron.AA2_AD.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired

    private MemberRepository memberRepository;

    @Override
    public Set<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public Optional<Loan> findById(long id) {
        return loanRepository.findById(id);
    }

    @Override
    public Loan addLoan(LoanDTO loanDTO) {
        Book book = bookRepository.findByTitle(loanDTO.getBookTitle())
                .orElseThrow(()-> new BookNotFoundException("Book not found"));
        Member member = memberRepository.findByName(loanDTO.getMemberName())
                .orElseThrow(()-> new MemberNotFoundException("Member not found"));
        Loan loan = new Loan();
        loan.setLoanDate(LocalDateTime.now());
        loan.setReturnDate(LocalDateTime.now().plusDays(15));
        loan.setBook(book);
        loan.setMember(member);

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

    @Override
    public Set<Loan> findByLoanDateAfter(LocalDateTime loanDate) {
        return loanRepository.findByLoanDateAfter(loanDate);
    }
}
