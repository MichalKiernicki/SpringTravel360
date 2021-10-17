package pl.sda.travel360;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Calculator {

    private Display display;

    public int add(int a, int b){
       var result = a + b;
        display.show(a,b,"+", result);
        return result;
    }
    public int sub(int a, int b){
        var result = a - b;
        display.show(a,b,"-", result);
        return result;
    }
    public int div(int a, int b){
        if(b==0){
            throw new IllegalArgumentException("Nie można dzielić przez zero");
        }
        var result = a / b;
        display.show(a,b,"/", result);
        return result;
    }
    public int mull(int a, int b){
        var result = a * b;
        display.show(a,b,"*", result);
        return result;
    }
}
