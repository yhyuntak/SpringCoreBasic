package hello.core.member;

import hello.core.Appconfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        Appconfig appconfig = new Appconfig();
        memberService = appconfig.memberService();
    }

    @Test

    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member); // 회원가입
        Member findMember = memberService.findMember(1L); // 회원가입 되었는지 확인

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
