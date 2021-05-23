package com.test.flip.Disbursement.repository;

import com.test.flip.Disbursement.entity.Disbursement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface DisbursementRepository extends JpaRepository<Disbursement, Long> {
    Disbursement findByTransactionId(BigInteger transactionId);
}
