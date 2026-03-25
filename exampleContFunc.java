package calcnum;

public class test {
  public static void main(String[] args) {
    ContinuousFunc funcao = new ContinuousFunc(x -> (x*x)-5);
    System.out.println(funcao.searchZero(-100, 100, 0.5));
  }
}
