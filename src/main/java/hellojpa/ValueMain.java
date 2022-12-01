package hellojpa;

public class ValueMain {
    /**
     * 참고: 자바의 기본 타입은 절대 공유X
     * • int, double 같은 기본 타입(primitive type)은 절대 공유X
     * • 기본 타입은 항상 값을 복사함
     * • Integer같은 래퍼 클래스나 String 같은 특수한 클래스는 공유
     * 가능한 객체이지만 변경X
     */
    public static void main(String[] args) {
        primitiveType();
    }

    private static void primitiveType() {
        int a = 10;//기본 타입은 항상 값을 복사함
        int b = a;

        a = 20;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
