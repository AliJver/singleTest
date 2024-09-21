import java.util.HashMap;
import java.util.Map;

public class TyshxydmVerificationUtil {
    private static Map<String, Integer> datas = new HashMap<>();
    private static int[] power = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};
    private static char[] code = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'T', 'U', 'W', 'X', 'Y'};

    static {
        for (int i = 0; i < code.length; i++) {
            datas.put(String.valueOf(code[i]), i);
        }
    }

    /**
     * 计算统一社会信用代码的最后一位校验码
     *
     * @param tyshxydm 前17位统一社会信用代码
     * @return 校验码字符
     */
    public static char calculateLastDigit(String tyshxydm) {
        if (tyshxydm == null || tyshxydm.trim().length() != 17) {
            throw new IllegalArgumentException("统一社会信用代码必须是17位");
        }
        int sum = 0;
        char[] chars = tyshxydm.trim().toCharArray(); // 取传入的17位进行计算
        for (int i = 0; i < chars.length; i++) {
            if (!datas.containsKey(String.valueOf(chars[i]))) {
                throw new IllegalArgumentException("非法字符: " + chars[i]);
            }
            sum += power[i] * datas.get(String.valueOf(chars[i]));
        }
        int mod = sum % 31;
        int diff = 31 - (mod == 0 ? 31 : mod);
        return code[diff];
    }

    public static void main(String[] args) {
        String str = "33100420000601069"; // 这里只给出17位
        try {
            char lastDigit = calculateLastDigit(str);
            System.out.println("校验位是：" + lastDigit);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
