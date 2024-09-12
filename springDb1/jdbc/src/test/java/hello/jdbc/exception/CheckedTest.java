package hello.jdbc.exception;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    @Test
    void checked_test(){
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw()  {
        Service service = new Service();

        Assertions.assertThatThrownBy(()-> service.callThrow())
                .isInstanceOf(MyCheckedException.class);



    }





    // Exception 을 상속받은 예외는 체크 예외가 된다

    static  class MyCheckedException extends Exception{
        public MyCheckedException(String message){
            super(message);
        }
    }

    // 예외를 잡아서 처리하던가 던지던가 둘중 하나 해야함
    static class Service{
       Repository repository= new Repository();

       public void callCatch(){
           try {
               repository.call();
           } catch (MyCheckedException e) {
               log.info("예외처리 , message={}",e.getMessage(),e);
           }
       }

       public  void callThrow() throws MyCheckedException {

           repository.call();
       }





    }

    static class Repository{
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }

    }









}
