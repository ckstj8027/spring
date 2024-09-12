package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    void stringToInteger(){
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        Assertions.assertThat(result).isEqualTo(10);
    }
    @Test
    void integerToString(){
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);
        Assertions.assertThat(result).isEqualTo("10");
    }

    @Test
    void ipPortToStringTest(){
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        IpPortToStringConverter converter = new IpPortToStringConverter();
        String result = converter.convert(ipPort);
        Assertions.assertThat(result).isEqualTo("127.0.0.1:8080");


    }
    @Test
    void stringToIpPortTest(){

        String ipPort="127.0.0.1:8080";
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort result = converter.convert(ipPort);

        IpPort ipPort1 = new IpPort("127.0.0.1", 8080);


        Assertions.assertThat(result).isEqualTo(ipPort1);


    }

}
