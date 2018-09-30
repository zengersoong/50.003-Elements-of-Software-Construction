import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/*
*****************Homeworkquestion1*****************
1) Must contain 3 methods, Withdraw, deposit, checkBalance
2)Each method should print a status message to the screen on completion.
3)The method for withdraw should return false and do nothing if there are insufficient funds. 
4)SynchronizedAccount should be synchronized differently for read and write accesses
*****************Fin*****************
*/
public class SynchronizedAccount {

    private volatile double CurrentBalance;
    private Object WriteLock;
    private Object ViewLock;

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public SynchronizedAccount(double amt) {
        //Each account has his/her own lock for withdrawing/deposit
        this.WriteLock = new Object();
        // //Each account has his/her own lock for Viewing, When withdrawing/depositing this lock will prevent checkBalance;
        // this.ViewLock = new Object();

        this.CurrentBalance = amt;
    }

    private double getBalance() {

        return this.CurrentBalance;

    }

    private void checkBalance() {
        /**check balance of account, not synchronized method*/
        System.out.println("<" + this + "> contains : $" + this.CurrentBalance);

    }

    private boolean withdraw(double amt) {
        boolean response = false;

        synchronized (this.WriteLock) {

            if (this.getBalance() < amt) {
                System.out.println("<<Failure-Withdraw>>");
                System.out.println(
                        "<" + this + ">" + " has Insufficient funds... Withdrawal amount exceeds current balance!");

            } else {
                this.CurrentBalance -= amt;
                response = true;
                System.out.println("<<Success-Withdraw>>");
                System.out.println("<" + this + ">" + " have withdrawn $" + amt + " from account.");
            }
            return response;
        }
    }

    private void deposit(double amt) {
        synchronized (this.WriteLock) {
            this.CurrentBalance += amt;
            System.out.println("<<Success-Deposit>>");
            System.out.println("<" + this + ">" + " have deposited $" + amt + " to account");
        }
    }

    static class AccountMachine extends Thread {
        SynchronizedAccount sa;

        public AccountMachine() {
            Random randomGenerator = new Random();
            sa = new SynchronizedAccount(round((randomGenerator.nextInt(10000) + randomGenerator.nextDouble()), 2));

        }

        public void run() {
            Random randomGenerator = new Random();
            try {
                while (true) {
                    sa.deposit(round((randomGenerator.nextInt(10000) + randomGenerator.nextDouble()), 2));
                    sa.withdraw(round((randomGenerator.nextInt(10000) + randomGenerator.nextDouble()), 2));
                    sa.checkBalance();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Accounts creation interrupted, bankers probably having lunchbreak...");
            }

        }
    }

    public static void main(String[] args) throws Exception {
        int x = 5;
        AccountMachine[] am = new AccountMachine[x];
        for (int n = 0; n < x; n++) {
            am[n] = new AccountMachine();
        }
        for (int i = 0; i < x; i++) {
            am[i].start();
        }
    }
}