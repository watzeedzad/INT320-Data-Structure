package code.from.other;


import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 000
 */
public class Koh1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type an infix expression");
        String input;
        System.out.print(">");
        input = sc.nextLine();

        String postfix = toPostfix(input);
        System.out.println("postfix = " + postfix);
        System.out.println(computePostfix(postfix));

    }

    public static String toPostfix(String infix) {

        String postfix = "";
        Stack<String> stack = new Stack<String>();
        StringTokenizer st = new StringTokenizer(infix, "()+-/%*", true); 
        //จับ infix มาแยกระหว่างตัวเลขกับ operator ใส่ใน token 
        while (st.hasMoreTokens()) { 
        //ใช้ลูปเช็ค token ไล่ตั้งแต่ตัวหน้าสุด
            String token = st.nextToken().trim();
            if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                String op;
                while (!(op = stack.pop()).equals("(")) { 
                //ถ้าเจอ ) ให้ดึง operator ใน stack ออกมาใส่ใน postfix จนกว่าจะถึง (
                    postfix += " " + op;
                }
            } else if (token.equals("*") || token.equals("+") || token.equals("-")
                    || token.equals("%") || token.equals("/")) { 
              //กรณีที่ token ตัวนั้นเป็น operator
                int p = operatorPrecedence(token); 
                //เอา operator ไปเช็คค่าความสำคัญ
                while (!stack.isEmpty() && !stack.lastElement().equals("(")
                        && operatorPrecedence(stack.lastElement()) >= p) { 
                //ถ้า operator ที่รับเข้ามามีค่าความสำคัญน้อยกว่า ให้วนลูปเอา operator ใน stack ออกมาจนกว่าจะหมดหรือเจอ (
                    String op = stack.pop();
                    postfix += " " + op;
                }
                stack.push(token); // เอา operator ตัวปัจจุบันใส่ stack

            } else { // ถ้าเป็นตัวเลขไม่ต้องใส่ stack แต่เอามาใส่ใน postfix เลย
                postfix += " " + token;
            }
        }
        while (!stack.isEmpty()) {
        //ถ้า token หมดแล้วหรือก็คือครบทุกตัวแล้ว ให้เอา operator ที่ค้างอยู่ใน stack ออกมาให้หมด
            String op = stack.pop();
            postfix += " " + op;
        }
        return postfix;
    }

    public static int computePostfix(String postfix) {
        int result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        StringTokenizer st = new StringTokenizer(postfix); //นำ postfix มาใส่ token 
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equals("*") || token.equals("+") || token.equals("-")
                    || token.equals("%") || token.equals("/")) {//ถ้าเจอ operator ให้นำไปคำนวณ
                applyOperator(token, stack);
            } else { // ถ้าเจอตัวเลข ให้เอาไปใส่ใน stack
                stack.push(new Integer(token));
            }
        }
        result = ((Integer) stack.pop()).intValue();
        //หลังจากผ่านลูปด้านบนมาได้ จะเหลือเพียงค่าเดียวใน stack นั่นคือคำตอบ
        return result;
    }

    private static void applyOperator(String operator, Stack<Integer> s) {
        int op1 = s.pop(); //เมื่อเจอ operator ดึงตัวเลขสองตัวล่าสุดออกมาคำนวณ แล้วใส่กลับไปใน stack
        int op2 = s.pop();
        int result = 0;
        if (operator.equals("+")) {
            result = op2 + op1;
        } else if (operator.equals("-")) {
            result = op2 - op1;
        } else if (operator.equals("/")) {
            result = op2 / op1;
        } else if (operator.equals("%")) {
            result = op2 % op1;
        } else if (operator.equals("*")) {
            result = op2 * op1;
        }
        s.push(result);
    }

    private static int operatorPrecedence(String operator) {
        if (operator.equals("*") || operator.equals("/") //เช็คความสำคัญของ operator * / มากกว่า + -
                || operator.equals("%")) {
            return 1;
        } else if (operator.equals("-") || operator.equals("+")) {
            return 0;
        }
        return -1;
    }
}
