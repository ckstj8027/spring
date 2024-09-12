package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
public class LoginMemberArgumentResolver implements
        HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");
        // 홈컨트롤러 거기에 모델어트라이뷰트마냥 존재여부체크
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        //매개변수 타입이 Member 또는 Member를 상속받는 타입인지 확인하는 것
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument 실행");
        HttpServletRequest request = (HttpServletRequest)  webRequest.getNativeRequest();

        HttpSession session = request.getSession(false);

        //세션 데이터 출력
//        session.getAttributeNames().asIterator()
//                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));
//        log.info("sessionId={}", session.getId());
//        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
//        log.info("creationTime={}", new Date(session.getCreationTime()));
//        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
//        log.info("isNew={}", session.isNew());




        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER)==null) {
            return null;
        }
        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }

}
