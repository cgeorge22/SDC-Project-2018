
class Solution {
  public static void main(String[] args) {
    System.out.println(solution(12));
  }

  public static int solution(int N) {
    int oneCounter = 0;
    long decNum = (long) Math.pow(11, N);
    char[] charArray;
    String numString = Long.toString(decNum);
    charArray = numString.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      if (charArray[i] == '1') {
        oneCounter += 1;
      }
    }


    return oneCounter;
  }
}
