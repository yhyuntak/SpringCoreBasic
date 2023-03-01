package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// App은 클라이언트 느낌임.
public class OrderApp {
    public static void main(String[] args) {


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
//
////         필요한 서비스들 : 멤버(멤버를 만들어서 저장하기 위해), 주문
//        Appconfig appconfig = new Appconfig();
//        MemberService memberService = appconfig.memberService();
//        OrderService orderService = appconfig.orderService();


//        MemberService memberService = new MemberServiceImpl(null);
//        OrderService orderService = new OrderServiceImpl(null,null);

        Member member1 = new Member(1L, "member1", Grade.VIP);
        // 회원 가입하기
        memberService.join(member1);
        
        // 회원으로 주문하기. 
        Order order  = orderService.createOrder(member1.getId(), "뽀또", 120000);

        System.out.println("order = " + order);
        System.out.println("계산 가격 = " + order.calculatePrice());

    }
}
