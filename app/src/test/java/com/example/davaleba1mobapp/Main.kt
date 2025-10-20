package com.example.davaleba1mobapp

open class Account(
    val accountNumber: String,
    var ownerName: String
) {
    private var balance: Double = 0.0

    fun getBalance(): Double {
        return balance
    }


    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("$amount lari daemata angarishs $accountNumber. axali balansi: $balance")
        } else {
            println("arasowori tanxa depozitistvis")
        }
    }


    open fun withdraw(amount: Double) {
        if (amount > 0 && balance >= amount) {
            balance -= amount
            println("$amount lari gamotanilia angarishidan $accountNumber. axali balansi: $balance")
        } else {
            println("operacia ver shesrulda: ar aris sakmarisi balansi an tanxa arasworia")
        }
    }

    fun printInfo() {
        println("angarishis nomeri: $accountNumber")
        println("mflobeli: $ownerName")
        println("balansi: $balance")
    }
}




class SavingsAccount(accountNumber: String, ownerName: String) : Account(accountNumber, ownerName) {
    override fun withdraw(amount: Double) {
        if (amount > 500) {
            println("gamotanis limiti 500 laria. operacia ver shesrulda")
        } else {
            super.withdraw(amount)
        }
    }
}


class VIPAccount(accountNumber: String, ownerName: String, private val transactionFee: Double = 2.0) :
    Account(accountNumber, ownerName) {
    override fun withdraw(amount: Double) {
        val totalAmount = amount + transactionFee
        if (getBalance() >= totalAmount) {
            super.withdraw(totalAmount)
        } else {
            println("operacia ver shesrulda: balansi sakmarisi ar aris VIP sakomisios chatvlit")
        }
    }
}


fun main() {
    val acc1 = SavingsAccount("N101", "saba A.")
    val acc2 = VIPAccount("VIP202", "luka A.")

    acc1.deposit(1000.0)
    acc1.withdraw(300.0)
    acc1.withdraw(600.0)

    acc2.deposit(1000.0)
    acc2.withdraw(50.0)
    acc2.printInfo()


    val accounts: List<Account> = listOf(acc1, acc2)
    for (account in accounts) {
        account.deposit(50.0)
        account.printInfo()
    }
}