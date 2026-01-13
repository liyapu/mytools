package com.lyp.likou.lk;

/**
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 *
 * 英文中的 元音字母 为（a, e, i, o, u）。
 *
 * 示例 1：
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 *
 * 示例 2：
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
 *
 * 示例 3：
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 *
 * 示例 4：
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。
 *
 * 示例 5：
 * 输入：s = "tryhard", k = 4
 * 输出：1
 *
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 */
public class A01456_定长子串中元音的最大数目 {

    /**
     * 定长滑窗套路
     * 窗口右端点在 i 时，由于窗口长度为 k，所以窗口左端点为 i−k+1。
     * 我总结成三步：入-更新-出。
     * 入：下标为 i 的元素进入窗口，更新相关统计量。如果窗口左端点 i−k+1<0，则尚未形成第一个窗口，重复第一步。
     * 更新：更新答案。一般是更新最大值/最小值。
     * 出：下标为 i−k+1 的元素离开窗口，更新相关统计量，为下一个循环做准备。
     * 以上三步适用于所有定长滑窗题目。
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/solutions/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        int sLength = s.length();
        int max = 0;
        int maxResult = 0;
        int left = 0;
        for (int right = 0; right < sLength; right++) {
            char ch = s.charAt(right);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ){
                max++;
            }
            left = right-k+1;
            if(left < 0){
                continue;
            }
            if(max > maxResult){
                maxResult = max;
            }
            char leftCh = s.charAt(left);
            if(leftCh == 'a' || leftCh == 'e' || leftCh == 'i' || leftCh == 'o' || leftCh == 'u' ){
                max--;
            }
        }
        return maxResult;
    }
}
