package ����;

import java.util.Stack;

/*˫ջʵ�ֶ���
 * ��˼����������ջA��B
 * �Ƚ�����ѹ��ջA����ʱ���Ƚ�����������϶��е��Ƚ��ȳ�
 * �����ٽ����ݵ�����������ѹ��ջB����ʱ���ݵ�˳����ת��Ҳ��ʵ�����Ƚ��ȳ���ԭ��
 */
public class DoubleStackQueue {

    public static void main(String[] args) {

        //����ջA��������ѹջ
        Stack<Integer> A = new Stack<Integer>();
        A.push(1111);
        A.push(2222);
        A.push(3333);
        A.push(4444);
        System.out.println("ջA��Ԫ�ظ�����"+A.size());
        
      //����ջB��������ѹջ
        Stack<Integer> B = new Stack<Integer>();
        int countA = 0;
        int size1 = A.size();
        
        while (countA < size1) {
            System.out.println("ջA��"+(countA+1)+"�ε�ջǰջ��Ԫ��Ϊ��"+A.peek());
            B.add(A.pop());
            countA++;
        }
        
        System.out.println("ջA��ջ��ϣ�\n");
        
        System.out.println("ջB��Ԫ�ظ�����"+B.size());
        
        int countB = 0;
        System.out.println("��ջB���е�ջ��");
        
        while (countB < countA) {
            System.out.println(B.pop());
            countB++;
        }
    }

}
