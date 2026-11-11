package core;

import java.util.ArrayList;
import java.util.List;

public class StudentAccount implements Cloneable {
    // الحقول الإجبارية
    private String id;
    private String fullName;
    private String email;

    // الحقول الاختيارية
    private String phone;
    private String level;
    private String major;
    private boolean scholarship;
    private List<String> courses;

    // مُنشئ خاص يُستدعى من الـ Builder فقط
    private StudentAccount(StudentAccountBuilder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.level = builder.level;
        this.major = builder.major;
        this.scholarship = builder.scholarship;
        this.courses = builder.courses;
    }

    // Getters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean isScholarship() { return scholarship; }
    public List<String> getCourses() { return courses; }

    // === نمط Prototype (النسخ) ===
    @Override
    public StudentAccount clone() {
        try {
            StudentAccount cloned = (StudentAccount) super.clone();
            // نسخة عميقة (Deep Copy) للقائمة لتجنب التداخل
            cloned.courses = new ArrayList<>(this.courses);
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // === نمط Builder (الكلاس الداخلي) ===
    public static class StudentAccountBuilder {
        private String id;
        private String fullName;
        private String email;

        // قيم افتراضية
        private String phone = null;
        private String level = "L1";
        private String major = null;
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        // مُنشئ الـ Builder يتطلب الحقول الإجبارية
        public StudentAccountBuilder(String id, String fullName, String email) {
            if (id == null || id.isEmpty()) throw new IllegalArgumentException("ID is required!");
            if (fullName == null || fullName.isEmpty()) throw new IllegalArgumentException("Full Name is required!");
            if (email == null || !email.contains("@")) throw new IllegalArgumentException("Valid Email is required!");
            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }

        public StudentAccountBuilder setPhone(String phone) { this.phone = phone; return this; }
        public StudentAccountBuilder setLevel(String level) { this.level = level; return this; }
        public StudentAccountBuilder setMajor(String major) { this.major = major; return this; }
        public StudentAccountBuilder setScholarship(boolean scholarship) { this.scholarship = scholarship; return this; }
        public StudentAccountBuilder setCourses(List<String> courses) { this.courses = courses; return this; }

        public StudentAccount build() {
            return new StudentAccount(this);
        }
    }
}
