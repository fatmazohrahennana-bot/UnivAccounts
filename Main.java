package core;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== UniAccounts: TP GL N:01 (Core) ===\n");

        // 1. Builder: بناء حساب طالب معقد
        StudentAccount student1 = new StudentAccount.StudentAccountBuilder(
                "ID-2024003", "Zahra", "Zahra@univ.dz"
        )
        .setPhone("0755123480")
        .setLevel("L3")
        .setMajor("Computer Science")
        .setScholarship(true)
        .setCourses(Arrays.asList("ihm", "UML", "Databases"))
        .build();

        System.out.println("1. [Builder] Account Created:");
        System.out.println("   -> Name:  " + student1.getFullName());
        System.out.println("   -> ID:    " + student1.getId());
        System.out.println("   -> Level: " + student1.getLevel());
        System.out.println("   -> Major: " + student1.getMajor());
        System.out.println("   -> Courses: " + student1.getCourses());

        // 2. Singleton: حفظ في المستودع
        Database db = Database.getInstance();
        db.addAccount(student1);
        System.out.println("\n2. [Singleton] Account saved to database.");

        // التأكد من أن النسخة واحدة فقط
        Database db2 = Database.getInstance();
        System.out.println("   -> Same DB instance? " + (db == db2));

        // 3. Prototype: نسخ الحساب
        System.out.println("\n3. [Prototype] Cloning student1...");
        StudentAccount student2 = student1.clone();
        student2.getCourses().add("Network Security"); // تعديل النسخة

        System.out.println("   -> Original Courses: " + student1.getCourses());
        System.out.println("   -> Cloned Courses:   " + student2.getCourses());
        System.out.println("   (Note: Original remains unchanged thanks to Deep Copy)");

        // طباعة سجل التدقيق
        db.printAuditLog();
    }
}
