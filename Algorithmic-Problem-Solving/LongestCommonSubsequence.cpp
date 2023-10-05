#include <iostream>
#include <vector>
#include <algorithm>

int longestCommonSubsequence(const std::string& str1, const std::string& str2) {
    int m = str1.length();
    int n = str2.length();

    // Create a 2D vector to store the lengths of common subsequences
    std::vector<std::vector<int>> dp(m + 1, std::vector<int>(n + 1, 0));

    // Building the DP table
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = std::max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    // The length of the longest common subsequence is stored in the bottom-right cell
    return dp[m][n];
}

int main() {
    std::string str1 = "ABCBDAB";
    std::string str2 = "BDCAB";

    int result = longestCommonSubsequence(str1, str2);

    std::cout << "Longest Common Subsequence Length: " << result << std::endl;

    return 0;
}
