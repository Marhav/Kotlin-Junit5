package com.codealong.kotlinandjunittutorial.datasource.mock

import com.codealong.kotlinandjunittutorial.datasource.BankDataSource
import com.codealong.kotlinandjunittutorial.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
            Bank("1234", 3.0, 17),
            Bank("5678", 7.0, 0),
            Bank("9101", 0.0, 23),
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String) = banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number: $accountNumber")

    override fun createBank(bank: Bank): Bank {
        if(banks.any{ it.accountNumber == bank.accountNumber}) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists.")
        }
        banks.add(bank)

        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
                ?: throw NoSuchElementException("Could not find a bank with account number: ${bank.accountNumber}")

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }

    override fun deleteBank(accountNumber: String): Unit {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
                ?: throw NoSuchElementException("Could not find a bank with account number: $accountNumber")

        banks.remove(currentBank)
    }
}