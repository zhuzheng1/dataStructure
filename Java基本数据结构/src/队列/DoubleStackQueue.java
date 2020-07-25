package 队列;

import java.util.Stack;

/*双栈实现队列
 * 意思就是用两个栈A和B
 * 先将数据压入栈A，此时是先进后出，不符合队列的先进先出
 * 所以再将数据弹出，并依次压入栈B，此时数据的顺序逆转，也就实现了先进先出的原则
 */
public class DoubleStackQueue {

    public static void main(String[] args) {

        //创建栈A，并进行压栈
        Stack<Integer> A = new Stack<Integer>();
        A.push(1111);
        A.push(2222);
        A.push(3333);
        A.push(4444);
        System.out.println("栈A的元素个数："+A.size());
        
      //创建栈B，并进行压栈
        Stack<Integer> B = new Stack<Integer>();
        int countA = 0;
        int size1 = A.size();
        
        while (countA < size1) {
            System.out.println("栈A第"+(countA+1)+"次弹栈前栈顶元素为："+A.peek());
            B.add(A.pop());
            countA++;
        }
        
        System.out.println("栈A弹栈完毕！\n");
        
        System.out.println("栈B的元素个数："+B.size());
        
        int countB = 0;
        System.out.println("对栈B进行弹栈：");
        
        while (countB < countA) {
            System.out.println(B.pop());
            countB++;
        }
    }

}
