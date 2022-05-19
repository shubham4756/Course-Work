
public class q6 {
    public static void main(String[] args) {
        Result st1 = new Result(1, 85, 90);
        Result st2 = new Result(2, 50, 41);

        st1.ShowResult();
        System.out.println("------------------------------------    ");
        st2.ShowResult();
        System.out.println("------------------------------------    ");

        System.out.println("Roll No of student1 : " + st1.getRoll_no());

        System.out.println("------------------------------------    ");
        st2.setSubject2(80);
        System.out.println("subject2 marks of student2 : " + st2.getSubject2());

        System.out.println("------------------------------------    ");
        st1.setSubject1(50);
        System.out.println("subject1 marks of student1 : " + st1.getSubject1());
        System.out.println("Total Marks of student1 : " + st1.getTotal());

        System.out.println("------------------------------------    ");
        st1.setRoll_no(10);
        System.out.println("Roll No of student1 : " + st1.getRoll_no());

        System.out.println("------------------------------------    ");
        st2.setRoll_no(11);
        System.out.println("Roll No of student2 : " + st2.getRoll_no());

        System.out.println("------------------------------------    ");
        st1.ShowResult();
        System.out.println("------------------------------------    ");
        st2.ShowResult();
    }

    private static class Student {
        private int roll_no;

        Student(int rollNo) { this.roll_no = rollNo; }

        int getRoll_no() { return this.roll_no; }

        void setRoll_no(int rollNo) { this.roll_no = rollNo; }
    }

    private static class ClassTest extends Student {
        private int subject1, subject2;

        ClassTest(int rollNo, int subject1, int subject2) {
            super(rollNo);
            this.subject1 = subject1;
            this.subject2 = subject2;
        }

        public int getSubject1() { return subject1; }

        public void setSubject1(int subject1) { this.subject1 = subject1; }

        public int getSubject2() { return subject2; }

        public void setSubject2(int subject2) { this.subject2 = subject2; }
    }

    private static class Result extends ClassTest {

        Result(int rollNo, int subject1, int subject2) {
            super(rollNo, subject1, subject2);
        }

        void ShowResult() {
            System.out.println("Student roll No is " + this.getRoll_no());
            System.out.println("First Subject Marks " + this.getSubject1());
            System.out.println("Second Subject Marks " + this.getSubject2());
            System.out.println("Total Marks " + (this.getSubject1() + this.getSubject2()));
        }

        public int getTotal() { return getSubject1()+getSubject2(); }

    }
}