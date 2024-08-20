package com.codealong.kotlinandjunittutorial.service

import com.codealong.kotlinandjunittutorial.datasource.BankDataSource
import com.codealong.kotlinandjunittutorial.model.Bank
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()

    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)
    fun update(bank: Bank): Bank = dataSource.updateBank(bank)
    fun deleteBank(accountNumber: String): Unit = dataSource.deleteBank(accountNumber)
}