package interviewtasks;

public class TestReverseStringTask {

    private TimeService timeService = new TimeService();

    public static void main(String[] args) {
        String[] strings = new String[10];
        TestReverseStringTask testTask = new TestReverseStringTask();
        System.out.println(testTask.getReverseString("cooltaskerff"));
//        Scanner s = new Scanner(System.in);
//        String word = s.nextLine();
//        System.out.println("Is "+word+" palindrome? - "+isWordPalindrome(word));
    }

    private static  String getReverseString(String str) {
        char[] chars = str.toCharArray();
        int startSize = str.length() - 1;
        for (int i = 0; i <= (chars.length / 2) - 1; ++i) {
            char l = chars[startSize - i];
            chars[startSize - i]=chars[i];
            chars[i]=l;
        }
        return new String(chars);
    }

    private static  String getReverseString2(String str) {
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = chars.length - 1; i >=0; i--) {
            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }

    private static  String getReverseString3(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static boolean isWordPalindrome(String word){
        String reverseWord = getReverseWord(word);
        //if word equals its reverse, then it is a palindrome
        if(word.equals(reverseWord)){
            return true;
        }
        return false;
    }

    public static String getReverseWord(String word){
        if(word == null || word.isEmpty()){
            return word;
        }

        return word.charAt(word.length()- 1) + getReverseWord(word.substring(0, word.length() - 1));
    }

    public String getCurrentTimeString() {
        return "Current time: " + timeService.getCurrentInstant();
    }
}
