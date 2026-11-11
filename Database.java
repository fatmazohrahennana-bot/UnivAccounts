package core;

import java.util.ArrayList;
import java.util.List;

public class Database {
    // 1. النسخة الوحيدة
    private static Database instance;

    private List<StudentAccount> accounts;
    private List<String> auditLog;

    // 2. مُنشئ خاص لمنع الإنشاء من الخارج
    private Database() {
        accounts = new ArrayList<>();
        auditLog = new ArrayList<>();
    }

    // 3. دالة الوصول العامة (Thread-safe)
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // دوال المستودع
    public void addAccount(StudentAccount account) {
        accounts.add(account);
        logOperation("Account Created: " + account.getId() + " - " + account.getFullName());
    }

    public List<StudentAccount> getAllAccounts() {
        return accounts;
    }

    // دوال سجل التدقيق
    public void logOperation(String operation) {
        auditLog.add(operation);
        System.out.println("[AUDIT LOG] " + operation);
    }

    public void printAuditLog() {
        System.out.println("\n========== Audit Log ==========");
        for (int i = 0; i < auditLog.size(); i++) {
            System.out.println((i + 1) + ". " + auditLog.get(i));
        }
        System.out.println("================================");
    }
}
