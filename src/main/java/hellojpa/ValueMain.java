package hellojpa;

import hellojpa.entity.embeddable.Address;

public class ValueMain {
    /**
     * 참고: 자바의 기본 타입은 절대 공유X
     * • int, double 같은 기본 타입(primitive type)은 절대 공유X
     * • 기본 타입은 항상 값을 복사함
     * • Integer같은 래퍼 클래스나 String 같은 특수한 클래스는 공유
     * 가능한 객체이지만 변경X
     */
    public static void main(String[] args) {
        compareEquivalence();
    }

    /**
     * 동등성(equivalence) 비교: 인스턴스의 값을 비교, equals() 사용
     * • 값 타입은 a.equals(b)를 사용해서 동등성 비교를 해야 함
     * • 값 타입의 equals() 메소드를 적절하게 재정의(주로 모든 필드 사용)
     */
    private static void compareEquivalence() {
        Address address1 = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");

        System.out.println("address1.equals(address2): " + address1.equals(address2));
    }

    /**
     * 동일성(identity) 비교: 인스턴스의 참조 값을 비교, == 사용
     */
    private static void compareIdentity() {
        int a = 10;
        int b = 10;

        System.out.println("(a == b) = " + (a == b));

        Address address1 = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");

        System.out.println("(address1 == address2) = " + (address1 == address2));
    }

    private static void primitiveType() {
        int a = 10;//기본 타입은 항상 값을 복사함
        int b = a;

        a = 20;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
