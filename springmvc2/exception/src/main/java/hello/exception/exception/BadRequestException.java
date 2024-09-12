package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 원래는 서버 내부 오류라서 500 오류여야하는데 이 어노테이션으로 400으로 설정함
//ResponseStatusExceptionResolver 가처리 해줌

@ResponseStatus(code= HttpStatus.BAD_REQUEST,reason="잘못된 요청 오류")
public class BadRequestException  extends RuntimeException{
}
