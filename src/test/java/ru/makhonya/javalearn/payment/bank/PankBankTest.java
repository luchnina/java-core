package ru.makhonya.javalearn.payment.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.makhonya.javalearn.payment.transaction.Card;
import ru.makhonya.javalearn.payment.transaction.Money;
import ru.makhonya.javalearn.payment.transaction.Transaction;
import ru.makhonya.javalearn.payment.transaction.TransactionStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PankBankTest {

  private static final String TERMINAL_ID = "TERM123";
  private static final String CARD_MASKED_NUMBER = "**** 1234";

  private Card testCard;
  private Money initialBalance;

  @BeforeEach
  void setUp() {
    testCard = new Card(CARD_MASKED_NUMBER);
    initialBalance = new Money(200);
  }

  @Test
  void shouldCreateBankWithCardAndInitialBalance_directConstructor() {
    PankBank bank = new PankBank(testCard, initialBalance);

    assertNotNull(bank);
    assertEquals(initialBalance, bank.getRawBalance());
  }

  @Test
  void shouldCreateEmptyBankAndAddCardLater() {
    PankBank bank = new PankBank();
    bank.addCard(testCard, initialBalance);

    assertEquals(initialBalance, bank.getRawBalance());
  }

  @Test
  void shouldReturnZeroBalance_forEmptyBank() {
    PankBank bank = new PankBank();

    assertEquals(Money.ZERO, bank.getRawBalance());
  }

  @Test
  void shouldOpenTransaction_withCorrectIdAndStatus() {
    PankBank bank = new PankBank(testCard, initialBalance);
    Transaction tx = bank.openTransaction(TERMINAL_ID, testCard);

    assertNotNull(tx);
    assertEquals(TransactionStatus.OPEN, tx.status());
  }

  @Test
  void freezeCommit_shouldDeductFromBalance() {
    PankBank bank = new PankBank(testCard, initialBalance);
    String txId = bank.openTransaction(TERMINAL_ID, testCard).id();
    Money freezeAmount = new Money(50);

    bank.freeze(txId, freezeAmount);
    bank.commit(txId);

    assertEquals(initialBalance.minus(freezeAmount), bank.getRawBalance());
  }

  @Test
  void getBalance_shouldDeductAllOpenFreezes() {
    PankBank bank = new PankBank(testCard, initialBalance);
    String tx1Id = bank.openTransaction(TERMINAL_ID, testCard).id();
    String tx2Id = bank.openTransaction(TERMINAL_ID, testCard).id();

    Money freezeAmount = new Money(20);
    bank.freeze(tx1Id, freezeAmount);

    Money freezeAmount2 = new Money(115);
    bank.freeze(tx2Id, freezeAmount2);

    assertEquals(initialBalance.minus(freezeAmount).minus(freezeAmount2), bank.getBalance(tx1Id));
  }
}