package pl.sda.travel360;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // adnotacja wpisywana nad klase konieczna do uruchomienia mockito, dostępna po dodaniu zależności w pliku pom
class CalculatorTest {

    @Mock // powołanie do życia mockito w tescie
    private Display display;

    @InjectMocks // wstrzykuje mockito do klasy Calculator
    private Calculator calculator;

    @Test
    void shouldAddTwoValues() {
        //given

        //when
        var result = calculator.add(5,4);
        //then
        assertEquals(9,result);
        verify(display,times(1))
                .show(5,4,"+",9);
    }

    @Test
    void shouldSubTwoValues() {
        //given

        //when
        var result = calculator.sub(10,2);
        //then
        assertEquals(8,result);
        verify(display,times(1))
                .show(10,2,"-",8);
    }

    @Test
    void shouldMullTwoValues() {
        //given

        //when
        var result = calculator.mull(5, 3);
        //then
        assertEquals(15, result);
        verify(display,times(1))
                .show(5,3,"*",15);
    }
    @Test
    void shouldDivTwoValues() {
        //given

        //when
        var result = calculator.div(30,10);
        //then
        assertEquals(3,result);
        verify(display,times(1))
                .show(30,10,"/",3);
    }
    @Test
    void shouldThrowExceptionWhenDividerIsZero(){
        //given

        //when
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.div(10,0);
        });
        verifyNoInteractions(display);
    }
}